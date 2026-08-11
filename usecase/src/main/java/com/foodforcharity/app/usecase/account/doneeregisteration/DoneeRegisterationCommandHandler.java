package com.foodforcharity.app.usecase.account.doneeregisteration;

import com.foodforcharity.app.domain.constant.DoneeStatus;
import com.foodforcharity.app.domain.constant.Error;
import com.foodforcharity.app.domain.entity.Donee;
import com.foodforcharity.app.domain.response.Response;
import com.foodforcharity.app.domain.service.DoneeService;
import com.foodforcharity.app.domain.service.PersonService;
import com.foodforcharity.app.domain.valueobject.Address; // <-- NOVO IMPORT
import com.foodforcharity.app.mediator.CommandHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * CommandHandler class for RegisterCommand
 **/
@Service
public class DoneeRegisterationCommandHandler implements CommandHandler<DoneeRegisterationCommand, Response<Void>> {
    
    private static final Logger log = LoggerFactory.getLogger(DoneeRegisterationCommandHandler.class);
    
    private final PersonService personService;
    private final DoneeService doneeService;

    /**
     * Public Constructor
     *
     * @param personService
     * @param doneeService
     */
    @Autowired
    public DoneeRegisterationCommandHandler(PersonService personService, DoneeService doneeService) {
        this.personService = personService;
        this.doneeService = doneeService;
    }

    @Override
    public Response<Void> handle(DoneeRegisterationCommand command) {

        if (!isValid(command.getEmail())) {
            return Response.of(Error.InvalidEmail);
        }

        if (personService.findByUsername(command.getEmail()).isPresent()) {
            return Response.of(Error.EmailAlreadyExist);
        }

        try {
            int minimumMemberCount = 1;
            if (command.getMemberCount() < minimumMemberCount) {
                return Response.of(Error.InvalidMemberCount);
            }

            Donee donee = new Donee();
            donee.setUsername(command.getEmail());
            donee.setPassword(command.getPassword()); // for now

            donee.setDoneeName(command.getName());
            donee.setEmail(command.getEmail());
            donee.setPhoneNumber(command.getPhoneNumber());

            // --- CORREÇÃO DO ENDEREÇO AQUI ---
            Address address = new Address();
            address.setAddressDescription(command.getAddress());
            address.setCity(command.getCity());
            address.setCountry(command.getCountry());
            donee.setAddress(address);
            // ---------------------------------

            donee.setDoneeStatus(DoneeStatus.Initial);
            donee.setQuantityRequested(0); // is this required over here??
            donee.setDoneeType(command.getDoneeType());
            donee.setMemberCount(command.getMemberCount());

            doneeService.save(donee);

        } catch (Exception e) {
            log.error("Erro inesperado ao registrar novo Donee.", e);
            return Response.of(Error.UnknownError);
        }

        return Response.EmptyResponse();
    }

    public boolean isValid(String email) {
        String regex = "^[\\w-_\\.+]*[\\w-_\\.]\\@([\\w]+\\.)+[\\w]+[\\w]$";
        return email.matches(regex);
    }
}