package com.epam.hometask.furniture;

import com.epam.hometask.size.Area;

public abstract class Furniture {

    private Area area;
    private String name;

    public Furniture(String name, int upArea) {
        this.area = new Area(upArea);
        this.name = name;
    }

    public Furniture(String name, int downArea, int upArea) {
        this.area = new Area(downArea, upArea);
        this.name = name;
    }

    public Area getArea() {
        return this.area;
    }

    public String getName() {
        return this.name;
    }
}
