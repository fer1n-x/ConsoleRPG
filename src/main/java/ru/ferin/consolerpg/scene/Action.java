package ru.ferin.consolerpg.scene;

public abstract class Action {
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