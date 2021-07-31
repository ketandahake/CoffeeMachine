package main;

import beverages.Beverage;
import beverages.BeverageFactory;
import beverages.BeverageType;
import composers.Composer;
import ingredients.IngredientInventory;
import ingredients.Ingredient;

import java.util.Map;
import java.util.concurrent.Semaphore;

public class CoffeeMachine {
    private int numSlots;
    private Map<BeverageType, Composer> composers;
    private IngredientInventory ingredientInventory;
    //semapohore is used to ensure that only numSlots number of beverages can be served in parallel
    private Semaphore semaphore;

    public CoffeeMachine(int numSlots, Map<BeverageType, Composer> composers, IngredientInventory ingredientInventory){
        this.numSlots=numSlots;
        this.composers=composers;
        this.ingredientInventory=ingredientInventory;
        this.semaphore=new Semaphore(numSlots);
    }

    //getBeverage() is called to get a particular beverage
    public Beverage getBeverage(BeverageType beverageType) throws InterruptedException {
        Beverage beverage=null;
        //check if slots are available to serve the beverage
        boolean isSlotAvailable=this.semaphore.tryAcquire();
        if(!isSlotAvailable){
            System.out.println("No slots available for serving beverage "+beverageType+" to thread "+Thread.currentThread().getName());
        }
        else{
            System.out.println("Slot acquired by the the thread "+Thread.currentThread().getName());
        }
        synchronized (ingredientInventory){
            Composer composer = composers.get(beverageType);
            Map<Ingredient, Integer> composition = composer.getIngredients();

            //check if sufficient amount of all required ingredients are present
            for (Map.Entry<Ingredient, Integer> ingredientQuantity : composition.entrySet()) {
                if (!ingredientInventory.containsIngredient(ingredientQuantity.getKey())) {
                    System.out.println(beverageType + " cannot be prepared for thread: "+Thread.currentThread().getName()+" " +
                            "because "+ingredientQuantity.getKey().getName()+" is not available");
                    semaphore.release();
                    return null;
                }
                if (ingredientInventory.getIngredientQuantity(ingredientQuantity.getKey()) < ingredientQuantity.getValue()) {
                    System.out.println(beverageType + " cannot be prepared for thread: "+Thread.currentThread().getName()+" " +
                            "because "+ingredientQuantity.getKey().getName()+" is not sufficient");
                    semaphore.release();
                    return null;
                }
            }

            //get the required ingredients from inventory and update their quantities
            for (Map.Entry<Ingredient, Integer> ingredientQuantity : composition.entrySet()) {
                int amount = ingredientInventory.getIngredientQuantity(ingredientQuantity.getKey());
                amount -= ingredientQuantity.getValue();
                ingredientInventory.setIngredientQuantity(ingredientQuantity.getKey(), amount);
            }
        }
        beverage = BeverageFactory.getBeverage(beverageType);
        System.out.println(beverageType+" is prepared for thread "+Thread.currentThread().getName());
        semaphore.release();
        return(beverage);
    }

    //addIngredient is used to add ingredient in the inventory
    public void addIngredient(Ingredient ingredient, int quantity){
        this.ingredientInventory.addIngredient(ingredient, quantity);
    }

    //showIngrdientsRunningLow to indicate which all ingredients are running low
    public void showIngredientsRunningLow(int limit){
        this.ingredientInventory.checkIngredientsRunningLow(limit);
    }
}
