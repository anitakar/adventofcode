package org.anita.adventofcode.year2015;

import org.anita.adventofcode.util.FileUtils;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.stream.Stream;

public class Day3Test {

    private Day3 day3 = new Day3();

    @Test
    public void testWithSanta() throws IOException {
        InputStream inputStream = getClass().getResourceAsStream("/2015_3.txt");
        String path = FileUtils.readStringsLineByLine(inputStream).get(0);
        System.out.println(day3.numUniqueHouses(path));
    }

    @Test
    public void testWithSantaAndRoboSanta() throws IOException {
        InputStream inputStream = getClass().getResourceAsStream("/2015_3.txt");
        String path = FileUtils.readStringsLineByLine(inputStream).get(0);
        System.out.println(day3.numUniqueHousesWithRobo(path));
    }


}
