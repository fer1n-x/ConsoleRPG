package ru.ferin.consolerpg.entity;


import ru.ferin.consolerpg.util.SOut;

public class EntityPlayer extends EntityBase {


    public EntityPlayer(double health, int lvl) {
        super(health, lvl);
    }

    @Override
    public String getName() {
        return "Player";
    }

    @Override
    public double getHealth() {
        return health*(Math.min(0, lvl*0.1));
    }

    @Override
    public double getAttackStrength() {
        return attackStrength*lvl;
    }

    @Override
    public int getLvl() {
        return lvl;
    }

    @Override
    public void attack(double strength) {
    }

    @Override
    public void death() {
        //no-op
    }

}
