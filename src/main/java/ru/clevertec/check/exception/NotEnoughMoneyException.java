package main.java.ru.clevertec.check.exception;

import main.java.ru.clevertec.check.service.builder.Director;
import main.java.ru.clevertec.check.service.print.Logger;

public class NotEnoughMoneyException extends Exception {
    private static final String ERROR_MESSAGE = "NOT ENOUGH MONEY";


    public NotEnoughMoneyException() {
        super(ERROR_MESSAGE);
    }


}
