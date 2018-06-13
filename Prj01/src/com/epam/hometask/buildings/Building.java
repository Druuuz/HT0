package com.epam.hometask.buildings;

import com.epam.hometask.exceptions.IlluminanceTooMuchException;

import java.util.*;

/**
 * Class, which contains rooms and operations with them.
 */
public class Building {
    /**
     *  List of rooms in current Building
     */
    private HashMap<String, Room> listOfRooms;
    /**
     * Name of room
     */
    private String name;

    /**
     * Constructor of Building
     * @param name is Name of building
     */
    public Building(String name) {
        this.name = name;
        this.listOfRooms = new HashMap<>();
    }

    /**
     *
     * @param nameOfRoom name of room
     * @param area area of room
     * @param numOfWindows number of windows in room
     * @throws IlluminanceTooMuchException happen, when total light of windows not in 300-4000
     */
    public void addRoom(String nameOfRoom, int area, int numOfWindows) throws IlluminanceTooMuchException {
        if ((numOfWindows * 700) < 300 || (numOfWindows * 700) > 4000) {
            throw new IlluminanceTooMuchException();
        }
        listOfRooms.put(nameOfRoom, new Room(area, numOfWindows));
    }

    /**
     *
     * @param nameOfRoom name of room, which you want to get
     * @return room by name
     */
    public Room getRoom(String nameOfRoom) {
        return listOfRooms.get(nameOfRoom);
    }

    /**
     * write info about bulding and rooms
     */
    public void describe() {
        if (listOfRooms.size() != 0) {
            System.out.println(this.name + ":");
            Iterator<Map.Entry<String, Room>> iterator = listOfRooms.entrySet().iterator();

            while (iterator.hasNext()) {
                Map.Entry<String, Room> entry = iterator.next();
                System.out.println(" " + entry.getKey() + ":");
                System.out.println(entry.getValue().toString());
            }
        }
        else {
            System.out.println("В здании нет помещений");
        }


    }


}


