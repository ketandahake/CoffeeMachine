package composers;

import ingredients.Ingredient;

import java.util.Map;

public class GreenTeaComposer extends Composer {
    public GreenTeaComposer(Map<Ingredient, Integer> ingredientQuantity){
        super(ingredientQuantity);
    }
}
