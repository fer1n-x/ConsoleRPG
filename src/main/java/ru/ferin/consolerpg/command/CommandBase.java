package ru.ferin.consolerpg.command;

import ru.ferin.consolerpg.core.ConsoleRPG;

import java.util.ArrayList;
import java.util.List;

//TODO 14.03.2025 Я УЖЕ ЗАБЫЛ ЗАЧЕМ ЗАВЕЗ СЮДА КОМАНДЫ, ААААААААААААААААААААААААААААААААААААААААААААААААААААААААА
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
