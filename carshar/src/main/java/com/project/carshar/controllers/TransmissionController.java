package com.project.carshar.controllers;

import com.project.carshar.model.Transmission;
import com.project.carshar.services.TransmissionService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;


@Controller
@RequestMapping("/admin/transmissions")
@PreAuthorize("hasAuthority('ADMIN')")
public class TransmissionController {
    private final TransmissionService service;

    public TransmissionController(TransmissionService service) {
        this.service = service;
    }

    @GetMapping
    public String list(Model model) {
        model.addAttribute("list", service.findAll());
        return "transmissions/list";
    }

    @GetMapping(value = "/new")
    public String addType(Model model) {
        model.addAttribute("object", new Transmission());
        return "transmissions/new";
    }


    @PostMapping("add")
    public String addType(@Valid @ModelAttribute("object") Transmission object, BindingResult br, Model model) {
        if (br.hasErrors()) {
            return "transmissions/new";
        }
        try {
            String str = object.getName();  //делаем первую букву заглавной
            String cap = str.substring(0, 1).toUpperCase() + str.substring(1);
            object.setName(cap);
            service.save(object);
        } catch (Exception e) {
            br.rejectValue("name", "error.object", e.getMessage());
            return "transmissions/new";
        }
        model.addAttribute("success","Тип '"+object.getName()+"' успешно добавлен");
        return "transmissions/new";
    }

    @GetMapping("/edit/{id}")
    public String edit(@PathVariable("id") int id, Model model) {
        model.addAttribute("object", service.findById(id));
        return "transmissions/edit";
    }

    @PostMapping("/edit")
    public String edit(@Valid @ModelAttribute("object") Transmission object, BindingResult br) {
        if (br.hasErrors()) {
            return "transmissions/edit";
        }
        try {
            service.save(object);
        } catch (Exception e) {
            br.rejectValue("name", "error.name", e.getMessage());
            return "transmissions/edit";
        }
        return "redirect:/admin/transmissions";
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable("id") int id, Model model) {
        model.addAttribute("object", service.findById(id));
        return "transmissions/delete";
    }

    @DeleteMapping("/delete")
    public String delete(Transmission object) {
        service.delete(object);
        return "redirect:/admin/transmissions";
    }

}
