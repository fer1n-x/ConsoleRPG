package ru.ferin.consolerpg.scene.scenes;

import ru.ferin.consolerpg.core.ConsoleRPG;
import ru.ferin.consolerpg.scene.Action;
import ru.ferin.consolerpg.scene.Scene;

public class SceneDefeatAllEnemies extends Scene {
    @Override
    public void preInit() {

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

                return new Action.Result("Moving you to next scene", true);
            }
        });
    }

    @Override
    public String getDescription() {
        return "You defeated all enemies.";
    }
}
