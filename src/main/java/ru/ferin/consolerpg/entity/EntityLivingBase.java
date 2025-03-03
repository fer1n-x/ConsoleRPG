package ru.ferin.consolerpg.entity;

public abstract class EntityLivingBase extends Entity{
    int health;

    public EntityLivingBase(double posX, double posZ, int health) {
        super(posX, posZ, 500);
        this.health = health;
    }
    public void damage(int damage) {
        health -= damage;
    }
}
