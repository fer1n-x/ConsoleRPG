package ru.ferin.consolerpg.entity;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

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
        return 0;
    }

    @Override
    public int getLvl() {
        return lvl;
    }

    @Override
    public void attack(double strength) {
    }

}
