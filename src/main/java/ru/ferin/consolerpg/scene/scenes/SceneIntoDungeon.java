package ru.ferin.consolerpg.scene.scenes;

import ru.ferin.consolerpg.core.ConsoleRPG;
import ru.ferin.consolerpg.entity.EntityEnemy;
import ru.ferin.consolerpg.scene.Action;
import ru.ferin.consolerpg.scene.Scene;
import ru.ferin.consolerpg.world.locations.LocationDungeon;

public class SceneIntoDungeon extends Scene {
    LocationDungeon location;
    public SceneIntoDungeon(LocationDungeon location) {
        this.location = location;
    }
    @Override
    public void initActions() {
        actions.add(new Action() {

            @Override
            public String getDescription() {
                return "Attack it with " + ConsoleRPG.getInstance().getPlayer().getAttackStrength();
            }

            @Override
            public Result execute() {
                return null;
            }
        });
    }

    @Override
    public String getDescription() {
        if (location.getCurrentEnemy() == null) {
            if (location.isEnemiesLeft()) {
                return "All enemies are dead";
            } else {
                location.switchEnemy();
            }
        }
        return "You see a monster with " + location.getCurrentEnemy().getHealth() + " hp";
    }
}
