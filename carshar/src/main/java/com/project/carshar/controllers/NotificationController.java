package com.project.carshar.controllers;

import com.project.carshar.model.User;
import com.project.carshar.services.NotificationService;
import com.project.carshar.services.UserService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;


@Controller
public class NotificationController {
    final NotificationService service;
    final UserService userService;

    public NotificationController(NotificationService service, UserService userService) {
        this.service = service;
        this.userService = userService;
    }

    @GetMapping("profile/notifications")
    @PreAuthorize("hasAuthority('USER')")
    public String list(Model model) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User user = userService.findByEmail(auth.getName());
        model.addAttribute("list", service.findByUser(user.getId()));
        return "notifications/list";
    }

}
