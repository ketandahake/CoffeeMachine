package composers;

import ingredients.Ingredient;

import java.util.Map;

public class HotCoffeeComposer extends Composer {
    public HotCoffeeComposer(Map<Ingredient, Integer> ingredientQuantity){
        super(ingredientQuantity);
    }
}
