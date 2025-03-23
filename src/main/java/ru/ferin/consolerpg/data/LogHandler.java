package ru.ferin.consolerpg.data;

import java.util.ArrayList;
import java.util.List;

public class LogHandler {
    public static List<LogState> logList = new ArrayList<>();
    public static void writeLog(LogState.LogType type, double value) {
        logList.add(new LogState(type, value, System.currentTimeMillis()));
    }

}
