package com.foodforcharity.app.web.controller.person;

import com.foodforcharity.app.domain.response.Response;
import com.foodforcharity.app.mediator.Mediator;
import com.foodforcharity.app.usecase.account.doneeregisteration.DoneeRegisterationCommand;
import com.foodforcharity.app.web.controller.AbstractController;
import com.foodforcharity.app.web.model.DoneeRegisterRequest;
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
public class DoneeRegistrationController extends AbstractController {

    @Autowired
    public DoneeRegistrationController(Mediator mediator) {
        super(mediator);
    }

    @GetMapping("/donee-register")
    public String getDoneeRegisterView(DoneeRegisterRequest request) {
        return "donee-register";
    }

    @PostMapping("/donee-register")
    public String registerDonee(
            @Valid DoneeRegisterRequest request, 
            BindingResult result, 
            RedirectAttributes redirectAttributes) throws ExecutionException, InterruptedException {

        if (!request.getConfirmPassword().equals(request.getPassword())) {
            result.addError(new ObjectError("confirmPassword", "Passwords don't match"));
            return "donee-register";
        }

        if (result.hasErrors()) {
            return "donee-register";
        }

        DoneeRegisterationCommand command = new DoneeRegisterationCommand(request.getName(), request.getPassword(),
                request.getEmail(), request.getPhoneNumber(), request.getCity(), request.getCountry(),
                request.getAddress(), request.getDoneeType(), request.getNumberOfMembers());

        Response<Void> response = publishAsync(command).get();

        if (response.hasError()) {
            request.setError(response.getError());
            return "donee-register";
        }

        redirectAttributes.addFlashAttribute("doneeRegisterRequest", withSuccess(new DoneeRegisterRequest()));
        return "redirect:/donee-register";
    }
}