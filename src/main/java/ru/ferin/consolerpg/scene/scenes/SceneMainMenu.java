package ru.ferin.consolerpg.scene.scenes;

import ru.ferin.consolerpg.entity.EntityPlayer;
import ru.ferin.consolerpg.data.SaveHandler;
import ru.ferin.consolerpg.scene.Action;
import ru.ferin.consolerpg.scene.Scene;

public class SceneMainMenu extends Scene {


    @Override
    public void preInit() {

    }

    @Override
    public void initActions() {
        actions.add(new Action() {
            @Override
            public String getDescription() {
                return "New game";
            }

            @Override
            public Result execute() {
                consoleRPG.setPlayer(new EntityPlayer(1, 100, 2, 0));
                consoleRPG.setCurrentScene(new SceneEnterIntoDungeon());
                return new Result("Created new game", true);
            }
        });
        String lastSave = SaveHandler.getLastSaveDate();
        if (!lastSave.isEmpty()) {
            actions.add(new Action() {
                @Override
                public String getDescription() {
                    return "Load last game: " + lastSave;
                }

                @Override
                public Action.Result execute() {
                    SaveHandler.loadSave();
                    consoleRPG.setCurrentScene(new SceneEnterIntoDungeon());
                    return new Action.Result("Loaded last game", true);
                }
            });
        }
    }

    @Override
    public String getDescription() {
        return "Main menu";
    }
}
