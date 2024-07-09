package ru.clevertec.check.exception;


public class NotEnoughMoneyException extends Exception {
    private static final String ERROR_MESSAGE = "NOT ENOUGH MONEY";


    public NotEnoughMoneyException() {
        super(ERROR_MESSAGE);
    }


}
