package ru.ferin.consolerpg.player;

import ru.ferin.consolerpg.core.ConsoleRPG;

public class Potion {
    public Potion()
    {
        this.type = PotionType.NONE;
        this.strength = 0;
    }
    public Potion(PotionType type, double strength)
    {
        this.type = type;
        this.strength = strength;
    }
    public enum PotionType
    {
        HEALTH, DAMAGE, DEFENCE, NONE
    }
    public final PotionType type;
    public final double strength;
}
