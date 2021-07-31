package tests;

import beverages.BeverageType;
import composers.*;
import ingredients.Ingredient;
import ingredients.IngredientInventory;
import main.CoffeeMachine;

import java.util.HashMap;
import java.util.Map;

public class CoffeeMachineTest {
    private static CoffeeMachine coffeeMachine;

     public static void main(String[] args) throws InterruptedException {
         setup();
         testToGetABeverageWhenAllIngredientsArePresent();
         testToGetABeverageWhenIngredientsAreNotSufficient();
         coffeeMachine.addIngredient(Ingredient.SUGAR_SYRUP, 500);
         testToServeBeveragesInParallel();
         Thread.sleep(2000);
         System.out.println();
         testWhenBeveragesRequestedAreMoreThanNumberOfSlots();
    }

    //setup() to create a coffee machine with some ingredients and beverages
    public static void setup(){
         //adding ingredients to the inventory
        IngredientInventory ingredientInventory = new IngredientInventory();
        ingredientInventory.addIngredient(Ingredient.HOT_WATER, 1500);
        ingredientInventory.addIngredient(Ingredient.GINGER_SYRUP, 500);
        ingredientInventory.addIngredient(Ingredient.SUGAR_SYRUP, 50);
        ingredientInventory.addIngredient(Ingredient.TEA_LEAVES_SYRUP, 500);
        ingredientInventory.addIngredient(Ingredient.HOT_MILK, 1000);

        //composer map for the coffeemachine
        Map<BeverageType, Composer> beverageComposerMap = new HashMap<>();

        //adding a composer with ingredient quantities required for black tea
        Map<Ingredient, Integer> ingredientsForBlackTea = new HashMap<>();
        ingredientsForBlackTea.put(Ingredient.HOT_WATER, 300);
        ingredientsForBlackTea.put(Ingredient.GINGER_SYRUP, 30);
        ingredientsForBlackTea.put(Ingredient.SUGAR_SYRUP, 50);
        ingredientsForBlackTea.put(Ingredient.TEA_LEAVES_SYRUP, 30);
        Composer blackTeaComposer = new BlackTeaComposer(ingredientsForBlackTea);
        beverageComposerMap.put(BeverageType.BLACK_TEA, blackTeaComposer);

        //adding a composer with ingredient quantities required for green tea
        Map<Ingredient, Integer> ingredientsForGreenTea = new HashMap<>();
        ingredientsForGreenTea.put(Ingredient.HOT_WATER, 100);
        ingredientsForGreenTea.put(Ingredient.GINGER_SYRUP, 30);
        ingredientsForGreenTea.put(Ingredient.SUGAR_SYRUP, 50);
        ingredientsForGreenTea.put(Ingredient.GREEN_MIXTURE, 30);
        Composer greenTeaComposer = new GreenTeaComposer(ingredientsForGreenTea);
        beverageComposerMap.put(BeverageType.GREEN_TEA, greenTeaComposer);

        Map<Ingredient, Integer> ingredientsForHotTea = new HashMap<>();
        ingredientsForHotTea.put(Ingredient.HOT_WATER, 200);
        ingredientsForHotTea.put(Ingredient.HOT_MILK, 100);
        ingredientsForHotTea.put(Ingredient.GINGER_SYRUP, 10);
        ingredientsForHotTea.put(Ingredient.SUGAR_SYRUP, 10);
        ingredientsForHotTea.put(Ingredient.TEA_LEAVES_SYRUP, 30);
        Composer hotTeaComposer = new HotTeaComposer(ingredientsForHotTea);
        beverageComposerMap.put(BeverageType.HOT_TEA, hotTeaComposer);

        Map<Ingredient, Integer> ingredientsForHotCoffee = new HashMap<>();
        ingredientsForHotCoffee.put(Ingredient.HOT_WATER, 100);
        ingredientsForHotCoffee.put(Ingredient.GINGER_SYRUP, 30);
        ingredientsForHotCoffee.put(Ingredient.HOT_MILK, 400);
        ingredientsForHotCoffee.put(Ingredient.SUGAR_SYRUP, 50);
        ingredientsForHotCoffee.put(Ingredient.TEA_LEAVES_SYRUP, 30);
        Composer hotCoffeeComposer = new HotCoffeeComposer(ingredientsForHotCoffee);
        beverageComposerMap.put(BeverageType.HOT_COFFEE, hotCoffeeComposer);

        //creating a new coffeeMachine with an ingredientInventory and beverage composers
        coffeeMachine = new CoffeeMachine(2, beverageComposerMap, ingredientInventory);
    }

    public static void testToGetABeverageWhenIngredientsAreNotSufficient() throws InterruptedException {
        System.out.println("Running test to get a beverage when some ingredients are insufficient");
        coffeeMachine.getBeverage(BeverageType.BLACK_TEA);
        System.out.println();
    }

    public static void testToGetABeverageWhenAllIngredientsArePresent() throws InterruptedException {
        System.out.println("Running test to get a beverage when all ingredients are sufficient");
        coffeeMachine.getBeverage(BeverageType.BLACK_TEA);
        System.out.println();
    }


    public static void testToServeBeveragesInParallel(){
        System.out.print("Running test to get beverages in parallel when requested beverages are less than number of slots");
        Runnable blackTeaRunnable  = new Runnable() {
            @Override
            public void run() {
                try {
                    coffeeMachine.getBeverage(BeverageType.BLACK_TEA);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        };

        Runnable hotTeaRunnable  = new Runnable() {
            @Override
            public void run() {
                try {
                    coffeeMachine.getBeverage(BeverageType.HOT_COFFEE);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        };

        Runnable arr[] = new Runnable[]{hotTeaRunnable,blackTeaRunnable};

        for(int i=0;i<2;i++) {
            Thread t = new Thread(arr[i]);
            t.setName("["+ "Thread "+ i +"]");
            t.start();
        }
        System.out.println();
    }

    public static void testWhenBeveragesRequestedAreMoreThanNumberOfSlots(){
         System.out.print("Running test to get beverage when available slots are less than the number of beverages requested");
        Runnable blackTeaRunnable  = new Runnable() {
            @Override
            public void run() {
                try {
                    coffeeMachine.getBeverage(BeverageType.BLACK_TEA);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        };

        Runnable hotTeaRunnable  = new Runnable() {
            @Override
            public void run() {
                try {
                    coffeeMachine.getBeverage(BeverageType.HOT_TEA);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        };

        Runnable hotCoffeeRunnable  = new Runnable() {
            @Override
            public void run() {
                try {
                    coffeeMachine.getBeverage(BeverageType.HOT_COFFEE);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        };

        Runnable arr[] = new Runnable[]{blackTeaRunnable, hotTeaRunnable, hotCoffeeRunnable};

        for(int i=0;i<3;i++) {
            Thread t = new Thread(arr[i]);
            t.setName("["+ "Thread "+ i +"]");
            t.start();
        }
        System.out.println();
    }
}
