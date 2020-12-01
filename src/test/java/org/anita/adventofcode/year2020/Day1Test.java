package org.anita.adventofcode.year2020;

import org.anita.adventofcode.util.FileUtils;
import org.junit.Assert;
import org.junit.Test;

import java.io.InputStream;
import java.util.List;

public class Day1Test {
    @Test
    public void task1() throws Exception {
        InputStream inputStream = getClass().getResourceAsStream("/2020_1.txt");
        List<Integer> numbers = FileUtils.readIntsLineByLine(inputStream);
        Assert.assertEquals(838624, new Day1().task1(numbers));
    }

    @Test
    public void task2() throws Exception {
        InputStream inputStream = getClass().getResourceAsStream("/2020_1.txt");
        List<Integer> numbers = FileUtils.readIntsLineByLine(inputStream);
        Assert.assertEquals(52764180, new Day1().task2(numbers));
    }
}
