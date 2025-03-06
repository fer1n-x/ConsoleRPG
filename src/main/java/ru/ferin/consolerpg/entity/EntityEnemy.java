package ru.ferin.consolerpg.entity;

public class EntityEnemy extends EntityBase {

    public EntityEnemy(int health, int lvl) {
        super(health, lvl);
    }

    @Override
    public String getName() {
        return null;
    }

    @Override
    public double getHealth() {
        return 0;
    }

    @Override
    public double getAttackStrength() {
        return 0;
    }

    @Override
    public void attack(double strength) {

    }

    @Override
    public int getLvl() {
        return lvl;
    }
}
