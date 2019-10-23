package org.anita.adventofcode.year2016;

import org.anita.adventofcode.util.FileUtils;
import org.junit.Assert;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

public class Day2Test {

    @Test
    public void task1Example1() throws IOException {
        InputStream inputStream = getClass().getResourceAsStream("/2016_2_test.txt");
        List<String> steps = FileUtils.readElementsLineByLine(inputStream, s -> s);
        Assert.assertEquals("1985", new Day2().lockpadNumber(steps));
    }

    @Test
    public void task1() throws IOException {
        InputStream inputStream = getClass().getResourceAsStream("/2016_2.txt");
        List<String> steps = FileUtils.readElementsLineByLine(inputStream, s -> s);
        Assert.assertEquals("1985", new Day2().lockpadNumber(steps));
    }
}
