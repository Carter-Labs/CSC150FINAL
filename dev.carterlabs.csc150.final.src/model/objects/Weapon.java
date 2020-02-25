package model.objects;

import java.util.Objects;

public class Weapon {
    /**
     * Variables
     */
    private int damage;
    private WeaponType weaponType;

    /**
     * Weapon Constructor
     * @param damage Weapon damage
     * @param weaponType Weapon type
     */
    public Weapon(int damage, WeaponType weaponType) {
        this.setDamage(damage);
        this.setWeaponType(weaponType);
    }

    /**
     * @return Weapon Damage
     */
    public int getDamage() {
        return damage;
    }

    /**
     * Sets the weapon damage
     * @param damage Weapon damage
     */
    public void setDamage(int damage) {
        if(damage < 0) {
            throw new IllegalArgumentException("damage must be greater than 0.");
        }
        this.damage = damage;
    }

    /**
     * @return Weapon type
     */
    public WeaponType getWeaponType() {
        return weaponType;
    }

    /**
     * Sets the weapon type
     * @param weaponType Weapon type
     */
    public void setWeaponType(WeaponType weaponType) {
        this.weaponType = weaponType;
    }

    /**
     * Compares weapons
     * @param o Weapon object
     * @return If the weapons are equal
     */
    @Override public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Weapon weapon = (Weapon) o;
        return damage == weapon.damage &&
                weaponType == weapon.weaponType;
    }

    /**
     * @return Hash of weapons
     */
    @Override public int hashCode() {
        return Objects.hash(damage, weaponType);
    }

    /**
     * @return Weapon description
     */
    @Override public String toString() {
        return "model.objects.Weapon{" +
                "damage=" + damage +
                ", weaponType=" + weaponType +
                '}';
    }
}
