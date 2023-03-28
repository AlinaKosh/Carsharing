package ru.project.carsharing.controllers;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.project.carsharing.model.Person;
import ru.project.carsharing.services.PeopleService;

import java.security.Principal;

@Controller
@RequestMapping("/")
@RequiredArgsConstructor
@Slf4j
public class MainController {
    private final PeopleService peopleService;
    @GetMapping
    public String main(Principal p, Model model){
        Person u = peopleService.getPersonByPrincipal(p);
        log.info(""+p);
        model.addAttribute("user", u);
        model.addAttribute("title", "Главная");
        if (p == null){
            return "main";
        }
        return "redirect:/account";
    }
}
