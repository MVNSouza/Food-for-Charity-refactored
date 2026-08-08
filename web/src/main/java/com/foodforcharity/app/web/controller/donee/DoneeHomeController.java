package com.foodforcharity.app.web.controller.donee;

import com.foodforcharity.app.domain.response.Response;
import com.foodforcharity.app.mediator.Mediator;
import com.foodforcharity.app.usecase.foodreservation.createrequest.CreateRequestCommand;
import com.foodforcharity.app.web.controller.AbstractController;
import com.foodforcharity.app.web.model.FoodRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.Optional;
import java.util.concurrent.ExecutionException;

import static com.foodforcharity.app.web.model.Request.withSuccess;

@Controller
@RequestMapping("/donee")
@PreAuthorize("hasAuthority('Donee')")
public class DoneeHomeController extends AbstractController {

    @Autowired
    public DoneeHomeController(Mediator mediator) {
        super(mediator);
    }

    @GetMapping("/home")
    public String getDoneeHomepageView() {
        // Send fooddtos to the homepage
        return "donee/donee-homepage";
    }

    @PostMapping("/home")
    public String makeFoodRequest(
            @RequestParam(value = "donorId", required = false) Optional<Long> donorId,
            FoodRequest request, 
            RedirectAttributes redirectAttributes) throws ExecutionException, InterruptedException {

        // CORREÇÃO: Tratamento seguro do Optional
        Long safeDonorId = donorId.orElse(null);
        CreateRequestCommand command = new CreateRequestCommand(getPersonId(), safeDonorId);

        Response<Void> response = publishAsync(command).get();

        if (response.hasError()) {
            request.setError(response.getError());
            // CORREÇÃO: Usando flash attributes para sobreviver ao redirect
            redirectAttributes.addFlashAttribute("errorRequest", request);
            return "redirect:/";
        }

        request.setSuccess(true);
        redirectAttributes.addFlashAttribute("success", withSuccess(request));

        return "redirect:/";
    }
}