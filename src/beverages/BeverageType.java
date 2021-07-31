package beverages;

public enum BeverageType {
    HOT_TEA("Hot Tea"),
    HOT_COFFEE("Hot Coffee"),
    BLACK_TEA("Black Tea"),
    GREEN_TEA("Green Tea");

    private String name;

    BeverageType(String name){
        this.name=name;
    }
}
