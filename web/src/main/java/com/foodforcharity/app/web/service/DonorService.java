package com.foodforcharity.app.web.service;

import com.foodforcharity.app.domain.entity.Donor;
import com.foodforcharity.app.domain.entity.Food;
import com.foodforcharity.app.domain.response.Response;
import com.foodforcharity.app.mediator.Mediator;
import com.foodforcharity.app.usecase.account.getdonor.GetDonorCommand;
import com.foodforcharity.app.usecase.profile.addmenu.AddMenuCommand;
import com.foodforcharity.app.usecase.profile.deletemenuitem.DeleteMenuItemCommand;
import com.foodforcharity.app.usecase.profile.getmenuitem.GetMenuItemCommand;
import com.foodforcharity.app.usecase.profile.modifymenuitem.ModifyMenuItemCommand;
import com.foodforcharity.app.web.dto.DonorDto;
import com.foodforcharity.app.web.dto.FoodDto;
import com.foodforcharity.app.web.mapper.DonorMapper;
import com.foodforcharity.app.web.mapper.FoodDtoMapper; // Import do novo Mapper
import com.foodforcharity.app.web.model.MenuModel;
import org.springframework.stereotype.Service;

import java.util.concurrent.ExecutionException;

@Service
public class DonorService {

    private final Mediator mediator;
    private final DonorMapper donorMapper;
    private final FoodDtoMapper foodDtoMapper; // Nova dependência

    // Injeção de dependência atualizada para incluir o FoodDtoMapper
    public DonorService(Mediator mediator, DonorMapper donorMapper, FoodDtoMapper foodDtoMapper) {
        this.mediator = mediator;
        this.donorMapper = donorMapper;
        this.foodDtoMapper = foodDtoMapper;
    }

    public DonorDto getDonorProfile(Long personId) throws ExecutionException, InterruptedException {
        GetDonorCommand command = new GetDonorCommand(personId);
        Response<Donor> response = mediator.publishAsync(command).get();
        
        if (response.hasError()) {
            throw new RuntimeException(response.getError().getMessage()); 
        }
        
        return donorMapper.toDto(response.getResponse());
    }

    public FoodDto getMenuItem(Long personId, Long foodId) throws ExecutionException, InterruptedException {
        GetMenuItemCommand command = new GetMenuItemCommand(personId, foodId);
        Response<Food> response = mediator.publishAsync(command).get();
        
        if (response.hasError()) {
            throw new RuntimeException(response.getError().getMessage());
        }
        
        // CORREÇÃO: Utilizando a instância do Mapper injetada pelo Spring
        return foodDtoMapper.toDto(response.getResponse());
    }

    public void addMenuItem(Long personId, MenuModel menuModel) throws ExecutionException, InterruptedException {
        AddMenuCommand command = new AddMenuCommand(personId, menuModel.getFoodName(),
                menuModel.getDescriptionText(), menuModel.getOriginalPrice(), menuModel.getMealForNPeople(),
                menuModel.getQuantityAvailable(), menuModel.getSpiceLevel(), menuModel.getMealTypes(),
                menuModel.getCuisines(), menuModel.getAllergen());

        Response<Void> response = mediator.publishAsync(command).get();
        if (response.hasError()) {
            throw new RuntimeException(response.getError().getMessage());
        }
    }

    public void updateMenuItem(Long personId, long itemId) throws ExecutionException, InterruptedException {
        ModifyMenuItemCommand command = new ModifyMenuItemCommand(personId, itemId);
        Response<Void> response = mediator.publishAsync(command).get();
        if (response.hasError()) {
            throw new RuntimeException(response.getError().getMessage());
        }
    }

    public void deleteMenuItem(Long personId, long foodId) throws ExecutionException, InterruptedException {
        DeleteMenuItemCommand command = new DeleteMenuItemCommand(personId, foodId);
        Response<Void> response = mediator.publishAsync(command).get();
        if (response.hasError()) {
            throw new RuntimeException(response.getError().getMessage());
        }
    }
}