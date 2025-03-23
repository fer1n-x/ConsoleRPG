package ru.ferin.consolerpg.entity;

import ru.ferin.consolerpg.core.ConsoleRPG;
import ru.ferin.consolerpg.data.LogHandler;
import ru.ferin.consolerpg.data.LogState;
import ru.ferin.consolerpg.player.Potion;
import ru.ferin.consolerpg.util.SOut;

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
    public double getDefense() {
        return defense;
    }

    @Override
    public void attack(double strength) {
        this.health = Math.max(0, this.health - strength);
        if (this.health == 0) death();
        LogHandler.writeLog(LogState.LogType.ENEMY_DAMAGE, strength);
    }

    @Override
    public void death() {
        EntityPlayer player = ConsoleRPG.getInstance().getPlayer();
        //С шансом 10% увеличивается урон или здоровье игрока
        if (Math.random() < 0.1) {
            SOut.println("You got a health potion! You health is now " + player.getHealth());
            player.applyPotion(new Potion(Potion.PotionType.HEALTH, 10+lvl));
            return;
        }
        if (Math.random() < 0.1) {
            SOut.println("You got a damage potion! You strength is now " + player.getAttackStrength());
            player.applyPotion(new Potion(Potion.PotionType.DAMAGE, 10+lvl));
        }
        LogHandler.writeLog(LogState.LogType.ENEMY_DEATH, 0);
    }

    @Override
    public int getLvl() {
        return lvl;
    }
}
