package com.foodforcharity.app.usecase.foodreservation.createrequest;

import com.foodforcharity.app.domain.constant.Error;
import com.foodforcharity.app.domain.entity.*;
import com.foodforcharity.app.domain.response.Response;
import com.foodforcharity.app.domain.service.DoneeService;
import com.foodforcharity.app.domain.service.DonorService;
import com.foodforcharity.app.domain.service.FoodService;
import com.foodforcharity.app.domain.service.RequestService;
import com.foodforcharity.app.domain.service.DonationRequestService; // Import do novo serviço
import com.foodforcharity.app.domain.valueobject.RequestedItem;
import com.foodforcharity.app.mediator.CommandHandler;
import com.foodforcharity.app.usecase.foodreservation.createrequest.CreateRequestCommand.FoodQuantityPair;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CreateRequestCommandHandler implements CommandHandler<CreateRequestCommand, Response<Void>> {

    private static final Logger log = LoggerFactory.getLogger(CreateRequestCommandHandler.class);

    private final FoodService foodService;
    private final DonorService donorService;
    private final DoneeService doneeService;
    private final RequestService requestService;
    private final DonationRequestService donationRequestService; // Declaração da dependência

    @Autowired
    public CreateRequestCommandHandler(FoodService foodService, 
                                       DonorService donorService, 
                                       DoneeService doneeService, 
                                       RequestService requestService,
                                       DonationRequestService donationRequestService) { // Injeção no construtor
        this.foodService = foodService;
        this.donorService = donorService;
        this.doneeService = doneeService;
        this.requestService = requestService;
        this.donationRequestService = donationRequestService;
    }

    @Override
    @Transactional
    public Response<Void> handle(CreateRequestCommand command) {
        try {
            // 1 - Buscar e validar Donee
            Optional<Donee> dbDonee = doneeService.findById(command.doneeId);
            if (dbDonee.isEmpty()) return Response.of(Error.DoneeDoesNotExist);
            
            Donee donee = dbDonee.get();
            if (!donee.isEligibleForRequests()) return Response.of(Error.IneligibleDoneeStatus);

            // 2 - Buscar e validar Donor
            Optional<Donor> dbDonor = donorService.findById(command.donorId);
            if (dbDonor.isEmpty()) return Response.of(Error.DonorDoesNotExist);
            
            Donor donor = dbDonor.get();
            if (!donor.isEligibleForDonations()) return Response.of(Error.IneligibleDonorStatus);

            // 3 - Validar alimentos e montar a lista de itens solicitados
            List<RequestedItem> requestedItems = new ArrayList<>();
            for (FoodQuantityPair pair : command.foodQuantityPairs) {
                if (pair.quantity < 1) return Response.of(Error.InvalidQuantityRequested);

                Optional<Food> optFood = donor.getFoods().stream()
                                              .filter(f -> f.getId() == pair.foodId)
                                              .findFirst();

                if (optFood.isEmpty()) return Response.of(Error.FoodDoesNotExist);
                
                Food food = optFood.get();
                if (food.getQuantityAvailable() < pair.quantity) return Response.of(Error.FoodShortage);

                requestedItems.add(new RequestedItem(food, pair.quantity));
            }

            // 4 - Validar capacidade total de refeições do Donatário
            int totalMealsRequested = requestedItems.stream()
                                                    .mapToInt(RequestedItem::calculateTotalMeals)
                                                    .sum();

            if (!donee.canRequestMore(totalMealsRequested)) {
                return Response.of(Error.QuanityAllowanceExceeded);
            }

            // 5 - Criar Request usando o novo Serviço de Domínio
            Request request = donationRequestService.generateRequest(donor, donee, requestedItems);
            
            // Atualizar os estoques e quantidades
            requestedItems.forEach(RequestedItem::deductFromStock);
            donee.incrementQuantityRequested(totalMealsRequested);

            // 6 - Salvar nos repositórios
            doneeService.save(donee);
            requestService.save(request);
            
            // Salvar o status atualizado de cada alimento
            requestedItems.forEach(item -> foodService.save(item.getFood()));

            return Response.EmptyResponse();

        } catch (Exception e) {
            log.error("Erro inesperado ao criar requisição de alimento. Command: {}", command, e);
            return Response.of(Error.UnknownError);
        }
    }
}