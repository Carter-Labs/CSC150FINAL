package model.objects;

public class Currency {
    /*
     * Variables
     */
    private int value;

    /*
     * Constructors
     */
    public Currency(int value){
        this.setValue(value);
    }

    /*
     * Getters and Setters
     */
    public int getValue() {
        return value;
    }
    public void setValue(int value) {
        if(value < 0){
            throw new IllegalArgumentException("value must be greater than 0.");
        }
        this.value = value;
    }

    /*
     * To String
     */
    @Override public String toString() {
        return "model.objects.Currency{" +
                "value=" + value +
                '}';
    }
}
