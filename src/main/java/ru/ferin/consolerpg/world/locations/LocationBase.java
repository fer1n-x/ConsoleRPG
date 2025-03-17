package ru.ferin.consolerpg.world.locations;

import ru.ferin.consolerpg.entity.EntityEnemy;

import java.util.EmptyStackException;
import java.util.Stack;

public abstract class LocationBase {
    public String name;
    protected transient final Stack<EntityEnemy> enemies = new Stack<>();
    private transient EntityEnemy currentEnemy = null;
    public LocationBase(String name, int enemyCount, int lvl) {
        //Выбрасываем исключение, тк кол-во существ не может быть равно или меньше 0
        if (enemyCount < 1) throw new IllegalArgumentException("enemyCount must be greater than 0");
        this.name = name;
        enemyCount += lvl;
        //Создаем существ на основе переданных аргументов
        for (int i = 0; i < enemyCount; i++) {
            createEnemy(new EntityEnemy(100, lvl, 2, 0));
        }
        //Предотвращаем нулл в переменной
        currentEnemy = enemies.pop();
    }
    public void createEnemy(EntityEnemy entity) {
        enemies.push(entity);
    }
    public EntityEnemy getCurrentEnemy() {
        return currentEnemy;
    }
    public void nextEnemy() {
        try {
            currentEnemy = enemies.pop();
        } catch (EmptyStackException e) {
            currentEnemy = null;
        }
    }
    public void attackCurrentEnemy(double strength) {
        currentEnemy.attack(strength);
    }
    public boolean isCurrentEnemyDead() {
        if (currentEnemy == null) return false;
        return currentEnemy.getHealth() <= 0;
    }
    public boolean isEnemiesLeft() {
        return enemies.isEmpty();
    }
    public int getEnemiesCount() {
        return enemies.size();
    }

}
