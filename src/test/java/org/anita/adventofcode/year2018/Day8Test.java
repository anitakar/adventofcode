package org.anita.adventofcode.year2018;

import org.anita.adventofcode.util.FileUtils;
import org.junit.Assert;
import org.junit.Test;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.stream.IntStream;

public class Day8Test {

    @Test
    public void task1Test1() {
        Assert.assertEquals(138, new Day8().task1(IntStream.of(2, 3, 0, 3, 10, 11, 12, 1, 1, 0, 1, 99, 2, 1, 1, 2).iterator()));
    }

    @Test
    public void task1() throws IOException, URISyntaxException {
        Assert.assertEquals(41849, new Day8().task1(FileUtils.readIntsFromSingleLine("/tree.txt")));
    }

    @Test
    public void task2Test1() {
        Assert.assertEquals(66, new Day8().task2(IntStream.of(2, 3, 0, 3, 10, 11, 12, 1, 1, 0, 1, 99, 2, 1, 1, 2).iterator()));
    }

    @Test
    public void task2() throws IOException, URISyntaxException {
        Assert.assertEquals(32487, new Day8().task2(FileUtils.readIntsFromSingleLine("/tree.txt")));
    }
}
