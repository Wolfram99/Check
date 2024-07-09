package main.java.ru.clevertec.check.exception;

import main.java.ru.clevertec.check.service.builder.Director;

public class InternalServerException extends Exception {

    private static final String ERROR_MESSAGE = "INTERNAL SERVER";

    public InternalServerException() {
        super(ERROR_MESSAGE);
    }


}
