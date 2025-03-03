package ru.ferin.consolerpg.scene;

import ru.ferin.consolerpg.handler.SaveHandler;

public class SceneMainMenu extends Scene {
    @Override
    public String getDescription() {
        return "Main menu\n" + super.getDescription();
    }

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
                return new Result("AHALAI MAHALAI", true);
            }
        });
    }
}
