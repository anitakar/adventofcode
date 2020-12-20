package org.anita.adventofcode.year2020;

import org.anita.adventofcode.util.FileUtils;
import org.junit.Assert;
import org.junit.Test;

import java.util.List;

public class Day10Test {

    @Test
    public void task1Test() throws Exception {
        final List<Integer> jolts = FileUtils.readIntsLineByLine(getClass().getResourceAsStream("/2020_10_test.txt"));
        Assert.assertEquals(35, new Day10.JoltAdapterSequence().indicator(jolts));
    }

    @Test
    public void task1() throws Exception {
        final List<Integer> jolts = FileUtils.readIntsLineByLine(getClass().getResourceAsStream("/2020_10.txt"));
        Assert.assertEquals(2080, new Day10.JoltAdapterSequence().indicator(jolts));
    }
}
