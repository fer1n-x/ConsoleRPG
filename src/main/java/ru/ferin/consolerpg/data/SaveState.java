package ru.ferin.consolerpg.data;

/**
 * Условная коробка с посылками из <link ru.ferin.consolerpg.data.SQLiteManager>SQLiteManager</link>, которую можно распаковать
 * в <link ru.ferin.consolerpg.world.World>World</link>
 */
public record SaveState(int lvl, double health, double attack, double defence, long lasttime) {
    public boolean isValid() {
        return lvl > -1 && health > -1;
    }
}
