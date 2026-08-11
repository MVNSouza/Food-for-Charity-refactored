package com.foodforcharity.app.usecase.account.donorregisteration;

import com.foodforcharity.app.domain.constant.DonorStatus;
import com.foodforcharity.app.domain.constant.Error;
import com.foodforcharity.app.domain.entity.Donor;
import com.foodforcharity.app.domain.response.Response;
import com.foodforcharity.app.domain.service.DonorService;
import com.foodforcharity.app.domain.service.PersonService;
// IMPORT NOVO AQUI:
import com.foodforcharity.app.domain.valueobject.Address; 
import com.foodforcharity.app.mediator.CommandHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * CommandHandler class for RegisterCommand
 **/
@Service
public class DonorRegisterationCommandHandler implements CommandHandler<DonorRegisterationCommand, Response<Void>> {
    private final PersonService personService;
    private final DonorService donorService;

    /**
     * Public Constructor
     *
     * @param personService
     * @param donorService
     */
    @Autowired
    public DonorRegisterationCommandHandler(PersonService personService,
                                            DonorService donorService) {
        this.personService = personService;
        this.donorService = donorService;
    }

    @Override
    public Response<Void> handle(DonorRegisterationCommand command) {

        if (!isValid(command.getEmail())) {
            return Response.of(Error.InvalidEmail);
        }

        if (personService.findByUsername(command.getEmail()).isPresent()) {
            return Response.of(Error.EmailAlreadyExist);
        }

        try {
            Donor donor = new Donor();
            donor.setUsername(command.getEmail());
            donor.setPassword(command.getPassword()); // for now

            donor.setDonorName(command.getName());
            
            // --- CORREÇÃO: Usando a nova classe Address ---
            Address address = new Address();
            address.setAddressDescription(command.getAddress());
            address.setCity(command.getCity());
            address.setCountry(command.getCountry());
            donor.setAddress(address); // Atribuindo o endereço ao Doador
            // ----------------------------------------------

            donor.setEmail(command.getEmail());
            donor.setPhoneNumber(command.getPhoneNumber());
            donor.setRating(0);
            donor.setNumberOfRating(0);
            donor.setDiscountApplied(0);

            donor.setDonorStatus(DonorStatus.Initial);

            donorService.save(donor);
        } catch (Exception e) {
            return Response.of(Error.UnknownError);
        }

        return Response.EmptyResponse();
    }

    public boolean isValid(String email) {
        String regex = "^[\\w-_\\.+]*[\\w-_\\.]\\@([\\w]+\\.)+[\\w]+[\\w]$";
        return email.matches(regex);
    }

}