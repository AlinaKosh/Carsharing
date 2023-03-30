package com.project.carshar.controllers;

import com.project.carshar.model.Car;
import com.project.carshar.services.CarService;
import com.project.carshar.services.FirmService;
import com.project.carshar.services.TransmissionService;
import com.project.carshar.services.TypeService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;


@Controller
@RequestMapping("/cars")
public class CarController {
     final CarService carService;
     final FirmService firmService;
     final TypeService typeService;
     final TransmissionService transmissionService;

    public CarController(CarService carService, FirmService firmService, TypeService typeService, TransmissionService transmissionService) {
        this.carService = carService;
        this.firmService = firmService;
        this.typeService = typeService;
        this.transmissionService = transmissionService;
    }

    @GetMapping
    @PreAuthorize("hasAnyAuthority({'ADMIN','USER'})")
    public String list(Model model) {
        model.addAttribute("list", carService.findAll());
        return "cars/list";
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @GetMapping(value = "/new")
    public String addType(Model model) {
        model.addAttribute("object", new Car());
        model.addAttribute("firms", firmService.findAll());
        model.addAttribute("types", typeService.findAll());
        model.addAttribute("transmissions", transmissionService.findAll());
        return "cars/new";
    }

    @PostMapping("add")
    public String addType(@Valid @ModelAttribute("object") Car object, BindingResult br, Model model) {
        if (br.hasErrors()) {
            return "cars/new";
        }
        try {
            carService.save(object);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            model.addAttribute("firms", firmService.findAll());
            model.addAttribute("types", typeService.findAll());
            model.addAttribute("transmissions", transmissionService.findAll());
            br.rejectValue("number", "error.object", e.getMessage());
            return "cars/new";
        }

        return "redirect:/cars";
    }
    @PreAuthorize("hasAuthority('ADMIN')")
    @GetMapping("/edit/{id}")
    public String edit(@PathVariable("id") long id, Model model) {
        model.addAttribute("object", carService.findById(id));
        model.addAttribute("firms", firmService.findAll());
        model.addAttribute("types", typeService.findAll());
        model.addAttribute("transmissions", transmissionService.findAll());
        return "cars/edit";
    }

    @PostMapping("/edit")
    public String edit(@Valid @ModelAttribute("object") Car object, BindingResult br,Model model) {
        if (br.hasErrors()) {
            return "cars/edit";
        }
        try {
            carService.save(object);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            model.addAttribute("firms", firmService.findAll());
            model.addAttribute("types", typeService.findAll());
            model.addAttribute("transmissions", transmissionService.findAll());
            br.rejectValue("number", "error.object", e.getMessage());
            return "cars/edit";
        }
        return "redirect:/cars";
    }
    @PreAuthorize("hasAuthority('ADMIN')")
    @GetMapping("/delete/{id}")
    public String delete(@PathVariable("id") long id, Model model) {
        model.addAttribute("object", carService.findById(id));
        return "cars/delete";
    }

    @DeleteMapping("/delete")
    public String delete(@ModelAttribute("object") Car object, Model model) {
        System.out.println(object);
        try {
            carService.delete(object);
        } catch (Exception e) {
            model.addAttribute("object", carService.findById(object.getId()));
            model.addAttribute("error","На данный автомобиль есть заказ. Альтернативы нет");
            return "/cars/delete";
        }

        return "redirect:/cars";
    }


}
