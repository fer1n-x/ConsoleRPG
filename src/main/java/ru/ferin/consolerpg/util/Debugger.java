package ru.ferin.consolerpg.util;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

//TODO: Оно не работает! Хотя должно
public class Debugger {
    private static final Logger logger = LogManager.getLogger();
    public static void info(Object content) {logger.info(content);}
    public static void debug(Object content) {logger.debug(content);}
    public static void warn(Object content) {logger.warn(content);}
    public static void error(Object content) {logger.error(content);}
}
