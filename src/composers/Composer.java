package composers;

import ingredients.Ingredient;

import java.util.Map;

//Composer encapsulates the ingredients or the recipe for a particular beverage
//Every new beverage needs to have a composer with all the required ingredients an their quantities
//Each composer for a new beverage should extend this composer class
public class Composer {
    Map<Ingredient, Integer> ingredientQuantity;

    public Composer(Map<Ingredient, Integer> ingredientQuantity){
        this.ingredientQuantity=ingredientQuantity;
    }

    public Map<Ingredient, Integer> getIngredients(){
        return(this.ingredientQuantity);
    }
}
