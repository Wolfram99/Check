package ru.clevertec.check.exception;


public class InternalServerException extends Exception {

    private static final String ERROR_MESSAGE = "INTERNAL SERVER";

    public InternalServerException() {
        super(ERROR_MESSAGE);
    }


}
