package com.project.carshar.controllers;

import com.project.carshar.model.Firm;
import com.project.carshar.services.FirmService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;


@Controller
@RequestMapping("/admin/firms")
@PreAuthorize("hasAuthority('ADMIN')")
public class FirmController {
    private final FirmService service;

    public FirmController(FirmService service) {
        this.service = service;
    }

    @GetMapping
    public String list(Model model) {
        model.addAttribute("list", service.findAll());
        return "firms/list";
    }

    @GetMapping(value = "/new")
    public String addType(Model model) {
        model.addAttribute("object", new Firm());
        return "firms/new";
    }


    @PostMapping("add")
    public String addType(@Valid @ModelAttribute("object") Firm object, BindingResult br) {
        if (br.hasErrors()) {
            return "firms/new";
        }
        try {
            service.save(object);
        } catch (Exception e) {
            br.rejectValue("name", "error.name", e.getMessage());
            return "firms/new";
        }
        return "redirect:/admin/firms";
    }

    @GetMapping("/edit/{id}")
    public String edit(@PathVariable("id") int id, Model model) {
        model.addAttribute("object", service.findById(id));
        return "firms/edit";
    }

    @PostMapping("/edit")
    public String edit(@Valid @ModelAttribute("object") Firm object, BindingResult br) {
        if (br.hasErrors()) {
            return "firms/edit";
        }
        try {
            service.save(object);
        } catch (Exception e) {
            br.rejectValue("name", "error.name", e.getMessage());
            return "firms/edit";
        }
        return "redirect:/admin/firms";
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable("id") int id, Model model) {
        model.addAttribute("object", service.findById(id));
        return "firms/delete";
    }

    @DeleteMapping("/delete")
    public String delete(Firm object) {
        service.delete(object);
        return "redirect:/admin/firms";
    }

}
