package composers;

import ingredients.Ingredient;

import java.util.Map;

public class HotTeaComposer extends Composer{
    public HotTeaComposer(Map<Ingredient, Integer> ingredientQuantity){
        super(ingredientQuantity);
    }
}
