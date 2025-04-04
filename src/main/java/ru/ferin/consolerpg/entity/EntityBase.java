package ru.ferin.consolerpg.entity;

import ru.ferin.consolerpg.data.LogHandler;
import ru.ferin.consolerpg.data.LogState;

public abstract class EntityBase {
    protected double health;
    protected int lvl;
    protected double attackStrength;
    protected double defense;
    public EntityBase(double health, int lvl, double attackStrength, double defense) {
        health *= (Math.max(0, lvl*0.1));
        this.health = health;
        this.lvl = lvl;
        this.attackStrength = attackStrength;
        this.defense = defense;
    }
    public abstract String getName();
    public abstract double getHealth();
    public abstract double getAttackStrength();
    public abstract double getDefense();
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

    public void setLvl(int lvl) {
        this.lvl = lvl;
    }

    public abstract void attack(double strength);
    public abstract void death();
    public double handleDamage(double strength) {
        //С шансом 25% урон будет критический
        if (Math.random() < 0.25) strength *= 2;
        return strength;
    }
}
