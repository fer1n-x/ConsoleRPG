package ru.ferin.consolerpg.data;

public class DataManager {
    public synchronized static void save() {
        SaveHandler.saveProgress();
        SaveHandler.saveLogs();
    }
}
