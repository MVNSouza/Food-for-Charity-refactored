package com.foodforcharity.app.usecase.profile.selectpreferences;

import java.util.Optional;
import com.foodforcharity.app.domain.entity.Donee;
import com.foodforcharity.app.domain.response.Response;
import com.foodforcharity.app.domain.service.DoneeService;
import com.foodforcharity.app.mediator.CommandHandler;
import org.springframework.stereotype.Service;
import com.foodforcharity.app.domain.constant.Error;

@Service
public class SelectPreferencesCommandHandler implements CommandHandler<SelectPreferencesCommand, Response<Void>> {
    
    private final DoneeService doneeService;

    public SelectPreferencesCommandHandler(DoneeService doneeService) {
        this.doneeService = doneeService;
    }

    @Override
    public Response<Void> handle(SelectPreferencesCommand command) {
        try {
            Optional<Donee> dbDonee = doneeService.findById(command.getDoneeId());
            if (dbDonee.isEmpty()) {
                return Response.of(Error.DoneeDoesNotExist);
            }
            
            Donee donee = dbDonee.get();
            
            // Validações de entrada antes de aplicar à entidade
            if (!command.isPriceRangeValid()) {
                return Response.of(Error.InvalidPriceRange);
            }
            if (!command.isSpiceRangeValid()) {
                return Response.of(Error.InvalidSpiceRange);
            }

            // DELEGAÇÃO: O Handler desempacota os dados e a entidade se atualiza.
            // Dessa forma, a entidade Donee não sabe o que é um "Command",
            // mantendo a regra de dependência da Clean Architecture intacta!
            donee.updatePreferences(
                command.getCuisines(),
                command.getAllergens(),
                command.getMealTypes(),
                command.getPriceRange().getStart(),
                command.getPriceRange().getStop(),
                command.getSpiceRange().getStart(),
                command.getSpiceRange().getStop()
            );

            doneeService.save(donee);
            return Response.EmptyResponse();

        } catch (Exception e) {
            return Response.of(Error.UnknownError);
        }
    }
}