package org.anita.adventofcode.year2015;

import org.anita.adventofcode.util.FileUtils;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class Day2Test {

    private Day2 day2 = new Day2();

    @Test
    public void testWrapping() throws IOException {
        InputStream inputStream = getClass().getResourceAsStream("/2015_2.txt");
        List<int[]> dims = FileUtils.readElementsLineByLine(inputStream, s -> Stream.of(s.split("x")).mapToInt(Integer::parseInt).toArray());
        System.out.println(day2.wrapping(dims));
    }

    @Test
    public void testRibbon() throws IOException {
        InputStream inputStream = getClass().getResourceAsStream("/2015_2.txt");
        List<int[]> dims = FileUtils.readElementsLineByLine(inputStream, s -> Stream.of(s.split("x")).mapToInt(Integer::parseInt).toArray());
        System.out.println(day2.ribbon(dims));
    }


}
