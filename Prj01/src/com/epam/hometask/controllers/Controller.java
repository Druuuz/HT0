package com.epam.hometask.controllers;

import com.epam.hometask.exceptions.IlluminanceTooMuchException;
import com.epam.hometask.exceptions.SpaceUsageTooMuchException;
import com.epam.hometask.furniture.Bed;
import com.epam.hometask.buildings.Building;
import com.epam.hometask.furniture.Chair;
import com.epam.hometask.furniture.Lightbulb;

/**
 * Main class, which control work with buildings
 */
public class Controller {

    public static void main(String[] args) {
        Building building = new Building("Здание 1");
        try {
            building.addRoom("Комната1", 505, 1);
            building.getRoom("Комната1").add(new Lightbulb(100));
            building.getRoom("Комната1").add(new Lightbulb(150));
            building.getRoom("Комната1").add(new Bed("мягкая кровать", 5, 10));
            building.getRoom("Комната1").add(new Chair("креслецо", 70));
            building.getRoom("Комната1").add(new Chair("креслеdddцо", 95));
        } catch (IlluminanceTooMuchException e) {
            System.out.println(e.getMessage());
        } catch (SpaceUsageTooMuchException e) {
            System.out.println(e.getMessage());
        }

        building.describe();

    }
}
