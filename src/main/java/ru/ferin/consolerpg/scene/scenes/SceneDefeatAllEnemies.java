package ru.ferin.consolerpg.scene.scenes;

import ru.ferin.consolerpg.core.ConsoleRPG;
import ru.ferin.consolerpg.data.SQLiteManager;
import ru.ferin.consolerpg.data.SaveState;
import ru.ferin.consolerpg.entity.EntityPlayer;
import ru.ferin.consolerpg.scene.Action;
import ru.ferin.consolerpg.scene.Scene;
import ru.ferin.consolerpg.util.WorldUtils;
import ru.ferin.consolerpg.world.World;
import ru.ferin.consolerpg.world.locations.LocationDungeon;

public class SceneDefeatAllEnemies extends Scene {
    //Я НЕ ЗНАЮ ПОЧЕМУ, НО ПЕРЕКЛЮЧЕНИЕ СЦЕНЫ В Action#execute() НЕ РАБОТАЕТ
    //Мне впадлу фиксить это
    boolean isOk = false;
    @Override
    public void preInit() {
        if (isOk) {
            ConsoleRPG.getInstance().setCurrentScene(new SceneEnterIntoDungeon());
        }
    }

    @Override
    public void initActions() {
        actions.add(new Action() {

            @Override
            public String getDescription() {
                return "Ok";
            }

            @Override
            public Result execute() {
                isOk = true;
                EntityPlayer player = consoleRPG.getPlayer();
                player.lvlUp();
                consoleRPG.setWorld(new World(new LocationDungeon(WorldUtils.getAvailableEnemyCount())));
                consoleRPG.save();
                return new Action.Result("Moving you to next dungeon", true);
            }
        });
    }

    @Override
    public String getDescription() {
        return "You defeated all enemies.";
    }
}
