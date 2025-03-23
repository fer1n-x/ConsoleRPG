package ru.ferin.consolerpg.data;

import java.util.ArrayList;
import java.util.List;

public record LogState(LogType type, double value, long time) {
    public enum LogType {
        PLAYER_ATTACK,
        PLAYER_DAMAGE,
        PLAYER_DEATH,
        ENEMY_ATTACK,
        ENEMY_DAMAGE,
        ENEMY_DEATH,
        APPLIED_POTION,
        LEVEL_UP
    };
}
