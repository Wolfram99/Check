package main.java.ru.clevertec.check.service.print;

public class WriteToFileAndConsole implements Logger {
    private Logger wtf = new WriteToFile();
    private final Logger wtc = new WriteToConsole();

    public WriteToFileAndConsole() {
    }

    public WriteToFileAndConsole(Logger wtf) {
        this.wtf = wtf;
    }

    @Override
    public void write(String message) {
        wtc.write(message);
        wtf.write(message);
    }
}
