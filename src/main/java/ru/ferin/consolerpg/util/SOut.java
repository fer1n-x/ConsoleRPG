package ru.ferin.consolerpg.util;

/**
 * Чтоб не писать каждый раз System.out.print(ln) при необходимости вывода текста в консоль
 */
public class SOut {
    public static void println(Object content) {System.out.println(content);};
    public static void print(Object content) {System.out.print(content);};
}
