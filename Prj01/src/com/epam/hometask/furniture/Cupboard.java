package com.epam.hometask.furniture;

import com.epam.hometask.furniture.Furniture;

public class Cupboard extends Furniture {

    public Cupboard(String name, int upArea) {
        super(name, upArea);
    }

    public Cupboard(String name, int downArea, int upArea) {
        super(name, downArea, upArea);
    }
}
