package com.epam.hometask.furniture;

/**
 * Class, which contains info about lightbulb
 */
public class Lightbulb {

    private int light;

    public Lightbulb(int light) {
        if (light < 0){
            this.light = 0;
        }
        this.light = light;
    }

    public int getLight() {
        return this.light;
    }

}
