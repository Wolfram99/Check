package main.java.ru.clevertec.check.exception;

import main.java.ru.clevertec.check.service.builder.Director;

public class BadRequestException extends Exception {

    private static final String ERROR_MESSAGE = "BAD REQUEST";

    public BadRequestException() {
        super(ERROR_MESSAGE);

//        System.exit(0);
    }


}
