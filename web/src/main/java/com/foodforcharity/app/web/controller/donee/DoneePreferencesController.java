package com.foodforcharity.app.web.controller.donee;

import com.foodforcharity.app.domain.response.Response;
import com.foodforcharity.app.mediator.Mediator;
import com.foodforcharity.app.usecase.profile.selectpreferences.SelectPreferencesCommand;
import com.foodforcharity.app.web.controller.AbstractController;
import com.foodforcharity.app.web.model.FoodPreferences;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.util.concurrent.ExecutionException;

@Controller
@RequestMapping("/donee")
@PreAuthorize("hasAuthority('Donee')")
public class DoneePreferencesController extends AbstractController {

    @Autowired
    public DoneePreferencesController(Mediator mediator) {
        super(mediator);
    }

    @GetMapping("/food-preferences")
    public String getFoodPreferencesView(FoodPreferences foodPreferences) {
        return "donee/food-preferences";
    }

    @GetMapping("/edit-food-preferences")
    public String getEditFoodPreferencesView(FoodPreferences foodPreferences) {
        return "donee/edit-food-preferences";
    }

    @PostMapping("/edit-food-preferences")
    public String selectPreferences(
            @Valid FoodPreferences foodPreferences, 
            BindingResult result) throws ExecutionException, InterruptedException {

        if (result.hasErrors()) {
            return "donee/food-preferences";
        }

        SelectPreferencesCommand command = new SelectPreferencesCommand();
        command.setDoneeId(getPersonId());

        Response<Void> response = publishAsync(command).get();

        if (!response.success()) {
            foodPreferences.setError(response.getError());
        } else {
            foodPreferences = new FoodPreferences();
            foodPreferences.setSuccess(true);
        }
        
        return getFoodPreferencesView(foodPreferences);
    }

    @PutMapping("/FoodPreferences")
    public String modifyPreferences(
            @ModelAttribute FoodPreferences foodPreferences, 
            RedirectAttributes redirectAttributes) throws ExecutionException, InterruptedException {

        SelectPreferencesCommand command = new SelectPreferencesCommand();
        command.setDoneeId(getPersonId());

        Response<Void> response = publishAsync(command).get();

        // CORREÇÃO: Usar RedirectAttributes pois haverá um redirecionamento
        if (!response.success()) {
            redirectAttributes.addFlashAttribute("IsError", true);
            redirectAttributes.addFlashAttribute("ErrorMessage", response.getError().getMessage());
        } else {
            redirectAttributes.addFlashAttribute("Success", "Food Preferences Modified Successfully!");
        }
        
        return "redirect:/";
    }
}