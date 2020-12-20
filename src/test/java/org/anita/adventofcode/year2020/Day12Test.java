package org.anita.adventofcode.year2020;

import org.anita.adventofcode.util.FileUtils;
import org.junit.Assert;
import org.junit.Test;

public class Day12Test {

    @Test
    public void test1() throws Exception {
        Day12.Ship ship = new Day12.Ship();

        FileUtils.readElementsLineByLine(getClass().getResourceAsStream("/2020_12.txt"), ship::move);
        Assert.assertEquals(1838, ship.getManhattanDistance());
    }

    @Test
    public void test2Test() throws Exception {
        Day12.Ship ship = new Day12.Ship();

        FileUtils.readElementsLineByLine(getClass().getResourceAsStream("/2020_12_test.txt"), ship::move2);
        Assert.assertEquals(286, ship.getManhattanDistance());
    }

    @Test
    public void test2() throws Exception {
        Day12.Ship ship = new Day12.Ship();

        FileUtils.readElementsLineByLine(getClass().getResourceAsStream("/2020_12.txt"), ship::move2);
        Assert.assertEquals(89936, ship.getManhattanDistance());
    }
}
