package ru.ferin.consolerpg.scene;

import ru.ferin.consolerpg.core.ConsoleRPG;

import java.util.ArrayList;
import java.util.List;

public abstract class Scene {
    private static final ConsoleRPG consoleRPG = ConsoleRPG.getInstance();
    List<Action> actions = new ArrayList<>();

    public String getDescription() {
        if (actions.isEmpty()) {
            initActions();
        }
        int id = 1;
        ListBuilder listBuilder = new ListBuilder();
        for (Action action : actions) {
            listBuilder.add(action.getDescription());
            action.id = id++;
        }
        return listBuilder.build();
    }
    public Action.Result executeAction(int id) {
        if (actions.size() < id) {
            return new Action.Result("There is no action with id " + id + ".", false);
        }
        Action action = actions.get(id - 1);
        return action.execute();
    }

    public abstract void initActions();

    public static abstract class Action {
        public int id = -1;
        public abstract String getDescription();
        public abstract Result execute();
        public static class Result {
            public String message;
            public boolean isDone;
            public Result(String message, boolean isDone) {
                this.message = message;
                this.isDone = isDone;
            }
        }
    }
}
