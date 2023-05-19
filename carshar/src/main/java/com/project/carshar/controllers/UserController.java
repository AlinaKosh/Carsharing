package com.project.carshar.controllers;

import com.project.carshar.model.OrderReturn;
import com.project.carshar.model.User;
import com.project.carshar.services.OrderReturnService;
import com.project.carshar.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
@RequestMapping("/admin/users")
@PreAuthorize("hasAnyAuthority('ADMIN')")
public class UserController {

    @Autowired
    private final UserService userService;

    @Autowired
    private OrderReturnService orderReturnService;

    public UserController(UserService service) {
        this.userService = service;
    }

    @GetMapping
    public String list(Model model) {
        model.addAttribute("users", userService.findAll());
        return "users/list";
    }

    @GetMapping("/returnStatus/{user}/{order}")
    public String returnStatus(@PathVariable("user") Long user, @PathVariable("order") Long order, Model model){
        model.addAttribute("user", user);
        model.addAttribute("order", order);
        return "users/returnRez";
    }

    @PostMapping("/returnStatus/statement")
    public String saveStatement(Model model, Long userId, Double statement, Long orderId){
        OrderReturn orderReturn = new OrderReturn();
        orderReturn.setStatement(statement);
        orderReturn.setUser(userService.findById(userId));
        orderReturnService.save(orderReturn);
        return "redirect:/admin/orders/changeStatus/toFinish/" + orderId;
    }

}
