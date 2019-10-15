package org.anita.adventofcode.year2015;

import org.anita.adventofcode.util.FileUtils;
import org.junit.Assert;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

public class Day17Test {

    @Test
    public void example() throws IOException {
        InputStream inputStream = getClass().getResourceAsStream("/2015_17_1.txt");
        List<Integer> containers = FileUtils.readIntsLineByLine(inputStream);

        int combinations = Day17.bruteForce(containers, 25);
        Assert.assertEquals(4, combinations);
    }

    @Test
    public void test() throws IOException {
        InputStream inputStream = getClass().getResourceAsStream("/2015_17_2.txt");
        List<Integer> containers = FileUtils.readIntsLineByLine(inputStream);

        int combinations = Day17.bruteForce(containers, 150);
        Assert.assertEquals(1638, combinations);
    }

    @Test
    public void examplePart2() throws IOException {
        InputStream inputStream = getClass().getResourceAsStream("/2015_17_1.txt");
        List<Integer> containers = FileUtils.readIntsLineByLine(inputStream);

        long numberOfCombinations = Day17.bruteForceCombinations(containers, 25);

        Assert.assertEquals(3, numberOfCombinations);
    }

    @Test
    public void testPart2() throws IOException {
        InputStream inputStream = getClass().getResourceAsStream("/2015_17_2.txt");
        List<Integer> containers = FileUtils.readIntsLineByLine(inputStream);

        long numberOfCombinations = Day17.bruteForceCombinations(containers, 150);

        Assert.assertEquals(17, numberOfCombinations);
    }
}
