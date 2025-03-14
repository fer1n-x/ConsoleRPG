package ru.ferin.consolerpg.world.locations;

import ru.ferin.consolerpg.entity.EntityEnemy;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public abstract class LocationBase {
    public String name;
    protected transient final Stack<EntityEnemy> enemies = new Stack<>();
    private transient EntityEnemy currentEnemy = null;
    public LocationBase(String name, int enemyCount, int lvl) {
        this.name = name;
        for (int i = 0; i < enemyCount; i++) {
            createEnemy(new EntityEnemy(100, lvl, 2, 0));
        }
    }
    public void createEnemy(EntityEnemy entity) {
        enemies.push(entity);
    }
    public EntityEnemy getCurrentEnemy() {
        return currentEnemy;
    }
    public void switchEnemy() {
        if (enemies.isEmpty()) currentEnemy = null;
        else currentEnemy = enemies.pop();
    }
    public void attackCurrentEnemy(double strength) {
        currentEnemy.attack(strength);
    }
    public boolean isCurrentEnemyDead() {
        return currentEnemy.getHealth() <= 0;
    }
    public boolean isEnemiesLeft() {
        return enemies.isEmpty();
    }

}
