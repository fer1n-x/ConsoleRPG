package ru.ferin.consolerpg.entity;


import ru.ferin.consolerpg.data.LogHandler;
import ru.ferin.consolerpg.data.LogState;
import ru.ferin.consolerpg.player.Potion;
import ru.ferin.consolerpg.util.SOut;

public class EntityPlayer extends EntityBase {


    public EntityPlayer(int lvl, double health, double attackStrength, double defense) {
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
    public double getDefense() {
        return this.defense;
    }

    @Override
    public int getLvl() {
        return lvl;
    }

    @Override
    public void attack(double strength) {
        this.health -= (strength - defense);
        defense -= Math.max(0, strength - defense);
        LogHandler.writeLog(LogState.LogType.PLAYER_DAMAGE, strength);
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
        LogHandler.writeLog(LogState.LogType.APPLIED_POTION, potion.strength);
    }
    public void lvlUp() {
        this.lvl++;
        LogHandler.writeLog(LogState.LogType.LEVEL_UP, this.lvl);
    }
}
