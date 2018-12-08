package org.anita.adventofcode.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

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

    public static <T> List<T> readElementsLineByLine(InputStream inputStream, Function<String, T> lineParser) throws IOException {
        List<T> result = new ArrayList<>();
        try (BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream))) {
            String line;
            while((line = bufferedReader.readLine()) != null) {
                result.add(lineParser.apply(line));
            }
        }
        return result;
    }

    public static Iterator<Integer> readElementsFromLine(String resource) throws URISyntaxException, IOException {
        Stream<String> lines = Files.lines(Paths.get(FileUtils.class.getResource(resource).toURI()));
        String line = lines.collect(Collectors.toList()).get(0);

        return Arrays.stream(line.split(" ")).mapToInt(e -> Integer.parseInt(e)).iterator();
    }


}
