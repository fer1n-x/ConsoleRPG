package ru.ferin.consolerpg.handler;

import ru.ferin.consolerpg.command.CommandBase;
import ru.ferin.consolerpg.core.ConsoleRPG;
import ru.ferin.consolerpg.util.SOut;

public class ConsoleInputHandler {
    //Ссылка на экземпляр ConsoleRPG (aka синглтон)
    private final ConsoleRPG consoleRPG = ConsoleRPG.getInstance();
    public void handleInput(String input) {
        switch (input) {
            case "exit" -> consoleRPG.turnOff();
            default -> {
                input = input.replaceAll("\\D", "");
                int id;
                try {
                     id = Integer.parseInt(input);
                } catch (NumberFormatException e) {
                    id = -1;
                }
                if (id == -1)
                    SOut.println("Invalid input!");
                else
                    consoleRPG.excuteAction(Integer.parseInt(input));
            }
        }

        //TODO: Изначально тут стоял switch(input) {...}, но такая схема - вылитый монолит. Я сторонник модульных проектов, по этому я решил воспользоваться абстрактным классом (CommandBase) и его дочерними классами (классы-обработчики)
        //TODO: Оптимизировать поиск по списку команд (сделать бинарный поиск, либо модель данных типа дерево)
        //CommandBase.commandList.stream().filter(command -> command.getName().equals(input)).forEach(this::accept);
    }

    /* Вообще хз зачем я это сделал, просто код выше выглядит более чистым благодаря этому методу */
    private void accept(CommandBase command) {
        command.execute(consoleRPG);
    }
}
