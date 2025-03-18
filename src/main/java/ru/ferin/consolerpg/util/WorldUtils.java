package ru.ferin.consolerpg.util;

import ru.ferin.consolerpg.core.ConsoleRPG;

public class WorldUtils {
    public static int getAvailableLvl() {
        return ConsoleRPG.getInstance().getPlayer().getLvl();
    }
    public static int getAvailableEnemyCount() {
        int lvl = getAvailableLvl();
        return lvl + Math.max(1, lvl / 2);
    }
    public static int getCurrentEnemyCount() {
        return ConsoleRPG.getInstance().getWorld().getCurrentLocation().getEnemiesCount();
    }
}
