package com.epam.hometask.furniture;

import com.epam.hometask.furniture.Furniture;

public class Chair extends Furniture {

    public Chair(String name, int upArea) {
        super(name, upArea);
    }

    public Chair(String name, int downArea, int upArea) {
        super(name, downArea, upArea);
    }
}
