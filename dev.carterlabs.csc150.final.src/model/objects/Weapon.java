package model.objects;

import java.util.Objects;

public class Weapon {
    /*
     * Variables
     */
    private int damage;
    private WeaponType weaponType;

    /*
     * Constructors
     */
    public Weapon() {}
    public Weapon(int damage, WeaponType weaponType) {
        this.setDamage(damage);
        this.setWeaponType(weaponType);
    }

    /*
     * Getters and setters
     */
    public int getDamage() {
        return damage;
    }
    public void setDamage(int damage) {
        if(damage < 0) {
            throw new IllegalArgumentException("damage must be greater than 0.");
        }
        this.damage = damage;
    }
    public WeaponType getWeaponType() {
        return weaponType;
    }
    public void setWeaponType(WeaponType weaponType) {
        this.weaponType = weaponType;
    }

    /*
     * Equal and Hash
     */
    @Override public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Weapon weapon = (Weapon) o;
        return damage == weapon.damage &&
                weaponType == weapon.weaponType;
    }
    @Override public int hashCode() {
        return Objects.hash(damage, weaponType);
    }

    /*
     * To String
     */
    @Override public String toString() {
        return "model.objects.Weapon{" +
                "damage=" + damage +
                ", weaponType=" + weaponType +
                '}';
    }
}
