package com.epam.hometask.exceptions;

public class IlluminanceTooMuchException extends Exception {

    public IlluminanceTooMuchException(){
        super("Light must be in range 300-4000");
    }
}
