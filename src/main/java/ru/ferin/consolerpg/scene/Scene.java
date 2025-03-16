package ru.ferin.consolerpg.scene;

import ru.ferin.consolerpg.core.ConsoleRPG;
import ru.ferin.consolerpg.world.locations.LocationBase;

import java.util.ArrayList;
import java.util.List;

public abstract class Scene {
    protected static final ConsoleRPG consoleRPG = ConsoleRPG.getInstance();
    protected LocationBase location = consoleRPG.getWorld() == null ? null : consoleRPG.getWorld().getCurrentLocation();
    protected List<Action> actions = new ArrayList<>();

    public String getActionsListAsString() {
        if (!actions.isEmpty()) actions.clear();
        initActions();
        int id = 1;
        ListBuilder listBuilder = new ListBuilder();
        for (Action action : actions) {
            listBuilder.add(action.getDescription());
            action.id = id++;
        }
        return "\n"+listBuilder.build();
    }
    public Action.Result executeAction(int number) {
        if (actions.size() < number) {
            return new Action.Result("There is no action with id " + number + ".", false);
        }
        Action action = actions.get(number - 1);
        return action.execute();
    }

    public void preInit() {
        //noop
    }
    public abstract void initActions();
    public abstract String getDescription();
    public String getContent() {
        return getDescription() + getActionsListAsString();
    }


}
