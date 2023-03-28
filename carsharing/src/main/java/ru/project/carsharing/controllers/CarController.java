package ru.project.carsharing.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.project.carsharing.model.Car;
import ru.project.carsharing.services.CarServices;

@Controller
@RequestMapping("/cars")
public class CarController {

    private final CarServices carServices;

    @Autowired
    public CarController(CarServices productServices) {
        this.carServices = productServices;
    }

    @GetMapping()
    public String index(Model model) {
        model.addAttribute("cars", carServices.findAll());
        return "cars/index";
    }

    @PostMapping("/create")
    public void saveCar(Car c){
        carServices.save(c);
    }
}
