package com.epam.hometask.furniture;

import com.epam.hometask.furniture.Furniture;

public class Table extends Furniture {

    public Table(String name, int upArea){
        super(name, upArea);
    }

    public Table(String name, int downArea, int upArea){
        super(name, downArea, upArea);
    }
}
