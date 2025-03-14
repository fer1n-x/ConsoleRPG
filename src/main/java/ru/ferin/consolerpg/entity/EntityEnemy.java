package ru.ferin.consolerpg.entity;

import ru.ferin.consolerpg.core.ConsoleRPG;

public class EntityEnemy extends EntityBase {


    public EntityEnemy(double health, int lvl, double attackStrength, double defense) {

        super(health, lvl, attackStrength, defense);
    }

    @Override
    public String getName() {
        return null;
    }

    @Override
    public double getHealth() {
        return health;
    }

    @Override
    public double getAttackStrength() {
        return attackStrength;
    }

    @Override
    public void attack(double strength) {
        this.health = Math.max(0, this.health - strength);
        if (this.health == 0) death();
    }

    @Override
    public void death() {
        //С шансом 10% увеличивается урон либо здоровье игрока
        if (Math.random() < 0.1) {ConsoleRPG.getInstance().getPlayer().addHealth(10+lvl); return;}
        if (Math.random() < 0.1) {ConsoleRPG.getInstance().getPlayer().addAttackStrength(10+lvl);}
    }

    @Override
    public int getLvl() {
        return lvl;
    }
}
