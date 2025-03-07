package ru.ferin.consolerpg.player;

import ru.ferin.consolerpg.core.ConsoleRPG;

public class Potion {
    public Potion()
    {
        this.type = PotionType.NONE;
    }
    public Potion(PotionType type, int strength)
    {
        if (type == PotionType.HEALTH) {
            ConsoleRPG.getInstance().getPlayer().addHealth(strength);
        }
        this.type = type;
        this.strength = strength;
    }
    public enum PotionType
    {
        HEALTH, DAMAGE, DEFENCE, NONE
    }
    private PotionType type;
    private int strength = 0;

    public double handleDamage(double damage)
    {
        if (type == PotionType.DAMAGE) {
            reset();
            return strength+damage;
        }
        else return damage;
    }
    private void reset()
    {
        this.type = PotionType.NONE;
    }
}
