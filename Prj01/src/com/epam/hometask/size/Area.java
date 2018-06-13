package com.epam.hometask.size;

/**
 * Class, which contains info about furniture area
 */
public class Area {
    /**
     * area in squeezed size
     */
    private int downArea = 0;
    /**
     * area in full size
     */
    private int upArea = 0;

    public Area(int downArea, int upArea) {
        if (downArea < 0) {
            this.downArea = 0;
        }
        else {
            this.downArea = downArea;
        }
        if (upArea < 0) {
            this.upArea = 0;
        }
        else {
            this.upArea = upArea;
        }
        if (this.upArea < this.downArea) {
            this.upArea = this.downArea;
        }

    }

    public Area(int upArea) {
        if (upArea < 0) {
            this.upArea = 0;
        }
        else {
            this.upArea = upArea;
        }

    }

    public int getDownArea() {
        return this.downArea;
    }

    public int getUpArea() {
        return this.upArea;
    }

    public void addDownArea(int downArea) {
        this.downArea += downArea;
    }

    public void addUpArea(int upArea) {
        this.upArea += upArea;
    }

}
