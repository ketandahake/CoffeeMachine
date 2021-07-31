package exception;

public class IngredientNotAvailable extends Exception {
    private String name;

    public IngredientNotAvailable(String name){
        this.name=name;
    }

    public String getIngredientName(){
        return(this.name);
    }
}
