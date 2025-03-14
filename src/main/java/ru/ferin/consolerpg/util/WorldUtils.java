package ru.ferin.consolerpg.util;

import ru.ferin.consolerpg.core.ConsoleRPG;

public class WorldUtils {
    public static int getAvailableLvl() {
        return ConsoleRPG.getInstance().getPlayer().getLvl();
    }
    public static int getAvailableEnemyCount() {
        int lvl = getAvailableLvl();
        return lvl + (lvl / 2);
    }
}
