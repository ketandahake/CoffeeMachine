package ingredients;

public enum Ingredient {
    GINGER_SYRUP("Ginger Syrup"),
    GREEN_MIXTURE("Green Mixture"),
    HOT_MILK("Hot Milk"),
    HOT_WATER("Hot Water"),
    SUGAR_SYRUP("Sugar Syrup"),
    TEA_LEAVES_SYRUP("Tea Leaves Syrup");

    private String name;

    Ingredient(String name){
        this.name=name;
    }

    public String getName(){
        return(this.name);
    }
}
