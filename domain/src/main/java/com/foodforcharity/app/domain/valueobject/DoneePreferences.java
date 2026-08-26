package com.foodforcharity.app.domain.valueobject;

import com.foodforcharity.app.domain.constant.Allergen;
import com.foodforcharity.app.domain.constant.Cuisine;
import com.foodforcharity.app.domain.constant.MealType;
import com.foodforcharity.app.domain.constant.SpiceLevel;
import com.foodforcharity.app.domain.entity.Donee;
import com.foodforcharity.app.domain.entity.DoneePriceRange;
import com.foodforcharity.app.domain.entity.DoneeSpiceRange;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Getter
@Setter
@Embeddable
public class DoneePreferences {

    @Embedded
    private DietaryPreferences dietaryPreferences = new DietaryPreferences();

    @OneToOne(mappedBy = "donee", fetch = FetchType.EAGER, cascade = CascadeType.ALL, orphanRemoval = true)
    private DoneePriceRange priceRange;

    @OneToOne(mappedBy = "donee", fetch = FetchType.EAGER, cascade = CascadeType.ALL, orphanRemoval = true)
    private DoneeSpiceRange spiceRange;

    public void update(Donee donee, List<Cuisine> cuisines, List<Allergen> allergens, List<MealType> mealTypes, Integer startPrice, Integer endPrice, SpiceLevel startSpice, SpiceLevel endSpice) {
        
        this.dietaryPreferences.clearAll();
        if (cuisines != null) cuisines.forEach(this.dietaryPreferences::addCuisine);
        if (allergens != null) allergens.forEach(this.dietaryPreferences::addAllergen);
        if (mealTypes != null) mealTypes.forEach(this.dietaryPreferences::addMealType);

        if (this.priceRange == null) {
            this.priceRange = new DoneePriceRange();
            this.priceRange.setDonee(donee);
        }
        this.priceRange.setStartPrice(startPrice);
        this.priceRange.setEndPrice(endPrice);

        if (this.spiceRange == null) {
            this.spiceRange = new DoneeSpiceRange();
            this.spiceRange.setDonee(donee);
        }
        this.spiceRange.setStartLevel(startSpice);
        this.spiceRange.setEndLevel(endSpice);
    }
}