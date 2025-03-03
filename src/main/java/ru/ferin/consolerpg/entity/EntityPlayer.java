package ru.ferin.consolerpg.entity;

public class EntityPlayer extends EntityLivingBase {
    public final int lvl;
    public EntityPlayer(double posX, double posZ, int health, int lvl) {
        super(posX, posZ, health);
        this.lvl = lvl;
    }
}
