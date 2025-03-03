package ru.ferin.consolerpg.scene;

public class SceneCreatingGame extends Scene{
    @Override
    public void initActions() {
        actions.add(new Action() {
            @Override
            public String getDescription() {
                return null;
            }

            @Override
            public Result execute() {
                return null;
            }
        });
    }
}
