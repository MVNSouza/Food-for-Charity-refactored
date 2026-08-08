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
import com.foodforcharity.app.web.model.MenuModel;
import org.springframework.stereotype.Service;

import java.util.concurrent.ExecutionException;

@Service
public class DonorService {

    private final Mediator mediator;

    // Injeção de dependência via construtor
    public DonorService(Mediator mediator) {
        this.mediator = mediator;
    }

    public DonorDto getDonorProfile(Long personId) throws ExecutionException, InterruptedException {
        GetDonorCommand command = new GetDonorCommand(personId);
        Response<Donor> response = mediator.publishAsync(command).get();
        
        if (response.hasError()) {
            throw new RuntimeException(response.getError().getMessage()); 
        }
        return new DonorDto(response.getResponse());
    }

    public FoodDto getMenuItem(Long personId, Long foodId) throws ExecutionException, InterruptedException {
        GetMenuItemCommand command = new GetMenuItemCommand(personId, foodId);
        Response<Food> response = mediator.publishAsync(command).get();
        
        if (response.hasError()) {
            throw new RuntimeException(response.getError().getMessage());
        }
        return new FoodDto(response.getResponse());
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