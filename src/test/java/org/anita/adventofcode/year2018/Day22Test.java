package org.anita.adventofcode.year2018;

import org.junit.Assert;
import org.junit.Test;

public class Day22Test {

    @Test
    public void task1Test1() {
        Assert.assertEquals(114, new Day22().task1(10, 10, 510));
    }

    @Test
    public void task1() {
        Assert.assertEquals(7402, new Day22().task1(9, 751, 11817));
    }
}
