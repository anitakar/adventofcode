package org.anita.adventofcode.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

public class FileUtils {

    public static List<Integer> readIntsLineByLine(InputStream inputStream) throws IOException {
        List<Integer> result = new ArrayList<>();
        try (BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream))) {
            String line;
            while((line = bufferedReader.readLine()) != null) {
                result.add(Integer.parseInt(line));
            }
        }
        return result;
    }

    public static List<String> readStringssLineByLine(InputStream inputStream) throws IOException {
        List<String> result = new ArrayList<>();
        try (BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream))) {
            String line;
            while((line = bufferedReader.readLine()) != null) {
                result.add(line);
            }
        }
        return result;
    }

    public static <T> List<T> readElements(InputStream inputStream, Function<String, T> lineParser) throws IOException {
        List<T> result = new ArrayList<>();
        try (BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream))) {
            String line;
            while((line = bufferedReader.readLine()) != null) {
                result.add(lineParser.apply(line));
            }
        }
        return result;
    }


}
