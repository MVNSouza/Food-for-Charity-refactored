package com.foodforcharity.app.web.controller.person;

import com.foodforcharity.app.domain.response.Response;
import com.foodforcharity.app.mediator.Mediator;
import com.foodforcharity.app.usecase.account.changepassword.ChangePasswordCommand;
import com.foodforcharity.app.web.controller.AbstractController;
import com.foodforcharity.app.web.model.ChangePasswordRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.util.concurrent.ExecutionException;

import static com.foodforcharity.app.web.model.Request.withSuccess;

@Controller
public class PasswordController extends AbstractController {

    @Autowired
    public PasswordController(Mediator mediator) {
        super(mediator);
    }

    @GetMapping("/change-password")
    public String getChangePasswordView(ChangePasswordRequest request) {
        return "change-password";
    }

    @PostMapping("/change-password")
    public String changePassword(
            @Valid ChangePasswordRequest request, 
            BindingResult result, 
            RedirectAttributes redirectAttributes) throws ExecutionException, InterruptedException {

        if (!request.getConfirmNewPassword().equals(request.getNewPassword())) {
            result.rejectValue("confirmNewPassword", "error.changePasswordRequest", "Passwords don't match");
            return "change-password";
        }

        if (result.hasErrors()) {
            return "change-password";
        }

        ChangePasswordCommand command = new ChangePasswordCommand(getPersonId(), request.getPassword(),
                request.getNewPassword());

        Response<Void> response = publishAsync(command).get();

        if (response.hasError()) {
            request.setError(response.getError());
            return "change-password"; // Retorna view com erros
        }

        // CORREÇÃO: Padrão PRG (Post-Redirect-Get)
        redirectAttributes.addFlashAttribute("changePasswordRequest", withSuccess(new ChangePasswordRequest()));
        return "redirect:/change-password";
    }
}