package beverages;

public class BeverageFactory {

    public static Beverage getBeverage(BeverageType beverageType) throws InterruptedException {
        //time to prepare the beverage
        Thread.sleep(100);
        if(beverageType.equals(BeverageType.HOT_TEA)){
            return(new BlackTea());
        }
        else if(beverageType.equals(BeverageType.HOT_COFFEE)){
            return(new HotCoffee());
        }
        else if(beverageType.equals(BeverageType.BLACK_TEA)){
            return(new BlackTea());
        }
        else if(beverageType.equals(BeverageType.GREEN_TEA)){
            return(new GreenTea());
        }
        return(null);
    }
}
