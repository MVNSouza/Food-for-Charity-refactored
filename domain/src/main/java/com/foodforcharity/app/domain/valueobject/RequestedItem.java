package com.foodforcharity.app.domain.valueobject;

import com.foodforcharity.app.domain.entity.Food;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class RequestedItem {
    
    private final Food food;
    private final Integer quantity;

    public int calculateTotalOriginalPrice() {
        return food.getPrice() * quantity;
    }
    
    public int calculateTotalMeals() {
        return food.getMealForNPeople() * quantity;
    }

    public void deductFromStock() {
        food.setQuantityAvailable(food.getQuantityAvailable() - quantity);
    }
}