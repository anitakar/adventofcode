package org.anita.adventofcode.year2020;

import org.anita.adventofcode.util.FileUtils;
import org.anita.adventofcode.util.Pair;
import org.junit.Assert;
import org.junit.Test;

import java.io.InputStream;
import java.util.List;

public class Day5Test {

    @Test
    public void task1() throws Exception {
        InputStream inputStream = getClass().getResourceAsStream("/2020_5.txt");
        List<Pair<Integer, Integer>> seats = FileUtils.readElementsLineByLine(inputStream, Day5::readSeat);
        Assert.assertEquals(926, new Day5().task1(seats));
    }

    @Test
    public void task2() throws Exception {
        InputStream inputStream = getClass().getResourceAsStream("/2020_5.txt");
        List<Pair<Integer, Integer>> seats = FileUtils.readElementsLineByLine(inputStream, Day5::readSeat);
        Assert.assertEquals(657, new Day5().task2(seats));
    }
}
