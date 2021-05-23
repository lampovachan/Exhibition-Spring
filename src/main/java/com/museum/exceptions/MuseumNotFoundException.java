package com.museum.exceptions;

public class MuseumNotFoundException extends RuntimeException {
    public MuseumNotFoundException(String exception) {
        super(exception);
    }
}
