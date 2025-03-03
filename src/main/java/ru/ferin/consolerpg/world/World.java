package ru.ferin.consolerpg.world;

import ru.ferin.consolerpg.entity.Entity;
import ru.ferin.consolerpg.world.locations.LocationBase;

import java.util.ArrayList;
import java.util.List;

//Гайд как поехать крышей: сплагиатить концепцию игрового мира из майна
//Благо я забил на эту идею
public class World {
    LocationBase currentLocation;
    List<Entity> entities = new ArrayList<>();
    public World(int level) {

    }
    public void createEntityInWorld(Entity entity) {

    }
}
