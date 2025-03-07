package ru.ferin.consolerpg.entity;

public abstract class EntityBase {
    protected double health;
    protected int lvl;
    protected double attackStrength;
    public EntityBase(double health, int lvl) {
        this.health = health;
        this.lvl = lvl;
        this.attackStrength = 2;
    }
    public abstract String getName();
    public abstract double getHealth();
    public abstract double getAttackStrength();
    public void setHealth(double health) {
        this.health = health;
    }
    public void addHealth(double health) {
        this.health += health;
    }
    public void setAttackStrength(double attackStrength) {
        this.attackStrength = attackStrength;
    }
    public void addAttackStrength(double attackStrength) {
        this.attackStrength += attackStrength;
    }
    public abstract int getLvl();
    public abstract void attack(double strength);
    public abstract void death();
    public double handleDamage(double strength) {
        //С шансом 25% урон будет критический
        if (Math.random() < 0.25) strength *= 2;
        return strength;
    }
}
