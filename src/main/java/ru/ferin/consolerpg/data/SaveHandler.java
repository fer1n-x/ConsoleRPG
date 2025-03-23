package ru.ferin.consolerpg.data;

import ru.ferin.consolerpg.core.ConsoleRPG;
import ru.ferin.consolerpg.entity.EntityPlayer;
import ru.ferin.consolerpg.util.WorldUtils;
import ru.ferin.consolerpg.world.World;
import ru.ferin.consolerpg.world.locations.LocationDungeon;

/**
 * Сохранение мира в базу данных sqlite. Каждый мир (ака сейв) хранится в отдельном файле .bd по принципу "не класть все яйца в одну корзину".
 */
public class SaveHandler {
    private static final String SAVE_PATH = "saves/";
    public static void loadSave() {
        ConsoleRPG consoleRPG = ConsoleRPG.getInstance();
        SaveState saveState = consoleRPG.getTempSaveState();
        consoleRPG.setPlayer(new EntityPlayer(
                saveState.lvl(),
                saveState.health(),
                saveState.attack(),
                saveState.defence()
        ));
        consoleRPG.setWorld(new World(new LocationDungeon(WorldUtils.getAvailableEnemyCount())));
    }

    public static void saveProgress() {
        EntityPlayer player = ConsoleRPG.getInstance().getPlayer();
        SQLiteManager.saveProgress(new SaveState(
                player.getLvl(),
                player.getHealth(),
                player.getAttackStrength(),
                player.getDefense(),
                System.currentTimeMillis()
        ));
    }
    public static String getLastSaveDate() {
        return SQLiteManager.checkLevel();
    }
    public static void saveLogs() {
        SQLiteManager.saveLogs(LogHandler.logList);
    }
}
