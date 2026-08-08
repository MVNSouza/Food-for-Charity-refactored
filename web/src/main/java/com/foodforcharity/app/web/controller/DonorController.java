package com.foodforcharity.app.web.controller;

import com.foodforcharity.app.mediator.Mediator;
import com.foodforcharity.app.web.dto.DonorDto;
import com.foodforcharity.app.web.dto.FoodDto;
import com.foodforcharity.app.web.model.MenuModel;
import com.foodforcharity.app.web.service.DonorService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Optional;

import static com.foodforcharity.app.web.model.Request.withSuccess;

@Controller
@RequestMapping("/donor")
@PreAuthorize(value = "hasAuthority('Donor')")
public class DonorController extends AbstractController {

    private final DonorService donorService;

    // Injeção via construtor delegando o mediator para a superclasse
    public DonorController(Mediator mediator, DonorService donorService) {
        super(mediator);
        this.donorService = donorService;
    }

    @GetMapping(value = "/home")
    public String getDonorHomepageView(Model model) {
        try {
            DonorDto donor = donorService.getDonorProfile(getPersonId());
            model.addAttribute("donor", donor);
        } catch (Exception e) {
            model.addAttribute("error", e.getMessage());
        }
        return "donor/donor-homepage";
    }

    @GetMapping(value = "/menu")
    public String getMenu(MenuModel request, Model model) {
        try {
            DonorDto donor = donorService.getDonorProfile(getPersonId());
            model.addAttribute("menuModel", withSuccess(new MenuModel()));
            model.addAttribute("foods", donor.getFoods());
        } catch (Exception e) {
             // O erro é tratado internamente se não achar perfil
        }
        return "donor/menu-items";
    }

    @GetMapping(value = "/edit-menu")
    public String getEditMenu(MenuModel menuModel,
                              @RequestParam(value = "foodId", required = false) Optional<Long> foodId, 
                              Model model) {
        if (foodId.isPresent()) {
            try {
                FoodDto food = donorService.getMenuItem(getPersonId(), foodId.get());
                menuModel.setSuccess(true);
                model.addAttribute("menuModel", food);
            } catch (Exception e) {
                return "redirect:/";
            }
        } else {
            model.addAttribute("menuModel", new MenuModel());
        }
        return "donor/edit-menu";
    }

    @PostMapping(value = "/edit-menu")
    public String addMenuItem(@Valid MenuModel menuModel, BindingResult result, Model model) {
        if (result.hasErrors()) {
            return "edit-menu"; // View de edição
        }
        try {
            donorService.addMenuItem(getPersonId(), menuModel);
            model.addAttribute("success", withSuccess(menuModel));
            return "donor/menu";
        } catch (Exception e) {
            return "donor/menu";
        }
    }

    @PutMapping(value = "/edit-menu")
    public String updateMenuItem(@RequestParam(value = "itemId", required = true) long itemId,
                                 @Valid MenuModel menuModel, BindingResult result, Model model) {
        if (result.hasErrors()) {
            return "edit-menu";
        }
        try {
            donorService.updateMenuItem(getPersonId(), itemId);
            model.addAttribute("success", withSuccess(menuModel));
            return "menu"; 
        } catch (Exception e) {
            return "redirect:/"; // Corrigido o typo "reditrect:/" que tinha no original
        }
    }

    @DeleteMapping(value = "/delete-menu")
    public String deleteMenuItem(@RequestParam(value = "foodId", required = true) long foodId,
                                 @Valid MenuModel menuModel, Model model) {
        try {
            donorService.deleteMenuItem(getPersonId(), foodId);
            model.addAttribute("success", withSuccess(menuModel));
        } catch (Exception e) {
            // Em caso de erro o sistema não faz nada, conforme original
        }
        return "menu";
    }

    @GetMapping(value="/profile")
    public String getDonorProfile(Model model) {
        try {
            DonorDto donor = donorService.getDonorProfile(getPersonId());
            model.addAttribute("donor", donor);
        } catch (Exception e) {
            model.addAttribute("error", e.getMessage());
        }
        return "donor/view-profile";
    }
}