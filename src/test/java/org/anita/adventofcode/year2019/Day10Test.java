package org.anita.adventofcode.year2019;

import org.anita.adventofcode.util.FileUtils;
import org.junit.Assert;
import org.junit.Test;

import java.io.IOException;
import java.util.List;

public class Day10Test {

    @Test
    public void task1Test1() throws IOException {
        List<String> map = FileUtils.readStringsLineByLine(getClass().getResourceAsStream("/2019_10_test1.txt"));
        Assert.assertEquals(8, new Day10().task1(map));
    }


    @Test
    public void task1() throws IOException {
        List<String> map = FileUtils.readStringsLineByLine(getClass().getResourceAsStream("/2019_10.txt"));
        Assert.assertEquals(334, new Day10().task1(map));
    }

    @Test
    public void task2Test1() throws IOException {
        List<String> map = FileUtils.readStringsLineByLine(getClass().getResourceAsStream("/2019_10_test2.txt"));
        Assert.assertEquals(802, new Day10().task2(map));
    }

    @Test
    public void task2() throws IOException {
        List<String> map = FileUtils.readStringsLineByLine(getClass().getResourceAsStream("/2019_10.txt"));
        Assert.assertEquals(1119, new Day10().task2(map));
    }
}
