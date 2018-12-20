package org.anita.adventofcode.year2018;

import org.anita.adventofcode.util.FileUtils;
import org.junit.Assert;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

public class Day17Test {

    @Test
    public void task1Test1() throws IOException {
        InputStream inputStream = getClass().getResourceAsStream("/testwater.txt");
        List<String> map = FileUtils.readStringsLineByLine(inputStream);

        Assert.assertEquals(57, new Day17(map).task1());
    }

    @Test
    public void task1Test2() throws IOException {
        InputStream inputStream = getClass().getResourceAsStream("/test2water.txt");
        List<String> map = FileUtils.readStringsLineByLine(inputStream);

        Assert.assertEquals(122, new Day17(map).task1());
    }

    @Test
    public void task1() throws IOException {
        InputStream inputStream = getClass().getResourceAsStream("/water.txt");
        List<String> map = FileUtils.readStringsLineByLine(inputStream);

        Assert.assertEquals(39877, new Day17(map).task1());
    }

    @Test
    public void task2Test1() throws IOException {
        InputStream inputStream = getClass().getResourceAsStream("/testwater.txt");
        List<String> map = FileUtils.readStringsLineByLine(inputStream);

        Assert.assertEquals(29, new Day17(map).task2());
    }

    @Test
    public void task2Test2() throws IOException {
        InputStream inputStream = getClass().getResourceAsStream("/test2water.txt");
        List<String> map = FileUtils.readStringsLineByLine(inputStream);

        Assert.assertEquals(73, new Day17(map).task2());
    }

    @Test
    public void task2() throws IOException {
        InputStream inputStream = getClass().getResourceAsStream("/water.txt");
        List<String> map = FileUtils.readStringsLineByLine(inputStream);

        Assert.assertEquals(33291, new Day17(map).task2());
    }
}
