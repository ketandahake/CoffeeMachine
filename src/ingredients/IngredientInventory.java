package ingredients;

import exception.IngredientNotAvailable;
import exception.IngredientNotSufficient;

import java.util.HashMap;

public class IngredientInventory {
    private HashMap<Ingredient, Integer> ingredientQuantity;

    public IngredientInventory(){
        ingredientQuantity= new HashMap<>();
    }

    public void addIngredient(Ingredient ingredient, int quantity){
        if(!ingredientQuantity.containsKey(ingredient)){
            ingredientQuantity.put(ingredient, quantity);
        }
        else{
            int oldQuantity=ingredientQuantity.get(ingredient);
            ingredientQuantity.replace(ingredient, oldQuantity+quantity);
        }
    }

    public int getIngredient(Ingredient ingredient, int quantity) throws IngredientNotAvailable, IngredientNotSufficient {
        if(!ingredientQuantity.containsKey(ingredient)){
            throw new IngredientNotAvailable(ingredient.getName());
        }
        else if(ingredientQuantity.get(ingredient)<quantity){
            throw new IngredientNotSufficient(ingredient.getName());
        }
        int oldQuantity=ingredientQuantity.get(ingredient);
        ingredientQuantity.replace(ingredient, oldQuantity-quantity);
        return(quantity);
    }

    public Integer getIngredientQuantity(Ingredient ingredient){
        return(this.ingredientQuantity.get(ingredient));
    }

    public void setIngredientQuantity(Ingredient ingredient, Integer quantity){
        this.ingredientQuantity.replace(ingredient, quantity);
    }

    public boolean containsIngredient(Ingredient ingredient){
        return(this.ingredientQuantity.containsKey(ingredient));
    }

    public void checkIngredientsRunningLow(int limit){
        for(Ingredient ingredient: this.ingredientQuantity.keySet()){
            if(this.ingredientQuantity.get(ingredient)<limit){
                System.out.println("Ingredient "+ingredient.getName()+" running below given limit of "+limit+" available amount: "+
                ingredientQuantity.get(ingredient));
            }
        }
    }
}
