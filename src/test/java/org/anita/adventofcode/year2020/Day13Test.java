package org.anita.adventofcode.year2020;

import org.junit.Assert;
import org.junit.Test;

public class Day13Test {

    @Test
    public void task1Test() {
        Assert.assertEquals(295, new Day13().task1(939, new int[] {7, 13, 59, 31, 19}));
    }

    @Test
    public void task1() {
        Assert.assertEquals(2092, new Day13().task1(1000495, new int[] {19,41,521,23,17,29,523,37,13}));
    }
}
