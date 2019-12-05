package org.anita.adventofcode.year2019;

import org.anita.adventofcode.util.FileUtils;
import org.junit.Assert;
import org.junit.Test;

import java.io.IOException;
import java.net.URISyntaxException;

public class Day5Test {

    @Test
    public void task1Test1() throws IOException, URISyntaxException {
        Assert.assertEquals("17;", new Day5().interpret(new int[]{3,0,4,0,99}, 17));
    }

    @Test
    public void task1Test2() throws IOException, URISyntaxException {
        Assert.assertEquals("", new Day5().interpret(new int[]{1002,4,3,4,33}, 1));
    }

    @Test
    public void task1Test3() throws IOException, URISyntaxException {
        Assert.assertEquals("", new Day5().interpret(new int[]{1101,100,-1,4,0}, 1));
    }

    @Test
    public void task1() throws IOException, URISyntaxException {
        int[] regs = FileUtils.readIntArrayFromSingleLine("/2019_5.txt", ",");

        Assert.assertEquals("0;0;0;0;0;0;0;0;0;9006673;", new Day5().interpret(regs, 1));
    }

    @Test
    public void task2() throws IOException, URISyntaxException {
        int[] regs = FileUtils.readIntArrayFromSingleLine("/2019_5.txt", ",");

        Assert.assertEquals("3629692;", new Day5().interpret(regs, 5));
    }
}
