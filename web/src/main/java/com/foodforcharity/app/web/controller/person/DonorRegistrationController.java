package com.foodforcharity.app.web.controller.person;

import com.foodforcharity.app.domain.response.Response;
import com.foodforcharity.app.mediator.Mediator;
import com.foodforcharity.app.usecase.account.donorregisteration.DonorRegisterationCommand;
import com.foodforcharity.app.web.controller.AbstractController;
import com.foodforcharity.app.web.model.DonorRegisterRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.util.concurrent.ExecutionException;

import static com.foodforcharity.app.web.model.Request.withSuccess;

@Controller
public class DonorRegistrationController extends AbstractController {

    @Autowired
    public DonorRegistrationController(Mediator mediator) {
        super(mediator);
    }

    @GetMapping("/donor-register")
    public String getDonorRegisterView(DonorRegisterRequest request) {
        return "donor-register";
    }

    @PostMapping("/donor-register")
    public String registerDonor(
            @Valid DonorRegisterRequest request, 
            BindingResult result, 
            RedirectAttributes redirectAttributes) throws ExecutionException, InterruptedException {

        if (!request.getConfirmPassword().equals(request.getPassword())) {
            result.addError(new ObjectError("confirmPassword", "Passwords don't match"));
            return "donor-register";
        }

        if (result.hasErrors()) {
            return "donor-register";
        }

        DonorRegisterationCommand command = new DonorRegisterationCommand(request.getName(), request.getPassword(),
                request.getEmail(), request.getPhoneNumber(), request.getCity(), request.getCountry(),
                request.getAddress());

        Response<Void> response = publishAsync(command).get();

        if (response.hasError()) {
            request.setError(response.getError());
            return "donor-register";
        }

        redirectAttributes.addFlashAttribute("donorRegisterRequest", withSuccess(new DonorRegisterRequest()));
        return "redirect:/donor-register";
    }
}