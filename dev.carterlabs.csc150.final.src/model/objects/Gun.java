package model.objects;

import java.util.Objects;

public class Gun extends Weapon {
    /*
     * Variables for the specific instance
     */
    private double reloadSpeed;
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
     * for the randomly generated entities
     */
    public Gun(int damage, WeaponType weaponType){
        super(damage, weaponType);
        switch (weaponType) {
            case AR: this.setReloadSpeed(10); this.setProjectTileCount(1); this.setMagSize(30);break;
            case SHOTGUN: this.setReloadSpeed(50); this.setProjectTileCount(3); this.setMagSize(3);break;
            case SMG: this.setReloadSpeed(10); this.setProjectTileCount(1); this.setMagSize(50); break;
            case SNIPER: this.setReloadSpeed(8); this.setProjectTileCount(1); this.setMagSize(10);break;
            case RAY_GUN: this.setReloadSpeed(20); this.setProjectTileCount(1); this.setMagSize(25);break;
            case ROCKET_LAUNCHER:this.setReloadSpeed(75); this.setProjectTileCount(1); this.setMagSize(3);break;
        }
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
        if (!super.equals(o)) return false;
        Gun gun = (Gun) o;
        return Double.compare(gun.reloadSpeed, reloadSpeed) == 0 &&
                projectTileCount == gun.projectTileCount &&
                magSize == gun.magSize;
    }

    @Override public int hashCode() {
        return Objects.hash(super.hashCode(), reloadSpeed, projectTileCount, magSize);
    }

    /*
     *To String
     */
    @Override public String toString() {
        return "model.objects.Gun{" +
                "reloadSpeed=" + reloadSpeed +
                ", projectTileCount=" + projectTileCount +
                ", magSize=" + magSize +
                '}';
    }
}
