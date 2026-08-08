package com.foodforcharity.app.web.controller.donee;

import com.foodforcharity.app.mediator.Mediator;
import com.foodforcharity.app.web.controller.AbstractController;
import com.foodforcharity.app.web.model.FoodPreferences;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/donee")
@PreAuthorize("hasAuthority('Donee')")
public class DoneeFoodRequestController extends AbstractController {

    @Autowired
    public DoneeFoodRequestController(Mediator mediator) {
        super(mediator);
    }

    @GetMapping("/FoodRequests")
    public String getFoodRequestsView() {
        return "donee/Food-request";
    }

    @GetMapping("/FoodRequest/{foodId}")
    public String getFoodRequestView(
            @PathVariable("foodId") long foodId, // CORREÇÃO: Binding correto da variável
            RedirectAttributes redirectAttributes) {
            
        // CORREÇÃO: Novamente, como há um redirect, 'Model' não funciona. Usar RedirectAttributes.
        redirectAttributes.addFlashAttribute("FoodRequest", new FoodPreferences());
        
        return "redirect:/";
    }
}