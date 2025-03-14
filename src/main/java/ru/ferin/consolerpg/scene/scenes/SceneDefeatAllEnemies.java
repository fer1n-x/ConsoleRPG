package ru.ferin.consolerpg.scene.scenes;

import ru.ferin.consolerpg.core.ConsoleRPG;
import ru.ferin.consolerpg.scene.Scene;

public class SceneDefeatAllEnemies extends Scene {
    @Override
    public void initActions() {

    }

    @Override
    public String getDescription() {
        return "You defeated all enemies. Move to the next level ("+ ConsoleRPG.getInstance().getPlayer().getLvl()+1+")?";
    }
}
