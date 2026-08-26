package com.foodforcharity.app.usecase.profile.selectpreferences;

import com.foodforcharity.app.domain.constant.Allergen;
import com.foodforcharity.app.domain.constant.Cuisine;
import com.foodforcharity.app.domain.constant.MealType;
import com.foodforcharity.app.domain.constant.SpiceLevel;
import com.foodforcharity.app.domain.response.Response;
import com.foodforcharity.app.mediator.Command;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.Value;

import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
public class SelectPreferencesCommand implements Command<Response<Void>> {
    
    private long doneeId;
    private Range<SpiceLevel> spiceRange;
    private Range<Integer> priceRange;
    
    // Inicializar as listas evita NullPointerException caso o payload venha sem esses campos
    private List<Allergen> allergens = new ArrayList<>();
    private List<Cuisine> cuisines = new ArrayList<>();
    private List<MealType> mealTypes = new ArrayList<>();

    /**
     * A classe Range precisa ser estática. 
     * Como ela usa @Value (que cria objetos imutáveis), ser não-estática faria com que 
     * cada instância de Range guardasse uma referência oculta para a instância do Command, 
     * o que pode causar problemas de serialização com o Jackson/Gson.
     */
    @Value
    public static class Range<T> {
        T start;
        T stop;
    }

    // --- Validações de Domínio Trazidas do Handler ---

    public boolean isPriceRangeValid() {
        if (priceRange == null || priceRange.getStart() == null || priceRange.getStop() == null) {
            return false;
        }
        return priceRange.getStart() >= 0 && priceRange.getStop() >= priceRange.getStart();
    }

    public boolean isSpiceRangeValid() {
        if (spiceRange == null || spiceRange.getStart() == null || spiceRange.getStop() == null) {
            return false;
        }
        return spiceRange.getStop().ordinal() >= spiceRange.getStart().ordinal();
    }
}