package ru.ferin.consolerpg.scene.scenes;

import ru.ferin.consolerpg.core.ConsoleRPG;
import ru.ferin.consolerpg.data.SQLiteManager;
import ru.ferin.consolerpg.scene.Action;
import ru.ferin.consolerpg.scene.Scene;
import ru.ferin.consolerpg.util.WorldUtils;
import ru.ferin.consolerpg.world.World;
import ru.ferin.consolerpg.world.locations.LocationBase;
import ru.ferin.consolerpg.world.locations.LocationDungeon;

import java.util.EmptyStackException;

public class SceneIntoDungeon extends Scene {

    @Override
    public void preInit() {
        if (location.isEnemiesLeft()) {
            consoleRPG.getPlayer().setLvl(consoleRPG.getPlayer().getLvl() + 1);
            consoleRPG.setWorld(new World(new LocationDungeon("Dungeon", WorldUtils.getAvailableEnemyCount(), consoleRPG.getPlayer().getLvl())));
            consoleRPG.setCurrentScene(new SceneDefeatAllEnemies());
            SQLiteManager.saveLevel(consoleRPG.getPlayer().getLvl());
        }
        else if (location.isCurrentEnemyDead() && !location.isEnemiesLeft()) consoleRPG.setCurrentScene(new SceneDefeatEnemy());
    }

    @Override
    public void initActions() {
        if (!location.isEnemiesLeft()) {
            if (!location.isCurrentEnemyDead()) {
                actions.add(new Action() {

                    @Override
                    public String getDescription() {
                        return "Attack it ("+ConsoleRPG.getInstance().getPlayer().getAttackStrength()+" damage)";
                    }

                    @Override
                    public Result execute() {
                        location.attackCurrentEnemy(ConsoleRPG.getInstance().getPlayer().getAttackStrength());
                        if (location.isCurrentEnemyDead()) {
                            return new Result("Enemy died.", true);
                        }
                        return new Result("Enemy attacked. He has " + location.getCurrentEnemy().getHealth() + " hp", true);
                    }
                });
            }
        }
    }

    @Override
    public String getDescription() {
        return "You see a monster with " + location.getCurrentEnemy().getHealth() + " hp";
    }
}
