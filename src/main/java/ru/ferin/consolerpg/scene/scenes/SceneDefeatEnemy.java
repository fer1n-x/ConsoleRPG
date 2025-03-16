package ru.ferin.consolerpg.scene.scenes;

import ru.ferin.consolerpg.scene.Action;
import ru.ferin.consolerpg.scene.Scene;

public class SceneDefeatEnemy extends Scene {
    @Override
    public void preInit() {

    }

    @Override
    public void initActions() {
        actions.add(new Action() {

            @Override
            public String getDescription() {
                return "Switch to the next enemy";
            }

            @Override
            public Result execute() {
                location.nextEnemy();
                consoleRPG.setCurrentScene(new SceneIntoDungeon());
                return new Result("Switched to the next enemy", true);
            }
        });
    }

    @Override
    public String getDescription() {
        return "You defeated the enemy. Remain " + location.getEnemiesCount() + " enemies.";
    }
}
