package com.project.carshar.controllers;

import com.project.carshar.model.User;
import com.project.carshar.services.OrderService;
import com.project.carshar.services.UserService;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;


@Controller
public class LoginController {

	private final UserService userService;
    private final OrderService orderService;

    public LoginController(UserService userService, OrderService orderService) {
        this.userService = userService;
        this.orderService = orderService;
    }

    @GetMapping("/")
    public String index() {
        return "/index";
	}

	@GetMapping("/login")
	public String login() {
		return "security/login";
	}

    @GetMapping("/access-denied")
    public String accessDeniedPage() {
        return "security/access-denied";
    }

	@GetMapping("/registration")
	public String registration(Model model) {
		model.addAttribute("user", new User());
		return "security/registration";
	}

    @PostMapping("/newUser")
    public String createNewAccount(@Valid User user, BindingResult br) {
        System.out.println(user);
        if (br.hasErrors()) {
            return "security/registration";
        }
        try {
            userService.create(user);
        } catch (Exception e) {
            br.rejectValue("email", "error.user", e.getMessage());
            return "security/registration";
        }
        return "redirect:/login";
    }


    @GetMapping("/logout")
    public String logoutPage (HttpServletRequest request, HttpServletResponse response) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth != null){
            new SecurityContextLogoutHandler().logout(request, response, auth);
        }
        return "redirect:/login?logout";
    }

	@GetMapping("/profile")
	@Secured({"IS_AUTHENTICATED_FULLY","IS_AUTHENTICATED_REMEMBERED"})
	public String profile(Model model) {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		User user = userService.findByEmail(auth.getName());
		model.addAttribute("user", user);
		return "profile/index";
	}




    @GetMapping("/profile/edit/{id}")
    public String edit(@PathVariable("id") Long id, Model model) {
        User user = userService.findById(id);
        System.out.println(user);
        model.addAttribute("user", user);
        return "users/edit";
    }

    @PostMapping("profile/edit")
    public String edit(@Valid @ModelAttribute("user") User user, BindingResult br) {
        if (br.hasErrors()) {
            return "users/edit";
        }
        try {
            userService.update(user);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return "users/edit";
        }
        return "redirect:/logout";
    }
}
