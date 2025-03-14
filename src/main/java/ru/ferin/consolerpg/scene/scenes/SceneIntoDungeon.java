package ru.ferin.consolerpg.scene.scenes;

import ru.ferin.consolerpg.core.ConsoleRPG;
import ru.ferin.consolerpg.scene.Action;
import ru.ferin.consolerpg.scene.Scene;
import ru.ferin.consolerpg.world.locations.LocationBase;

public class SceneIntoDungeon extends Scene {
    LocationBase location = ConsoleRPG.getInstance().getWorld().getCurrentLocation();
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
                        return new Result("Enemy attacked. He has " + location.getCurrentEnemy().getHealth() + " hp", true);
                    }
                });
            } else {
                actions.add(new Action() {

                    @Override
                    public String getDescription() {
                        return "Switch to the next enemy";
                    }

                    @Override
                    public Result execute() {
                        location.switchEnemy();
                        return new Result("Switched to the next enemy", true);
                    }
                });
            }
        } else {
            ConsoleRPG.getInstance().setCurrentScene(new SceneDefeatAllEnemies());
        }
    }

    @Override
    public String getDescription() {
        if (location.getCurrentEnemy() == null) location.switchEnemy();
        if (location.getCurrentEnemy() == null) {
            ConsoleRPG.getInstance().setCurrentScene(new SceneDefeatAllEnemies());
            return "";
        }
        return "You see a monster with " + location.getCurrentEnemy().getHealth() + " hp";
    }
}
