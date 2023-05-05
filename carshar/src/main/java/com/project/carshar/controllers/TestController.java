package com.project.carshar.controllers;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.time.LocalDate;


@Controller
@RequestMapping("/test")
public class TestController {

    @GetMapping
    public String addType(Model model) {
        model.addAttribute("object",new Test());
        return "test";
    }


    @PostMapping("add")
    public String addType(Test t) {
        System.out.println(t);
        return "redirect:/test";
    }


}


class Test{
    int i;
    LocalDate date;
    public int getI() {
        return i;
    }

    public void setI(int i) {
        this.i = i;
    }
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    @Override
    public String toString() {
        return "Test{" +
                "i=" + i +
                ", date=" + date +
                '}';
    }
}