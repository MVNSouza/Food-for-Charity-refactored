package com.foodforcharity.app.domain.valueobject;

import com.foodforcharity.app.domain.constant.Allergen;
import com.foodforcharity.app.domain.constant.Cuisine;
import com.foodforcharity.app.domain.constant.MealType;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Embeddable;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Embeddable
public class DietaryPreferences {

    private List<Cuisine> cuisines = new ArrayList<>();
    private List<Allergen> allergens = new ArrayList<>();
    private List<MealType> mealTypes = new ArrayList<>();

    public DietaryPreferences() {
    }

    public void addCuisine(Cuisine cuisine) {
        if (cuisine != null && !this.cuisines.contains(cuisine)) {
            this.cuisines.add(cuisine);
        }
    }

    public void addAllergen(Allergen allergen) {
        if (allergen != null && !this.allergens.contains(allergen)) {
            this.allergens.add(allergen);
        }
    }

    public void addMealType(MealType mealType) {
        if (mealType != null && !this.mealTypes.contains(mealType)) {
            this.mealTypes.add(mealType);
        }
    }

    public void clearAll() {
        if (this.allergens != null) this.allergens.clear();
        if (this.cuisines != null) this.cuisines.clear();
        if (this.mealTypes != null) this.mealTypes.clear();
    }
}