package ru.ferin.consolerpg.scene;

import ru.ferin.consolerpg.core.ConsoleRPG;

import java.util.ArrayList;
import java.util.List;

public abstract class Scene {
    private static final ConsoleRPG consoleRPG = ConsoleRPG.getInstance();
    public List<Action> actions = new ArrayList<>();

    public String getActionsListAsString() {
        if (actions.isEmpty()) {
            initActions();
        }
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

    public abstract void initActions();
    public abstract String getDescription();
    public String getContent() {
        return getDescription() + getActionsListAsString();
    }


}
