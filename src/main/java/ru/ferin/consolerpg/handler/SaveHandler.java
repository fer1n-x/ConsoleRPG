package ru.ferin.consolerpg.handler;

import ru.ferin.consolerpg.world.World;

/**
 * Сохранение мира в базу данных sqlite. Каждый мир (ака сейв) хранится в отдельном файле .bd по принципу "не класть все яйца в одну корзину".
 */
public class SaveHandler {
    private static final String SAVE_PATH = "saves/";
    public static World loadSave() {
        return null;
    }

    public static synchronized void saveWorld(World world) {

    }
    public static String getLastSave() {
        return "";
    }
}
