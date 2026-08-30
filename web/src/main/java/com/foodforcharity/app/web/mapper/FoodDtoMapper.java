package com.foodforcharity.app.web.mapper;

import com.foodforcharity.app.domain.entity.Food;
import com.foodforcharity.app.web.dto.FoodDto;
import org.springframework.stereotype.Component;

@Component
public class FoodDtoMapper {

    /**
     * Converte a entidade de domínio Food para FoodDto.
     * Centraliza a regra de negócio de conversão (ex: divisão do preço).
     */
    public FoodDto toDto(Food food) {
        if (food == null) {
            return null;
        }

        return FoodDto.builder()
                .basicInfo(FoodDto.BasicInfo.builder()
                        .id(food.getId())
                        .foodName(food.getFoodName())
                        .descriptionText(food.getDescriptionText())
                        .build())
                .commercialInfo(FoodDto.CommercialInfo.builder()
                        .price(food.getPrice() != null ? food.getPrice() / 100.0 : null)
                        .quantityAvailable(food.getQuantityAvailable())
                        .mealForNPeople(food.getMealForNPeople())
                        .build())
                .characteristics(FoodDto.Characteristics.builder()
                        .spiceLevel(food.getSpiceLevel())
                        .allergens(food.getAllergens())
                        .cuisine(food.getCuisine())
                        .mealType(food.getMealType())
                        .build())
                .build();
    }
}