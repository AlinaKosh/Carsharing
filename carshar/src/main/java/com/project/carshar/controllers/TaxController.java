package com.project.carshar.controllers;

import com.project.carshar.model.Tax;
import com.project.carshar.services.CarService;
import com.project.carshar.services.TaxService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;


@Controller
@RequestMapping("/admin/tax")
@PreAuthorize("hasAuthority('ADMIN')")
public class TaxController {
    private final TaxService taxService;
    private final CarService carService;

    public TaxController(TaxService taxService, CarService carService) {
        this.taxService = taxService;
        this.carService = carService;

    }

    @GetMapping
    public String list(Model model) {
        model.addAttribute("list", taxService.findAll());
        return "tax/list";
    }

    @GetMapping(value = "/new")
    public String addType(Model model) {
        model.addAttribute("carList", carService.findAll());
        return "tax/new";
    }


    @PostMapping("add")
    public String addType(@RequestParam(value = "cars", required = false) long[] cars) {
        for (long i : cars) {
            Tax t = new Tax();
            t.setCar(carService.findById(i));
            try{
                taxService.save(t);
            }catch (Exception e){
                e.printStackTrace();
            }

        }
        return "redirect:/admin/tax";
    }


}
