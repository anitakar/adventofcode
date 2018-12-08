package org.anita.adventofcode.year2018;

import org.anita.adventofcode.util.FileUtils;
import org.junit.Assert;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

public class Day5Test {

    @Test
    public void task1Test1() {
        Assert.assertEquals(10, new Day5().task1("dabAcCaCBAcCcaDA"));
    }

    @Test
    public void task1() throws IOException {
        InputStream inputStream = getClass().getResourceAsStream("/polymer.txt" );
        List<String> lines = FileUtils.readStringsLineByLine(inputStream);

        Assert.assertEquals(11264, new Day5().task1(lines.get(0)));
    }

    @Test
    public void task2Test1() {
        Assert.assertEquals(4, new Day5().task2("dabAcCaCBAcCcaDA"));
    }

    @Test
    public void task2() throws IOException {
        InputStream inputStream = getClass().getResourceAsStream("/polymer.txt" );
        List<String> lines = FileUtils.readStringsLineByLine(inputStream);

        Assert.assertEquals(4552, new Day5().task2(lines.get(0)));
    }
}
