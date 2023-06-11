package com.project.carshar.controllers;

import com.project.carshar.model.*;
import com.project.carshar.repositories.OrderCardRepository;
import com.project.carshar.repositories.RoleRepository;
import com.project.carshar.services.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.persistence.Column;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import java.net.http.HttpRequest;
import java.time.LocalDate;

@Controller
public class CardController {
    @Autowired
    private CardService cardService;
    @Autowired
    private CarService carService;
    @Autowired
    private OrderService orderService;
    @Autowired
    private OrderCardRepository orderCardRepository;
    @Autowired
    private OrderReturnService orderReturnService;
    @Autowired
    private UserService userService;


    @GetMapping("/add/card/{id}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public String saveCard(@PathVariable("id") long id, Model model){
        model.addAttribute("card", new CardForm());
        model.addAttribute("car", carService.findById(id));
        return "card/add";
    }

    @GetMapping("/add/card/{id}/{order}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public String saveCard(@PathVariable("id") long id, Model model, @PathVariable long order){
        model.addAttribute("order", order);
        model.addAttribute("card", new CardForm());
        model.addAttribute("car", carService.findById(id));

        return "card/add";
    }

    @PostMapping("/add/card")
    @PreAuthorize("hasAuthority('ADMIN')")
    public String saveCard(@ModelAttribute("object") CardForm cardForm){

        getCardFromForm(cardForm);

        return "redirect:/card/list";
    }

    private boolean flag = true;
    @PostMapping("/add/card/{order}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public String saveCard(@ModelAttribute("object") CardForm cardForm, @PathVariable long order){

        Card card = getCardFromForm(cardForm);
        System.err.println(card);

        Order order1 = orderService.findById(order);
        var orderCard = new OrderCard();
        orderCard.setOrder(order1);
        //int sum = order1.getCar().getCostPerDay() * order1.getDays();
        double sum = order1.getSum();
        //System.out.println(sum);

        if (flag) {
            flag=false;
        }else {
            if (cardForm.getKuzovState() == State.VERY_GOOD && cardForm.getSalonState() == State.VERY_GOOD) {
                saveOrderReturn(0.5, order);
            } else if (cardForm.getSalonState() == State.GOOD && cardForm.getKuzovState() == State.GOOD || cardForm.getSalonState() == State.GOOD &&
                    cardForm.getKuzovState() == State.VERY_GOOD || cardForm.getSalonState() == State.VERY_GOOD && cardForm.getKuzovState() == State.GOOD) {
                saveOrderReturn(0.75, order);
            } else if (cardForm.getSalonState() == State.BAD && cardForm.getKuzovState() == State.BAD) {
                saveOrderReturn(1.25, order);
            } else {
                saveOrderReturn(1.0, order);
            }
            flag=true;
        }

        orderCard.setCard(card);

        orderCardRepository.save(orderCard);

        return "redirect:/card/list";
    }

    /*
    @PostMapping("/add/card/{order}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public String saveCard(@ModelAttribute("object") CardForm cardForm, @PathVariable long order){

        Card card = getCardFromForm(cardForm);
        System.err.println(card);
        Order order1 = orderService.findById(order);
        var orderCard = new OrderCard();
        orderCard.setOrder(order1);
        orderCard.setCard(card);
        orderCardRepository.save(orderCard);

        return "redirect:/card/list";
    }
     */

    @GetMapping("/card/list")
    @PreAuthorize("hasAuthority('ADMIN')")
    public String listCarsCard(Model model){
        model.addAttribute("cars", carService.findAll());
        return "card/list";
    }

    @GetMapping("/card/car/{id}")
    @PreAuthorize("hasAuthority('USER')")
    public String cardByCar(@PathVariable("id")long id, @ModelAttribute("card")Card card, Model model){
        //model.addAttribute("cards", cardService.findById(card.getCar().getId()));
        model.addAttribute("cards", cardService.findAllByCarId(id));
        model.addAttribute("cars", carService.findById(id));
        return "card/cardForCar";
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @GetMapping("/card/edit/{id}")
    public String edit(@PathVariable("id") long id, Model model) {
        model.addAttribute("card", cardService.findById(id));
        return "card/edit";
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @PostMapping("/card/edit")
    public String edit(@ModelAttribute("card")Card card, BindingResult br, Model model){
        if (br.hasErrors()){
            return "card/edit";
        }
        try {
            cardService.save(card);
        }catch (Exception e){
            System.out.println(e.getMessage());
            model.addAttribute("card", cardService.findById(card.getId()));
            return "card/edit";
        }
        return "redirect:/cardCart";
    }

    private Card getCardFromForm(CardForm cardForm){

        Card card = new Card();
        card.setSalonState(cardForm.getSalonState());
        card.setKuzovState(cardForm.getKuzovState());
        card.setFuel(cardForm.getFuel());
        card.setTimeWatch(LocalDate.now());
        card.setCar(carService.findById(cardForm.carId));

        return cardService.save(card);

    }

    private void saveOrderReturn(double statement, long order){
        OrderReturn orderReturn = new OrderReturn();
        orderReturn.setStatement(statement);
        System.out.println(statement);
        //orderReturn.setUser(userService.findById(order));
        orderReturn.setUser(orderService.findUserByOrderId(order));
        System.out.println(orderService.findUserByOrderId(order));
        orderReturnService.save(orderReturn);
    }

}

@Data
@NoArgsConstructor
class CardForm{
    long carId;

    private State salonState = State.GOOD;

    private State kuzovState = State.GOOD;

    private double fuel;
}
