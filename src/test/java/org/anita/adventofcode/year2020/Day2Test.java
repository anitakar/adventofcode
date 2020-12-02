package org.anita.adventofcode.year2020;

import org.anita.adventofcode.util.FileUtils;
import org.anita.adventofcode.year2018.Day10;
import org.junit.Assert;
import org.junit.Test;

import java.io.InputStream;
import java.util.List;

public class Day2Test {

    @Test
    public void task1() throws Exception {
        Day2 day2 = new Day2();
        InputStream inputStream = Day10.class.getResourceAsStream("/2020_2.txt");
        final List<Boolean> isValidList = FileUtils.readElementsLineByLine(inputStream, day2::task1);
        Assert.assertEquals(398, isValidList.stream().filter(b -> b).count());
    }

    @Test
    public void task2() throws Exception {
        Day2 day2 = new Day2();
        InputStream inputStream = Day10.class.getResourceAsStream("/2020_2.txt");
        final List<Boolean> isValidList = FileUtils.readElementsLineByLine(inputStream, day2::task2);
        Assert.assertEquals(562, isValidList.stream().filter(b -> b).count());
    }
}
