package ru.clevertec.check.service.FormationResult;

import main.java.ru.clevertec.check.service.print.Logger;

import java.util.Objects;

public class ErrorReceipt extends Receipt {
    private final Logger logger;
    private String message;

    @Override
    public void receiptFormation() {
        logger.write("ERROR" + "\n" + getMessage());
    }

    public ErrorReceipt(Logger logger, String message) {
        this.logger = logger;
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ErrorReceipt that = (ErrorReceipt) o;
        return message.equals(that.message);
    }

    @Override
    public int hashCode() {
        return Objects.hash(message);
    }

    @Override
    public String toString() {
        return "ErrorReceipt{" +
                "message='" + message + '\'' +
                "} " + super.toString();
    }

    public static ErrorReceiptBuilder builder(){
        return new ErrorReceiptBuilder();
    }

    public static class ErrorReceiptBuilder{
        private Logger logger;
        private String message;

        public ErrorReceiptBuilder logger(Logger logger){
            this.logger = logger;
            return this;
        }

        public ErrorReceiptBuilder message(String message){
            this.message = message;
            return this;
        }

        public ErrorReceipt build(){
            return new ErrorReceipt(logger, message);
        }

    }

}
