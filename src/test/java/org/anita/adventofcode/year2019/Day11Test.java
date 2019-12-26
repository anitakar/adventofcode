package org.anita.adventofcode.year2019;

import org.anita.adventofcode.util.FileUtils;
import org.junit.Assert;
import org.junit.Test;

import java.io.IOException;
import java.net.URISyntaxException;

public class Day11Test {

    @Test
    public void task1() throws IOException, URISyntaxException {
        long[] memory = FileUtils.readLongArrayFromSingleLine("/2019_11.txt", ",");
        Assert.assertEquals(2883, new Day11().task1(memory));
    }

    @Test
    public void task2() throws IOException, URISyntaxException {
        long[] memory = FileUtils.readLongArrayFromSingleLine("/2019_11.txt", ",");
        new Day11().task2(memory);
    }
}
