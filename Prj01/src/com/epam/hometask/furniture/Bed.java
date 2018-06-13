package com.epam.hometask.furniture;

/**
 * Class, which contains info about bed
 */
public class Bed extends Furniture {

    public Bed(String name, int upArea) {
        super(name, upArea);
    }

    public Bed(String name, int downArea, int upArea) {
        super(name, downArea, upArea);
    }
}
