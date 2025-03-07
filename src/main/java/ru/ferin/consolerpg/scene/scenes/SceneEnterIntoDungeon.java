package ru.ferin.consolerpg.scene.scenes;

import ru.ferin.consolerpg.core.ConsoleRPG;
import ru.ferin.consolerpg.scene.Action;
import ru.ferin.consolerpg.scene.Scene;
import ru.ferin.consolerpg.world.locations.LocationDungeon;

public class SceneEnterIntoDungeon extends Scene {
    boolean triedToDisagree = false;
    @Override
    public void initActions() {
        actions.clear();
        if (triedToDisagree) {
            actions.add(new Action() {

                @Override
                public String getDescription() {
                    return "Yes, my lord *enters the dungeon*";
                }

                @Override
                public Result execute() {
                    ConsoleRPG.getInstance().setCurrentScene(new SceneIntoDungeon(new LocationDungeon("Dungeon", 10, ConsoleRPG.getInstance().getPlayer().getLvl())));
                    return new Result("Good luck.", true);
                }
            });
        } else {
            actions.add(new Action() {

                @Override
                public String getDescription() {
                    return "Yes";
                }

                @Override
                public Result execute() {
                    ConsoleRPG.getInstance().setCurrentScene(new SceneIntoDungeon(new LocationDungeon("Dungeon", 10, ConsoleRPG.getInstance().getPlayer().getLvl())));
                    return new Result("Good luck", true);
                }
            });
            actions.add(new Action() {

                @Override
                public String getDescription() {
                    return "No";
                }

                @Override
                public Result execute() {
                    triedToDisagree = true;
                    return new Result("Nice try.", true);
                }
            });
        }

    }

    @Override
    public String getDescription() {
        if (triedToDisagree) {
            return "Cmon, you have no choice. Do you want to enter the dungeon?";
        } else {
            return "Your level is "+ ConsoleRPG.getInstance().getPlayer().getLvl()+". Do you want to enter the dungeon?";
        }
    }
}
