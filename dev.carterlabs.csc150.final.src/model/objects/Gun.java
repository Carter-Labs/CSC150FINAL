package model.objects;

import java.util.Objects;

public class Gun extends Weapon {
    /*
     * Variables for the specific instance
     */
    private double reloadSpeed;
    private int damage;
    private int projectTileCount;
    private int magSize;

    /*
     * Constructors
     */
    public Gun() {}
    /*
     * for the gun with projectiles
     */
    public Gun(double reloadSpeed, int damage, int projectTileCount, int magSize, WeaponType weaponType) {
        super(damage, weaponType);
        this.setReloadSpeed(reloadSpeed);
        this.setProjectTileCount(projectTileCount);
        this.setMagSize(magSize);
    }

    /*
     * getters and setter for the specific instance of the gun
     */
    public double getReloadSpeed() {
        return reloadSpeed;
    }
    public void setReloadSpeed(double reloadSpeed) {
        if(reloadSpeed < 0) {
            throw new IllegalArgumentException("reloadSpeed must be greater than 0.");
        }
        this.reloadSpeed = reloadSpeed;
    }
    public int getProjectTileCount() {
        return projectTileCount;
    }
    public void setProjectTileCount(int projectTileCount) {
        if(projectTileCount < 0) {
            throw new IllegalArgumentException("projectileCount must be greater than 0.");
        }
        this.projectTileCount = projectTileCount;
    }
    public int getMagSize() {
        return magSize;
    }
    public void setMagSize(int magSize) {
        if(magSize < 0) {
            throw new IllegalArgumentException("magSize must be greater than 0.");
        }
        this.magSize = magSize;
    }
    /*
     * Equals and Hash
     */
    @Override public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Gun gun = (Gun) o;
        return Double.compare(gun.reloadSpeed, reloadSpeed) == 0 &&
                damage == gun.damage &&
                projectTileCount == gun.projectTileCount &&
                magSize == gun.magSize;
    }
    @Override public int hashCode() {
        return Objects.hash(reloadSpeed, damage, projectTileCount, magSize);
    }

    /*
     *To String
     */
    @Override public String toString() {
        return "model.objects.Gun{" +
                "reloadSpeed=" + reloadSpeed +
                ", damage=" + damage +
                ", projectTileCount=" + projectTileCount +
                ", magSize=" + magSize +
                '}';
    }
}
