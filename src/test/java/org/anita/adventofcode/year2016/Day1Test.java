package org.anita.adventofcode.year2016;

import org.anita.adventofcode.util.FileUtils;
import org.junit.Assert;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;
import java.util.List;

public class Day1Test {

    @Test
    public void task1Example1() {
        Assert.assertEquals(5, new Day1().manhattanDistance(Arrays.asList("R2", "L3")));
    }

    @Test
    public void task1Example2() {
        Assert.assertEquals(2, new Day1().manhattanDistance(Arrays.asList("R2", "R2", "R2")));
    }

    @Test
    public void task1Example3() {
        Assert.assertEquals(12, new Day1().manhattanDistance(Arrays.asList("R5", "L5", "R5", "R3")));
    }

    @Test
    public void task1() throws IOException {
        InputStream inputStream = getClass().getResourceAsStream("/2016_1.txt");
        List<String> moves = Arrays.asList(FileUtils.readStringsLineByLine(inputStream).get(0).split(", "));
        Assert.assertEquals(332, new Day1().manhattanDistance(moves));
    }

    @Test
    public void task2Example1() {
        Assert.assertEquals(4, new Day1().manhattanDistanceFromVisitedTwice(Arrays.asList("R8", "R4", "R4", "R8")));
    }

    @Test
    public void task2() throws IOException {
        InputStream inputStream = getClass().getResourceAsStream("/2016_1.txt");
        List<String> moves = Arrays.asList(FileUtils.readStringsLineByLine(inputStream).get(0).split(", "));
        Assert.assertEquals(166, new Day1().manhattanDistanceFromVisitedTwice(moves));
    }
}
