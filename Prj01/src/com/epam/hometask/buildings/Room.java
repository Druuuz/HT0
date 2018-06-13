package com.epam.hometask.buildings;

import com.epam.hometask.exceptions.IlluminanceTooMuchException;
import com.epam.hometask.exceptions.SpaceUsageTooMuchException;
import com.epam.hometask.size.Area;
import com.epam.hometask.furniture.Furniture;
import com.epam.hometask.furniture.Lightbulb;

import java.util.ArrayList;

/**
 * This class is room, which contains area of room, list of furniture, list of lightbulbs, number of windows in room
 */
public class Room {

    private int area;
    private ArrayList<Furniture> listOfFurniture;
    private ArrayList<Lightbulb> listOfLightbuln;
    private int numOfWindows;
    private int lightPerWindow = 700;

    /**
     * add lightvulb in room
     * @param lightbulb is lightbulb, which you want to add in room
     * @throws IlluminanceTooMuchException when total light in room not in range 300-4000
     */
    public void add(Lightbulb lightbulb) throws IlluminanceTooMuchException {
        if (((lightbulb.getLight() + getTotalLight()) >= 300) && ((lightbulb.getLight() + getTotalLight()) <= 4000)) {
            listOfLightbuln.add(lightbulb);
        }
        else {
            throw new IlluminanceTooMuchException();
        }
    }

    /**
     * add furniture in room
     * @param furniture is furniture, which you want to add in room
     * @throws SpaceUsageTooMuchException when area of furnirure in room > 70% area of room
     */
    public void add(Furniture furniture) throws SpaceUsageTooMuchException {
        if (100 * ((getTotalArea().getUpArea() + furniture.getArea().getUpArea()) / area) < 70) {
            listOfFurniture.add(furniture);
        }
        else {
            throw new SpaceUsageTooMuchException();
        }
    }

    /**
     * Constructor of room
     * @param area is area of room
     * @param numOfWindows is number of windows in rooms
     */
    public Room(int area, int numOfWindows) {
        listOfFurniture = new ArrayList<>();
        listOfLightbuln = new ArrayList<>();
        this.numOfWindows = numOfWindows;
        this.area = area;

    }

    /**
     * transform room object to string
     * @return room in string format
     */
    @Override
    public String toString() {
        int totalLight = getTotalLight();
        StringBuilder stringLight = new StringBuilder("  Освещенность = " + totalLight +
                "( " + numOfWindows + " окна по " + lightPerWindow + ", ");
        if (listOfLightbuln.size() != 0) {
            stringLight.append("лампочки: ");
            for (Lightbulb lightbulb : listOfLightbuln) {
                stringLight.append(lightbulb.getLight() + "лк ");
            }
        }
        else {
            stringLight.append("лампочек нет");
        }
        stringLight.append(")");

        StringBuilder stringFurniture;
        StringBuilder stringArea;
        if (listOfFurniture.size() != 0) {
            stringArea = new StringBuilder("\n  Площадь = " + area + "м^2" + "( занято ");
            if (getTotalArea().getDownArea() == 0) {
                stringArea.append(getTotalArea().getUpArea());
            }
            else {
                stringArea.append("от " + getTotalArea().getDownArea() + "-" + getTotalArea().getUpArea());
            }
            double procent = 100 * (area - getTotalArea().getUpArea()) / area;
            stringArea.append("м^2 гарантированно свободно " + (area - getTotalArea().getUpArea()) +
                    "м^2 или " + String.format("%.2f", procent) + "%)\n");

            stringFurniture = new StringBuilder(" Мебель:\n");
            for (Furniture furniture : listOfFurniture) {
                if (furniture.getArea().getDownArea() == 0) {
                    stringFurniture.append("   " + furniture.getName() + "(площадь " + furniture.getArea().getUpArea() + "м^2) \n");
                }
                else {
                    stringFurniture.append("   " + furniture.getName() + "(площадь от " + furniture.getArea().getDownArea() + " до " + furniture.getArea().getUpArea() + "м^2) \n");
                }
            }
        }
        else {
            stringArea = new StringBuilder("\n  Площадь = " + area + "м^2" + "(свободно 100%)\n");
            stringFurniture = new StringBuilder("  Мебели нет");
        }

        return stringLight.append(stringArea).append(stringFurniture).toString();

    }

    /**
     * get total light in room
     * @return total light in room
     */
    private int getTotalLight() {
        int totalLight = 0;
        for (Lightbulb lightbulb : listOfLightbuln) {
            totalLight += lightbulb.getLight();
        }
        return totalLight + (lightPerWindow * numOfWindows);
    }

    /**
     * get total area of furniture in room
     * @return area object, which contains info about total furniture area in room
     */
    private Area getTotalArea() {
        int downArea = 0;
        int upArea = 0;
        for (Furniture furniture : listOfFurniture) {
            if (furniture.getArea().getDownArea() == 0) {
                upArea += furniture.getArea().getUpArea();
                downArea += furniture.getArea().getUpArea();
            }
            else {
                upArea += furniture.getArea().getUpArea();
                downArea += furniture.getArea().getDownArea();
            }
        }
        if (downArea == upArea) {
            return new Area(upArea);
        }
        else {
            return new Area(downArea, upArea);
        }
    }

}
