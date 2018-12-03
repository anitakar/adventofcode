package org.anita.adventofcode.year2018;

import org.anita.adventofcode.util.FileUtils;
import org.anita.adventofcode.year2018.Day1;
import org.junit.Assert;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;
import java.util.List;

public class Day1Test {

    @Test
    public void task1() throws IOException {
        InputStream inputStream = getClass().getResourceAsStream("/frequencies.csv");
        List<Integer> frequencies = FileUtils.readIntsLineByLine(inputStream);

        Assert.assertEquals(520, new Day1().task1(frequencies));
    }

    @Test
    public void task2Test1() {
        List<Integer> frequencies = Arrays.asList(1, -1);

        Assert.assertEquals(0, new Day1().task2(frequencies));
    }

    @Test
    public void task2Test2() {
        List<Integer> frequencies = Arrays.asList(+3, +3, +4, -2, -4);

        Assert.assertEquals(10, new Day1().task2(frequencies));
    }

    @Test
    public void task2Test3() {
        List<Integer> frequencies = Arrays.asList(-6, +3, +8, +5, -6);

        Assert.assertEquals(5, new Day1().task2(frequencies));
    }

    @Test
    public void task2Test4() {
        List<Integer> frequencies = Arrays.asList(+7, +7, -2, -7, -4);

        Assert.assertEquals(14, new Day1().task2(frequencies));
    }

    @Test
    public void task2Final() throws IOException {
        InputStream inputStream = getClass().getResourceAsStream("/frequencies.csv");
        List<Integer> frequencies = FileUtils.readIntsLineByLine(inputStream);

        Assert.assertEquals(394, new Day1().task2(frequencies));
    }

}