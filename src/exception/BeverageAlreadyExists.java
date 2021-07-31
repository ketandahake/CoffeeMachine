package exception;

public class BeverageAlreadyExists extends Exception {
    private String name;

    public BeverageAlreadyExists(String name){
        this.name=name;
    }

    public String getBeverageName(){
        return(this.name);
    }
}
