package main.java.ru.clevertec.check.service.print;

public class WriteToConsole implements Logger {

    public void write(String message) {
        System.out.println(message);
    }
}
