package ru.ferin.consolerpg.scene.scenes;

import ru.ferin.consolerpg.core.ConsoleRPG;
import ru.ferin.consolerpg.data.SQLiteManager;
import ru.ferin.consolerpg.data.SaveState;
import ru.ferin.consolerpg.entity.EntityPlayer;
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
        if (location.isEnemiesLeft())
            consoleRPG.setCurrentScene(new SceneDefeatAllEnemies());
        else if (location.isCurrentEnemyDead() && !location.isEnemiesLeft())
            consoleRPG.setCurrentScene(new SceneDefeatEnemy());
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
