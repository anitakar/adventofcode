package org.anita.adventofcode.year2019;

import org.anita.adventofcode.util.FileUtils;
import org.junit.Assert;
import org.junit.Test;

import java.io.IOException;
import java.net.URISyntaxException;

public class Day5Test {

    @Test
    public void task1Test1() {
        Assert.assertArrayEquals(new long[]{17}, new IntCodeComputer().interpret(new long[]{3,0,4,0,99}, new long[]{17}));
    }

    @Test
    public void task1Test2() throws IOException, URISyntaxException {
        Assert.assertArrayEquals(new long[]{}, new IntCodeComputer().interpret(new long[]{1002,4,3,4,33}, new long[]{1}));
    }

    @Test
    public void task1Test3() throws IOException, URISyntaxException {
        Assert.assertArrayEquals(new long[]{}, new IntCodeComputer().interpret(new long[]{1101,100,-1,4,0}, new long[]{1}));
    }

    @Test
    public void task1() throws IOException, URISyntaxException {
        long[] regs = FileUtils.readLongArrayFromSingleLine("/2019_5.txt", ",");

        Assert.assertArrayEquals(new long[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 9006673}, new IntCodeComputer().interpret(regs, new long[]{1}));
    }

    @Test
    public void task2() throws IOException, URISyntaxException {
        long[] regs = FileUtils.readLongArrayFromSingleLine("/2019_5.txt", ",");

        Assert.assertArrayEquals(new long[]{3629692}, new IntCodeComputer().interpret(regs, new long[]{5}));
    }
}
