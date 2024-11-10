package store.utility.FileReader;

import store.enumerate.ExceptionEnum;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class BufferedReaderManager {
    public static BufferedReader getFileReaderFromPath(String filePath) {
        try {
            return new BufferedReader(new FileReader(filePath));
        } catch (FileNotFoundException exception) {
            throw new IllegalArgumentException(ExceptionEnum.FILE_NOT_FOUND.getMessage());
        }
    }

    public static String readLine(BufferedReader bufferedReader) {
        try {
            return bufferedReader.readLine();
        } catch (IOException exception) {
            throw new IllegalArgumentException(ExceptionEnum.READ_LINE_ERROR.getMessage());
        }
    }
}
