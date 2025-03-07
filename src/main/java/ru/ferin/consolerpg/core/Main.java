package ru.ferin.consolerpg.core;


import ru.ferin.consolerpg.util.Debugger;

import java.util.Scanner;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Main {
    //03.03.2025. Я решил особо не заморачиваться, и максимально упростил логику всей игры. Весь мир отныне вертится вокруг игрока. Буквально, он и есть мир.
    //04.03.2025. ОНО НЕ РАБОТАЕТ НИХРЕНА!
    //07.03.2025. ОНО РАБОТАЕТ!
    public static final ExecutorService EXECUTOR = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors());
    public static void main(String[] args) {
        ConsoleRPG consoleRPG = new ConsoleRPG();
        consoleRPG.init();
    }
}