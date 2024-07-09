package ru.clevertec.check.service.print;



import ru.clevertec.check.exception.InternalServerException;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Objects;

public class WriteToFile implements Logger {
    private final String DEFAULT_PATH_TO_FiLE = "result.csv";
    private String pathsToFile;

    public void write(String message) {

        try (BufferedWriter bw = new BufferedWriter(new PrintWriter(pathsToFile == null ? DEFAULT_PATH_TO_FiLE : pathsToFile))) {
            bw.write(message);
        } catch (IOException e) {
            try {
                throw new InternalServerException();
            } catch (InternalServerException ex) {
            }
        }
    }

    public WriteToFile() {}

    public WriteToFile(String pathsToFile) {
        this.pathsToFile = pathsToFile;
    }

    public String getPathsToFile() {
        return pathsToFile;
    }

    public void setPathsToFile(String pathsToFile) {
        this.pathsToFile = pathsToFile;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        WriteToFile that = (WriteToFile) o;
        return pathsToFile.equals(that.pathsToFile);
    }

    @Override
    public int hashCode() {
        return Objects.hash(pathsToFile);
    }

    @Override
    public String toString() {
        return "WriteToFile{" +
                "pathsToFile='" + pathsToFile + '\'' +
                '}';
    }
}


