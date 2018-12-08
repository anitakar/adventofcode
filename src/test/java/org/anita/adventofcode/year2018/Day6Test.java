package org.anita.adventofcode.year2018;

import org.anita.adventofcode.util.FileUtils;
import org.junit.Assert;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;
import java.util.List;

public class Day6Test {

    List<Day6.Point> testPoints = Arrays.asList(
            new Day6.Point(1, 1),
            new Day6.Point(1, 6),
            new Day6.Point(8, 3),
            new Day6.Point(3, 4),
            new Day6.Point(5, 5),
            new Day6.Point(8, 9)
    );

    @Test
    public void task1Test1() {
        Assert.assertEquals(17, new Day6().task1(testPoints));
    }

    @Test
    public void task1() throws IOException {
        Day6 day6 = new Day6();
        InputStream inputStream = getClass().getResourceAsStream("/coordinates.txt");
        List<Day6.Point> points = FileUtils.readElementsLineByLine(inputStream, day6::parsePoint);

        Assert.assertEquals(3933, new Day6().task1(points));
    }

    @Test
    public void task2Test1() {
        Assert.assertEquals(16, new Day6().task2(testPoints, 32));
    }

    @Test
    public void task2() throws IOException {
        Day6 day6 = new Day6();
        InputStream inputStream = getClass().getResourceAsStream("/coordinates.txt");
        List<Day6.Point> points = FileUtils.readElementsLineByLine(inputStream, day6::parsePoint);

        Assert.assertEquals(41145, new Day6().task2(points, 10000));
    }
}
