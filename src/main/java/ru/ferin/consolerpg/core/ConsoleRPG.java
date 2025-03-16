package ru.ferin.consolerpg.core;

import ru.ferin.consolerpg.entity.EntityPlayer;
import ru.ferin.consolerpg.handler.ConsoleInputHandler;
import ru.ferin.consolerpg.handler.SaveHandler;
import ru.ferin.consolerpg.scene.Action;
import ru.ferin.consolerpg.scene.Scene;
import ru.ferin.consolerpg.scene.scenes.SceneMainMenu;
import ru.ferin.consolerpg.world.World;

import java.util.Scanner;

import static ru.ferin.consolerpg.command.CommandBase.registerCommands;
import static ru.ferin.consolerpg.util.SOut.*;

public class ConsoleRPG {

    private boolean isRunning = true;
    private static ConsoleRPG consoleRPG;
    private final ConsoleInputHandler consoleInputHandler;
    private World world;
    private EntityPlayer player;
    private Scene currentScene = null;
    public ConsoleRPG(
            //TODO: String playerName
    ) {
        consoleRPG = this;
        consoleInputHandler = new ConsoleInputHandler();
    }

    /**
     * Предварительная настройка экземпляра игры
     */
    public void init()
    {
        println("ConsoleRPG v"+Info.VERSION);
        registerCommands();
        runLoop();
    }

    /**
     * Цикличный процесс обработки ввода в консоль
     */
    @SuppressWarnings("Duplicates")
    public void runLoop()
    {
        /*
        Изначально я собирался разделить общую логику игры на клиентскую (интерфейс, звук, etc) и серверную (мир, мобы, события etc)
        Но поскольку это текстовая игра, и у неё не может быть понятия "асинхронно работающий сервер" (т.к. любое событие происходит только после ввода команды/ключевой фразы),
        я отказался от этой идеи.
        */
        /*
        А еще я хрен знает, зачем я создал экземпляр Thread вообще.
        По крайней мере я уже не помню.
         */
        Thread mainThread = new Thread(() -> {
            while (isRunning) {
                if (currentScene == null) currentScene = new SceneMainMenu();
                print("============================\n");
                currentScene.preInit();
                print(currentScene.getContent());
                Scanner scanner = new Scanner(System.in);
                String input = scanner.nextLine();
                print("============================\n");
                print("----------------------------\n");
                consoleInputHandler.handleInput(input);
                print("----------------------------\n");
            }
        });
        mainThread.setName("Main Thread");
        mainThread.start();
    }

    /**
     * Остановить процесс игры
     */
    public void turnOff()
    {
        Main.EXECUTOR.execute(this::save);
        isRunning = false;
    }

    /**
     * Делаем вид, что разбираемся в ООП
     * @return экземпляр игры
     */
    public static ConsoleRPG getInstance()
    {
        if (consoleRPG == null) throw new IllegalStateException("ConsoleRPG is not initialized");
        return consoleRPG;
    }

    private void save() {
        SaveHandler.saveWorld(world);
    }
    public void excuteAction(int number) {

        Action.Result result = currentScene.executeAction(number);
        println(result.message);
    }
    public void setCurrentScene(Scene scene) {
        this.currentScene = scene;
    }
    public EntityPlayer getPlayer() {
        return player;
    }
    public void setPlayer(EntityPlayer player) {
        this.player = player;
    }

    public World getWorld() {
        return world;
    }

    public void setWorld(World world) {
        this.world = world;
    }
}
