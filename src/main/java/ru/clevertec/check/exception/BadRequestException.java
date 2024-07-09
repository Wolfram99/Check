package ru.clevertec.check.exception;

public class BadRequestException extends Exception {

    private static final String ERROR_MESSAGE = "BAD REQUEST";

    public BadRequestException() {
        super(ERROR_MESSAGE);

//        System.exit(0);
    }


}
