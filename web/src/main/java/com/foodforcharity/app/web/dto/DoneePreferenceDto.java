package com.foodforcharity.app.web.dto;

import com.foodforcharity.app.domain.constant.Allergen;
import com.foodforcharity.app.domain.constant.Cuisine;
import com.foodforcharity.app.domain.constant.MealType;
import com.foodforcharity.app.domain.constant.SpiceLevel;
import com.foodforcharity.app.domain.entity.Donee;
import com.foodforcharity.app.domain.entity.DoneePriceRange;
import com.foodforcharity.app.domain.entity.DoneeSpiceRange;
import lombok.Data;

import javax.validation.constraints.NotNull;
import java.util.List;

@Data
public class DoneePreferenceDto {

    private PriceRange priceRange;
    private SpiceRange spiceRange;
    private List<Allergen> allergens;
    private List<Cuisine> cuisines;
    private List<MealType> mealTypes;

    public DoneePreferenceDto(Donee donee) {
        if (donee != null && donee.getPreferences() != null) {
            
            // 1. Preferências alimentares
            if (donee.getPreferences().getDietaryPreferences() != null) {
                this.allergens = donee.getPreferences().getDietaryPreferences().getAllergens();
                this.cuisines = donee.getPreferences().getDietaryPreferences().getCuisines();
                this.mealTypes = donee.getPreferences().getDietaryPreferences().getMealTypes();
            }
            
            // 2. PriceRange acessado através de getPreferences()
            if (donee.getPreferences().getPriceRange() != null) {
                this.priceRange = new PriceRange(donee.getPreferences().getPriceRange());
            }
            
            // 3. SpiceRange acessado através de getPreferences()
            if (donee.getPreferences().getSpiceRange() != null) {
                this.spiceRange = new SpiceRange(donee.getPreferences().getSpiceRange());
            }
        }
    }

    @Data
    public static class PriceRange {
        @NotNull
        private Integer endPrice;

        @NotNull
        private Integer startPrice;

        public PriceRange(DoneePriceRange priceRange) {
            this.startPrice = priceRange.getStartPrice();
            this.endPrice = priceRange.getEndPrice();
        }
    }

    @Data
    public static class SpiceRange {
        @NotNull
        private SpiceLevel startLevel;

        @NotNull
        private SpiceLevel endLevel;

        public SpiceRange(DoneeSpiceRange spiceRange) {
            this.startLevel = spiceRange.getStartLevel();
            this.endLevel = spiceRange.getEndLevel();
        }
    }
}