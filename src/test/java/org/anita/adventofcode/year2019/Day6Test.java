package org.anita.adventofcode.year2019;

import org.anita.adventofcode.util.FileUtils;
import org.junit.Assert;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

public class Day6Test {

    @Test
    public void task1Test1() throws IOException {
        InputStream resource = getClass().getResourceAsStream("/2019_6_test.txt");
        List<String> relations = FileUtils.readStringsLineByLine(resource);

        Assert.assertEquals(54, new Day6().task1(relations));
    }

    @Test
    public void task1() throws IOException {
        InputStream resource = getClass().getResourceAsStream("/2019_6.txt");
        List<String> relations = FileUtils.readStringsLineByLine(resource);

        Assert.assertEquals(301100, new Day6().task1(relations));
    }

    @Test
    public void task2Test1() throws IOException {
        InputStream resource = getClass().getResourceAsStream("/2019_6_test.txt");
        List<String> relations = FileUtils.readStringsLineByLine(resource);

        Assert.assertEquals(4, new Day6().task2(relations));
    }

    @Test
    public void task2() throws IOException {
        InputStream resource = getClass().getResourceAsStream("/2019_6.txt");
        List<String> relations = FileUtils.readStringsLineByLine(resource);

        Assert.assertEquals(547, new Day6().task2(relations));
    }
}
