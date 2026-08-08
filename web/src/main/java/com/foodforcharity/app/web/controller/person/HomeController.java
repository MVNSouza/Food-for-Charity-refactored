package com.foodforcharity.app.web.controller.person;

import com.foodforcharity.app.mediator.Mediator;
import com.foodforcharity.app.web.controller.AbstractController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController extends AbstractController {

    @Autowired
    public HomeController(Mediator mediator) {
        super(mediator);
    }

    @GetMapping("/home")
    public String getHomeView() {
        return "redirect:/" + getPersonRole().toLowerCase() + "/home";
    }

    @GetMapping("/register")
    public String getRegisterView() {
        return "/register";
    }
}