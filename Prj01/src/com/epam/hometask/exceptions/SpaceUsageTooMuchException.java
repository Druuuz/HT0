package com.epam.hometask.exceptions;

public class SpaceUsageTooMuchException extends Exception {

    public SpaceUsageTooMuchException() {
        super("Unable add more objects in room");
    }
}
