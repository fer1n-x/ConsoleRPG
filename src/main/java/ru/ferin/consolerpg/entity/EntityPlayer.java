package ru.ferin.consolerpg.entity;


import ru.ferin.consolerpg.player.Potion;
import ru.ferin.consolerpg.util.SOut;

public class EntityPlayer extends EntityBase {


    public EntityPlayer(double health, int lvl, double attackStrength, double defense) {
        super(health, lvl, attackStrength, defense);
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
        this.health -= (strength - defense);
        defense -= Math.max(0, strength - defense);
    }

    @Override
    public void death() {
        //no-op
    }
    //TODO перенести логику в Potion? я хз
    public void applyPotion(Potion potion) {
        switch (potion.type) {
            case DAMAGE -> attackStrength += potion.strength;
            case HEALTH -> health += potion.strength;
            case DEFENCE -> defense += potion.strength;
        }
    }
}
