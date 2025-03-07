package ru.ferin.consolerpg.scene.scenes;

import ru.ferin.consolerpg.core.ConsoleRPG;
import ru.ferin.consolerpg.entity.EntityPlayer;
import ru.ferin.consolerpg.handler.SaveHandler;
import ru.ferin.consolerpg.scene.Action;
import ru.ferin.consolerpg.scene.Scene;

public class SceneMainMenu extends Scene {


    @Override
    public void initActions() {
        String lastSaveName = SaveHandler.getLastSave();
        if (lastSaveName != "") {
            actions.add(new Action() {
                @Override
                public String getDescription() {
                    return "Load last game: " + lastSaveName;
                }

                @Override
                public Action.Result execute() {
                    return new Action.Result("pon", true);
                }
            });
        }
        actions.add(new Action() {
            @Override
            public String getDescription() {
                return "New game";
            }

            @Override
            public Result execute() {
                ConsoleRPG.getInstance().setPlayer(new EntityPlayer(100, 1));
                ConsoleRPG.getInstance().setCurrentScene(new SceneEnterIntoDungeon());
                return new Result("Created new game", true);
            }
        });
    }

    @Override
    public String getDescription() {
        return "Main menu";
    }
}
