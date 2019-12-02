package org.anita.adventofcode.year2019;

import org.anita.adventofcode.util.FileUtils;
import org.junit.Assert;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

public class Day1Test {

    @Test
    public void task1Test() throws IOException {
        InputStream inputStream = getClass().getResourceAsStream("/2019_1_test.txt");
        List<Integer> masses = FileUtils.readIntsLineByLine(inputStream);
        Assert.assertEquals(34241, new Day1().task1(masses));
    }

    @Test
    public void task1() throws IOException {
        InputStream inputStream = getClass().getResourceAsStream("/2019_1.txt");
        List<Integer> masses = FileUtils.readIntsLineByLine(inputStream);
        Assert.assertEquals(3394032, new Day1().task1(masses));
    }

    @Test
    public void task2() throws IOException {
        InputStream inputStream = getClass().getResourceAsStream("/2019_1.txt");
        List<Integer> masses = FileUtils.readIntsLineByLine(inputStream);
        Assert.assertEquals(5088176, new Day1().task2(masses));
    }
}
