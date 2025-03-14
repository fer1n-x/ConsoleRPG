package ru.ferin.consolerpg.util;

public class GeneralUtils {
    public static int getRandomNumber(int min, int max) {
        return (int) (Math.random() * (max - min + 1) + min);
    }
}
