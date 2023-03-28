package ru.project.carsharing.controllers;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import ru.project.carsharing.dto.PersonDTO;
import ru.project.carsharing.model.Image;
import ru.project.carsharing.model.Person;
import ru.project.carsharing.services.ImageService;
import ru.project.carsharing.services.PeopleService;
import ru.project.carsharing.validation.PersonValidation;

import javax.validation.Valid;
import java.security.Principal;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/people")
@RequiredArgsConstructor
@Slf4j//Логгер
public class PeopleController {

    private final PeopleService peopleService;
    private final ModelMapper modelMapper;
    private final PersonValidation validator;
    private final ImageService imageService;

    @GetMapping()
    public String allPeople(Model model){
        model.addAttribute("person", peopleService.findAll().stream().map(this::convertToPersonDTO)
                .collect(Collectors.toList()));
        return "people/all";
    }

    @GetMapping("/{id}")
    public String getPerson(@PathVariable("id") int id, Model model){
        model.addAttribute("person", peopleService.findOne(id));
        return "people/show";
    }

    @PatchMapping("/{id}")
    public String update(@ModelAttribute("person") @Valid Person person, BindingResult bindingResult,
                         @PathVariable("id") int id) {
        if (bindingResult.hasErrors())
            return "people/edit";

        peopleService.update(id, person);
        return "redirect:/people";
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable("id") int id) {
        peopleService.delete(id);
        return "redirect:/people/show";
    }

    @GetMapping("/login")
    public String login(Principal p, Model model){
        model.addAttribute("user", peopleService.getPersonByPrincipal(p));
        model.addAttribute("title", "Вход");
        return "people/login";
    }

    @GetMapping("/registration")
    public String registration(Principal p, Model model){
        model.addAttribute("user", peopleService.getPersonByPrincipal(p));
        model.addAttribute("title", "Регистрация");
        return "people/registration";
    }

    @PostMapping("/registration")
    public String registrationPerson(Principal principal, Person person, Model model, BindingResult result){
        System.out.println("registration method called");

        model.addAttribute("user", peopleService.getPersonByPrincipal(principal));
        validator.validate(person, result);
        if (result.hasErrors()){
            model.addAttribute("message", "Пользователь с таким e-mail уже есть!");
            model.addAttribute("title", "Регистрация");
            return "people/registration";
        }

        peopleService.addPerson(person);
        model.addAttribute("title", "Успешная регистрация");
        model.addAttribute("message", "Успешная регистрация");

        return "people/message";
    }

    @GetMapping("/account")
    public String account(Principal principal, Model model){
        Person person = peopleService.getPersonByPrincipal(principal);
        model.addAttribute("title", "Личный кабинет");
        model.addAttribute("user", person);
        model.addAttribute("targetUser", person);
        return "people/userInfo";
    }

    @GetMapping("/logout/success")
    public String successLogout(Principal principal, Model model){
        model.addAttribute("title", "Выход из аккаунта");
        model.addAttribute("message", "Вы успешно вышли из аккаунта");
        model.addAttribute("user", peopleService.getPersonByPrincipal(principal));
        return "people/message";
    }

    @ResponseBody
    @PostMapping("/account/setAvatar")
    public String setAvatar(Principal principal, @RequestParam(name = "file")MultipartFile multipartFile){
        log.info(""+multipartFile);
        Person person = peopleService.getPersonByPrincipal(principal);
        Image image = imageService.toImage(multipartFile);
        peopleService.setAvatar(image, person);
        return "success";
    }

    private Person convertToPerson(PersonDTO personDTO) {
        return modelMapper.map(personDTO, Person.class);
    }

    private PersonDTO convertToPersonDTO(Person person){
        return modelMapper.map(person, PersonDTO.class);
    }
}
