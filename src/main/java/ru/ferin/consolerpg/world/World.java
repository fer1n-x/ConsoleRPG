package ru.ferin.consolerpg.world;

import ru.ferin.consolerpg.world.locations.LocationBase;

//Гайд как поехать крышей: сплагиатить концепцию игрового мира из майна
//Благо я забил на эту идею
public class World {
    LocationBase currentLocation;
    public World(LocationBase location) {
        this.currentLocation = location;
    }
    public void setCurrentLocation(LocationBase location) {
        this.currentLocation = location;
    }
    public LocationBase getCurrentLocation() {
        return this.currentLocation;
    }
}
