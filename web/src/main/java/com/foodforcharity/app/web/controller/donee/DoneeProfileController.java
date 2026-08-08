package com.foodforcharity.app.web.controller.donee;

import com.foodforcharity.app.domain.entity.Donee;
import com.foodforcharity.app.domain.response.Response;
import com.foodforcharity.app.mediator.Mediator;
import com.foodforcharity.app.usecase.account.getdonee.GetDoneeCommand;
import com.foodforcharity.app.web.controller.AbstractController;
import com.foodforcharity.app.web.dto.DoneeDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.concurrent.ExecutionException;

@Controller
@RequestMapping("/donee/profile")
@PreAuthorize("hasAuthority('Donee')")
public class DoneeProfileController extends AbstractController {

    @Autowired
    public DoneeProfileController(Mediator mediator) {
        super(mediator);
    }

    @GetMapping
    public String getDoneeProfile(Model model) throws ExecutionException, InterruptedException {
        GetDoneeCommand command = new GetDoneeCommand(getPersonId());

        Response<Donee> response = publishAsync(command).get();

        if (response.hasError()) {
            model.addAttribute("error", response.getError());
            return "donee/view-profile"; // Interrompe fluxo em caso de erro
        }

        // DICA: Em uma refatoração futura, faça o Mediator retornar DoneeDto diretamente
        DoneeDto donee = new DoneeDto(response.getResponse());
        model.addAttribute("donee", donee);

        return "donee/view-profile";
    }
}