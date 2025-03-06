package ru.ferin.consolerpg.world.locations;

import ru.ferin.consolerpg.entity.EntityEnemy;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public abstract class LocationBase {
    public String name;
    private transient final Stack<EntityEnemy> enemies = new Stack<>();
    private transient EntityEnemy currentEnemy = null;
    public LocationBase(String name, int enemyCount, int lvl) {

    }
    public void createEnemy(EntityEnemy entity) {

    }
    public void switchEnemy() {
        currentEnemy = enemies.pop();
    }
    public void attackCurrentEnemy(double strength) {
        currentEnemy.attack(strength);
    }
}
