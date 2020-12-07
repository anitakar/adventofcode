package org.anita.adventofcode.year2020;

import org.anita.adventofcode.util.FileUtils;
import org.junit.Assert;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;

public class Day3Test {

    @Test
    public void task1Test() throws IOException {
        Day3.MapReader mapReader = new Day3.MapReader();

        InputStream inputStream = getClass().getResourceAsStream("/2020_3_test.txt");
        FileUtils.readElementsLineByLine(inputStream, mapReader::readLine);
        Assert.assertEquals(7, new Day3().task1(mapReader.createMap(), 3, 1));
    }

    @Test
    public void task1() throws IOException {
        Day3.MapReader mapReader = new Day3.MapReader();

        InputStream inputStream = getClass().getResourceAsStream("/2020_3.txt");
        FileUtils.readElementsLineByLine(inputStream, mapReader::readLine);
        Assert.assertEquals(214, new Day3().task1(mapReader.createMap(), 3, 1));
    }

    @Test
    public void task2() throws IOException {
        Day3.MapReader mapReader = new Day3.MapReader();

        InputStream inputStream = getClass().getResourceAsStream("/2020_3.txt");
        FileUtils.readElementsLineByLine(inputStream, mapReader::readLine);
        Day3.Map map = mapReader.createMap();

        long trees1 = new Day3().task1(map, 1, 1);
        long trees2 = new Day3().task1(map, 3, 1);
        long trees3 = new Day3().task1(map, 5, 1);
        long trees4 = new Day3().task1(map, 7, 1);
        long trees5 = new Day3().task1(map, 1, 2);

        Assert.assertEquals(8336352024L, trees1 * trees2 * trees3 * trees4 * trees5);
    }
}
