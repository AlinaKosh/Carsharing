package com.project.carshar.controllers;

import com.project.carshar.exception.CarAlreadyException;
import com.project.carshar.model.Order;
import com.project.carshar.model.OrderStatus;
import com.project.carshar.model.User;
import com.project.carshar.services.CarService;
import com.project.carshar.services.OrderService;
import com.project.carshar.services.UserService;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;


@Controller
@RequiredArgsConstructor
public class OrderController {
    private final OrderService orderService;
    private final CarService carService;
    private final UserService userService;

    @GetMapping("admin/orders")
    @PreAuthorize("hasAuthority('ADMIN')")
    public String list(Model model) {
        List<Order> orders = orderService.findAll();
        model.addAttribute("list", orders);
        /*
        int[] sum = new int[orders.size()];
        for (int i = 0; i < orders.size(); i++) {
            sum[i] = orderService.getSum(orders.get(i).getId());
        }
        model.addAttribute("sum", sum);
         */
        return "orders/list";
    }

    @GetMapping("/profile/orders")
    @Secured({"IS_AUTHENTICATED_FULLY", "IS_AUTHENTICATED_REMEMBERED"})
    public String myOrders(Model model) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User user = userService.findByEmail(auth.getName());
        List<Order> orders = orderService.findAllByUser(user);
        model.addAttribute("list", orders);
        /*
        int[] sum = new int[orders.size()];
        for (int i = 0; i < orders.size(); i++) {
            sum[i] = orderService.getSum(orders.get(i).getId());
        }
        model.addAttribute("sum", sum);
         */
        return "/orders/list";
    }

    @GetMapping("orders/new/{id}")
    @PreAuthorize("hasAuthority('USER')")
    public String addOrder(@PathVariable("id") long id, Model model) {
        model.addAttribute("object", new Order());
        model.addAttribute("car", carService.findById(id));
        return "orders/new";
    }


    @PostMapping("orders/add")
    @PreAuthorize("hasAuthority('USER')")
    public String addOrder(@Valid @ModelAttribute("object") Order object, BindingResult br, Model model) {
        try {
            Authentication auth = SecurityContextHolder.getContext().getAuthentication();
            User user = userService.findByEmail(auth.getName());
            object.setUser(user);
            orderService.save(object);
        } catch (Exception e) {
            e.printStackTrace();
            model.addAttribute("object", new Order());
            model.addAttribute("car", carService.findById(object.getCar().getId()));
            model.addAttribute("error", "Нет свободных авто на выбранный период");
            return "/orders/new";
        }
        return "redirect:/cars";
    }

    @GetMapping("/orders/{id}/return_car")
    public String returnCar(@PathVariable("id") Long id, Model model) {
        Order order = orderService.findById(id);
        order.setReturned(LocalDate.now());
        try {
            orderService.save(order);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "redirect:/profile/orders";
    }


    @GetMapping("/orders/edit/{id}")
    public String edit(@PathVariable("id") Long id, Model model) {
        Order object = orderService.findById(id);
        model.addAttribute("object", object);
        return "orders/edit";
    }

    @PostMapping("orders/edit")
    public String edit(@Valid @ModelAttribute("object") Order order, BindingResult br, Model model) {
        if (br.hasErrors()) {
            return "orders/edit";
        }
        try {
            orderService.save(order);

        } catch (Exception e) {
            model.addAttribute("error", "Нет свободных авто на выбранный период");
            return "orders/edit";
        }
        return "redirect:/profile/orders";
    }

    @GetMapping("orders/delete/{id}")
    public String delete(@PathVariable("id") long id, Model model) {
        model.addAttribute("object", orderService.findById(id));
        return "orders/delete";
    }

    @PostMapping("orders/delete")
    public String delete(Order object) {
        orderService.delete(object);
        return "redirect:/profile/orders";
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @GetMapping("orders/find")
    public String find(Model model){
        return "orders/findOrder";
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @GetMapping("admin/orders/changeStatus")
    public String changeOrderStatus(Long id, Model model){
        model.addAttribute("order", orderService.findById(id));
        return "orders/changeStatus";
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @GetMapping("admin/orders/changeStatus/toGive/{id}")
    public String toGive(@PathVariable("id") long id) throws Exception {
        Order order = orderService.findById(id);
        order.setStatus(OrderStatus.GIVE);
        orderService.save(order);
        return "redirect:/add/card/"+order.getCar().getId()+"/"+order.getId();
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @GetMapping("admin/orders/changeStatus/toDenied/{id}")
    public String toDenied(@PathVariable("id") long id) throws Exception {
        Order order = orderService.findById(id);
        order.setStatus(OrderStatus.DENIED);
        order.setReturned(LocalDate.now());
        orderService.save(order);
        return "redirect:/orders/find";
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @GetMapping("admin/orders/changeStatus/toFinish/{id}")
    public String toFinish(@PathVariable("id") long id) throws Exception {
        Order order = orderService.findById(id);
        order.setStatus(OrderStatus.FINISH);
        order.setReturned(LocalDate.now());
        orderService.save(order);
        return "redirect:/add/card/"+order.getCar().getId()+"/"+order.getId();
    }

}
