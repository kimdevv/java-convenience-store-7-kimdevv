package store.utility;

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
            ExceptionThrower.throwing(ExceptionEnum.FILE_NOT_FOUND);
            return null;
        }
    }

    public static String readLine(BufferedReader bufferedReader) {
        try {
            return bufferedReader.readLine();
        } catch (IOException exception) {
            ExceptionThrower.throwing(ExceptionEnum.READ_LINE_ERROR);
            return null;
        }
    }
}
