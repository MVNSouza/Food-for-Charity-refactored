package com.foodforcharity.app.web.dto;

import com.fasterxml.jackson.annotation.JsonUnwrapped;
import com.foodforcharity.app.domain.constant.Allergen;
import com.foodforcharity.app.domain.constant.Cuisine;
import com.foodforcharity.app.domain.constant.MealType;
import com.foodforcharity.app.domain.constant.SpiceLevel;
import lombok.Builder;
import lombok.Value;

import java.util.Set;

@Value
@Builder
public class FoodDto {
    
    @JsonUnwrapped
    private BasicInfo basicInfo;
    
    @JsonUnwrapped
    private CommercialInfo commercialInfo;
    
    @JsonUnwrapped
    private Characteristics characteristics;

    // --- Classes Aninhadas Públicas ---

    @Value
    @Builder
    public static class BasicInfo {
        private long id;
        private String foodName;
        private String descriptionText;
    }

    @Value
    @Builder
    public static class CommercialInfo {
        private Double price;
        private Integer quantityAvailable;
        private Integer mealForNPeople;
    }

    @Value
    @Builder
    public static class Characteristics {
        private SpiceLevel spiceLevel;
        private Set<Allergen> allergens;
        private Cuisine cuisine;
        private MealType mealType;
    }
}