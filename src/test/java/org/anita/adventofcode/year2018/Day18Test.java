package org.anita.adventofcode.year2018;

import org.anita.adventofcode.util.FileUtils;
import org.junit.Assert;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

public class Day18Test {

    @Test
    public void task1Test1() throws IOException {
        InputStream inputStream = getClass().getResourceAsStream("/testwood.txt");
        List<String> map = FileUtils.readStringsLineByLine(inputStream);

        Assert.assertEquals(1147, new Day18(map).task1(10));
    }

    @Test
    public void task1() throws IOException {
        InputStream inputStream = getClass().getResourceAsStream("/wood.txt");
        List<String> map = FileUtils.readStringsLineByLine(inputStream);

        Assert.assertEquals(574200, new Day18(map).task1(10));
    }

    @Test
    public void task2() throws IOException {
        InputStream inputStream = getClass().getResourceAsStream("/wood.txt");
        List<String> map = FileUtils.readStringsLineByLine(inputStream);

        Assert.assertEquals(209951, new Day18(map).task1(2000));
        Assert.assertEquals(211145, new Day18(map).task1(3000));
        Assert.assertEquals(203138, new Day18(map).task1(4000));
        Assert.assertEquals(216315, new Day18(map).task1(5000));
        Assert.assertEquals(201600, new Day18(map).task1(6000));
        Assert.assertEquals(205907, new Day18(map).task1(7000));
        Assert.assertEquals(211653, new Day18(map).task1(8000));
        Assert.assertEquals(209951, new Day18(map).task1(9000));
        Assert.assertEquals(211145, new Day18(map).task1(10000));
        Assert.assertEquals(203138, new Day18(map).task1(11000));
        Assert.assertEquals(216315, new Day18(map).task1(12000));
        Assert.assertEquals(201600, new Day18(map).task1(13000));
        Assert.assertEquals(205907, new Day18(map).task1(14000));
        Assert.assertEquals(211653, new Day18(map).task1(15000));
        Assert.assertEquals(209951, new Day18(map).task1(16000));

        Assert.assertEquals(6, ((1000000000 - 2000) / 1000) % 7);
    }
}
