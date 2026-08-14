package com.foodforcharity.app.web.controller.donee;

import com.foodforcharity.app.domain.entity.Donee;
import com.foodforcharity.app.domain.response.Response;
import com.foodforcharity.app.mediator.Mediator;
import com.foodforcharity.app.usecase.account.getdonee.GetDoneeCommand;
import com.foodforcharity.app.web.controller.AbstractController;
import com.foodforcharity.app.web.dto.DoneeDto;
import com.foodforcharity.app.web.mapper.DoneeMapper; // 1. Importar o Mapper
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

    
    private final DoneeMapper doneeMapper;

    @Autowired
    public DoneeProfileController(Mediator mediator, DoneeMapper doneeMapper) {
        super(mediator);
        
        this.doneeMapper = doneeMapper; 
    }

    @GetMapping
    public String getDoneeProfile(Model model) throws ExecutionException, InterruptedException {
        GetDoneeCommand command = new GetDoneeCommand(getPersonId());

        Response<Donee> response = publishAsync(command).get();

        if (response.hasError()) {
            model.addAttribute("error", response.getError());
            return "donee/view-profile"; 
        }

      
        DoneeDto donee = doneeMapper.toDto(response.getResponse());
        model.addAttribute("donee", donee);

        return "donee/view-profile";
    }
}