package org.anita.adventofcode.year2015;

import org.anita.adventofcode.util.FileUtils;
import org.junit.Assert;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;

public class Day1Test {

    private Day1 day1 = new Day1();

    @Test
    public void testTask1() throws IOException {
        InputStream inputStream = getClass().getResourceAsStream("/2015_1.txt");
        String floors = FileUtils.readStringsLineByLine(inputStream).get(0);
        Assert.assertEquals(232, day1.floors(floors));
    }

    @Test
    public void testTask2() throws IOException {
        InputStream inputStream = getClass().getResourceAsStream("/2015_1.txt");
        String floors = FileUtils.readStringsLineByLine(inputStream).get(0);
        Assert.assertEquals(1783, day1.basementPosition(floors));
    }

}
