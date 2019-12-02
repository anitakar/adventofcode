package org.anita.adventofcode.year2015;

import org.anita.adventofcode.util.FileUtils;
import org.junit.Assert;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

public class Day8Test {

    @Test
    public void task1Test() throws IOException {
        InputStream stream = getClass().getResourceAsStream("/2015_8_test.txt");
        List<String> strings = FileUtils.readStringsLineByLine(stream);

        Assert.assertEquals(12, new Day8().task1(strings));
    }

    @Test
    public void task1() throws IOException {
        InputStream stream = getClass().getResourceAsStream("/2015_8.txt");
        List<String> strings = FileUtils.readStringsLineByLine(stream);

        Assert.assertEquals(1342, new Day8().task1(strings));
    }

    @Test
    public void task2Test() throws IOException {
        InputStream stream = getClass().getResourceAsStream("/2015_8_test.txt");
        List<String> strings = FileUtils.readStringsLineByLine(stream);

        Assert.assertEquals(19, new Day8().task2(strings));
    }

    @Test
    public void task2() throws IOException {
        InputStream stream = getClass().getResourceAsStream("/2015_8.txt");
        List<String> strings = FileUtils.readStringsLineByLine(stream);

        Assert.assertEquals(0, new Day8().task2(strings));
    }
}
