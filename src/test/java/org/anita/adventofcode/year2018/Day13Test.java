package org.anita.adventofcode.year2018;

import org.anita.adventofcode.structures.Position2D;
import org.anita.adventofcode.util.FileUtils;
import org.junit.Assert;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

public class Day13Test {

    @Test
    public void task1Test1() throws IOException {
        InputStream inputStream = getClass().getResourceAsStream("/test1carts.txt");
        List<String> map = FileUtils.readStringsLineByLine(inputStream);

        Assert.assertEquals(new Position2D(0, 3), new Day13(map).task1());
    }

    @Test
    public void task1Test2() throws IOException {
        InputStream inputStream = getClass().getResourceAsStream("/test2carts.txt");
        List<String> map = FileUtils.readStringsLineByLine(inputStream);

        Assert.assertEquals(new Position2D(7, 3), new Day13(map).task1());
    }

    @Test
    public void task1() throws IOException {
        InputStream inputStream = getClass().getResourceAsStream("/carts.txt");
        List<String> map = FileUtils.readStringsLineByLine(inputStream);

        Assert.assertEquals(new Position2D(119, 41), new Day13(map).task1());
    }

    @Test
    public void task2Test1() throws IOException {
        InputStream inputStream = getClass().getResourceAsStream("/test3carts.txt");
        List<String> map = FileUtils.readStringsLineByLine(inputStream);

        Assert.assertEquals(new Position2D(6, 4), new Day13(map).task2());
    }

    @Test
    public void task2() throws IOException {
        InputStream inputStream = getClass().getResourceAsStream("/carts.txt");
        List<String> map = FileUtils.readStringsLineByLine(inputStream);

        Assert.assertEquals(new Position2D(45, 136), new Day13(map).task2());
    }

}
