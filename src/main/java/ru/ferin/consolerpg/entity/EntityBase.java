package ru.ferin.consolerpg.entity;

public abstract class EntityBase {
    protected double health;
    protected int lvl;
    public EntityBase(double health, int lvl) {
        this.health = health;
        this.lvl = lvl;
    }
    public abstract String getName();
    public abstract double getHealth();
    public abstract double getAttackStrength();
    public abstract int getLvl();
    public abstract void attack(double strength);
}
