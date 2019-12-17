package org.anita.adventofcode.year2019;

import org.junit.Assert;
import org.junit.Test;

import java.util.Arrays;

public class Day12Test {

    @Test
    public void task1Test1() {
        Assert.assertEquals(179, new Day12().task1(Arrays.asList(
                new Day12.Moon(1,-1,0, 2),
                new Day12.Moon(2, 2, -10, -7),
                new Day12.Moon(3, 4, -8, 8),
                new Day12.Moon(4, 3, 5, -1)
        ), 10));
    }

    @Test
    public void task1() {
        Assert.assertEquals(8310, new Day12().task1(Arrays.asList(
                new Day12.Moon(1,1,3, -11),
                new Day12.Moon(2, 17, -10, -8),
                new Day12.Moon(3, -1, -15, 2),
                new Day12.Moon(4, 12, -4, -4)
        ), 1000));
    }
}
