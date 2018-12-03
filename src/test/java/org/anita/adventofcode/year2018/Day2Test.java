package org.anita.adventofcode.year2018;

import org.anita.adventofcode.util.FileUtils;
import org.junit.Assert;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;
import java.util.List;

public class Day2Test {

    @Test
    public void task1Test1() {
        List<String> boxIds = Arrays.asList("abcdef", "bababc", "abbcde", "abcccd", "aabcdd", "abcdee", "ababab");

        Assert.assertEquals(12, new Day2().task1(boxIds));
    }

    @Test
    public void task1() throws IOException {
        InputStream inputStream = getClass().getResourceAsStream("/boxids.txt");
        List<String> boxIds = FileUtils.readStringssLineByLine(inputStream);

        Assert.assertEquals(7221, new Day2().task1(boxIds));
    }

    @Test
    public void task2Test1() {
        List<String> boxIds = Arrays.asList("abcde", "fghij", "klmno", "pqrst", "fguij", "axcye", "wvxyz");

        Assert.assertEquals("fgij", new Day2().task2(boxIds));
    }

    @Test
    public void task2() throws IOException {
        InputStream inputStream = getClass().getResourceAsStream("/boxids.txt");
        List<String> boxIds = FileUtils.readStringssLineByLine(inputStream);

        Assert.assertEquals("mkcdflathzwsvjxrevymbdpoq", new Day2().task2(boxIds));
    }
}
