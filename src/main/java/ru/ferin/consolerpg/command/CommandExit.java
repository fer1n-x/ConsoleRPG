package ru.ferin.consolerpg.command;

import ru.ferin.consolerpg.core.ConsoleRPG;
import ru.ferin.consolerpg.util.SOut;

public class CommandExit extends CommandBase{
    @Override
    public void execute(ConsoleRPG consoleRPG) {
        SOut.println("Thanks for playing!");
        consoleRPG.turnOff();
    }

    @Override
    public String getName() {
        return "exit";
    }
}
