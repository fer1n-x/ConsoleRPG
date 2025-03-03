package ru.ferin.consolerpg.command;

import ru.ferin.consolerpg.core.ConsoleRPG;

import java.util.ArrayList;
import java.util.List;

public abstract class CommandBase {
    public static List<CommandBase> commandList = new ArrayList<>();
    public abstract void execute(ConsoleRPG consoleRPG);
    public abstract String getName();
    private static void registerCommand(CommandBase command)
    {
        commandList.add(command);
    }
    public static void registerCommands()
    {
        registerCommand(new CommandExit());
    }
}
