package exception;

public class IngredientNotSufficient extends Throwable {
    private String name;
    public IngredientNotSufficient(String name){
        this.name=name;
    }

    public String getIngredientName(){
        return(this.name);
    }
}
