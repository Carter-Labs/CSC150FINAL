package model;

public class Boss extends Entity {
    /*
     * Constructors
     */
    public Boss(){}
    public Boss(int health,int speed, Weapon weapon){
        super(health,speed,weapon);
    }

    /*
     * To String
     */
    @Override public String toString() {
        return super.toString();
    }
}
