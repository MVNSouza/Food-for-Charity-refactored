package com.foodforcharity.app.domain.valueobject;

import com.foodforcharity.app.domain.constant.Allergen;
import com.foodforcharity.app.domain.constant.Cuisine;
import com.foodforcharity.app.domain.constant.MealType;
import lombok.Data;

import javax.persistence.ElementCollection;
import javax.persistence.Embeddable;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import java.util.HashSet;
import java.util.Set;

@Data
@Embeddable
public class DietaryPreferences {

    @Enumerated(EnumType.STRING)
    @ElementCollection(targetClass = Allergen.class, fetch = FetchType.EAGER)
    private Set<Allergen> allergens = new HashSet<>();

    @Enumerated(EnumType.STRING)
    @ElementCollection(targetClass = Cuisine.class, fetch = FetchType.EAGER)
    private Set<Cuisine> cuisines = new HashSet<>();

    @Enumerated(EnumType.STRING)
    @ElementCollection(targetClass = MealType.class, fetch = FetchType.EAGER)
    private Set<MealType> mealTypes = new HashSet<>();

    // Trazemos os métodos para cá, onde eles realmente pertencem!
    public boolean addAllergen(Allergen allergen) {
        return allergens.add(allergen);
    }

    public boolean removeAllergen(Allergen allergen) {
        return allergens.remove(allergen);
    }

    public boolean addCuisine(Cuisine cuisine) {
        return cuisines.add(cuisine);
    }

    public boolean removeCuisine(Cuisine cuisine) {
        return cuisines.remove(cuisine);
    }

    public boolean addMealType(MealType mealType) {
        return mealTypes.add(mealType);
    }

    public boolean removeMealType(MealType mealType) {
        return mealTypes.remove(mealType);
    }
}