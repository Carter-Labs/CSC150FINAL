package model.objects;

public class Currency {
    /**
     * Variables
     */
    private int value;

    /**
     * Currency constructor
     * @param value Currency value
     */
    public Currency(int value){
        this.setValue(value);
    }

    /**
     * @return Currency value
     */
    public int getValue() {
        return value;
    }

    /**
     * Sets currency value
     * @param value Currency value
     */
    public void setValue(int value) {
        if(value < 0){
            throw new IllegalArgumentException("value must be greater than 0.");
        }
        this.value = value;
    }

    /**
     * @return Currency description
     */
    @Override public String toString() {
        return "model.objects.Currency{" +
                "value=" + value +
                '}';
    }
}
