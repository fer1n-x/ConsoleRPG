package ru.ferin.consolerpg.scene.scenes;

import ru.ferin.consolerpg.core.ConsoleRPG;
import ru.ferin.consolerpg.scene.Action;
import ru.ferin.consolerpg.scene.Scene;
import ru.ferin.consolerpg.util.WorldUtils;
import ru.ferin.consolerpg.world.World;
import ru.ferin.consolerpg.world.locations.LocationDungeon;

public class SceneEnterIntoDungeon extends Scene {
    boolean triedToDisagree = false;
    @Override
    public void initActions() {
        actions.clear();
        actions.add(new Action() {

            @Override
            public String getDescription() {
                return "Yes";
            }

            @Override
            public Result execute() {
                ConsoleRPG.getInstance().setWorld(new World(new LocationDungeon("Dungeon", 10, ConsoleRPG.getInstance().getPlayer().getLvl())));
                ConsoleRPG.getInstance().setCurrentScene(new SceneIntoDungeon());
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

    @Override
    public String getDescription() {
        StringBuilder sb = new StringBuilder();
        sb.append("Dungeon details:\n");
        sb.append("\tLevel: ").append(WorldUtils.getAvailableLvl()).append("\n");
        sb.append("\tEnemies count: ").append(WorldUtils.getAvailableEnemyCount()).append("\n");
        if (triedToDisagree) {
            sb.append("Cmon, you have no choice. Do you want to enter the dungeon?");
        } else {
            sb.append("Do you want to enter the dungeon?");
        }
        return sb.toString();
    }
}
