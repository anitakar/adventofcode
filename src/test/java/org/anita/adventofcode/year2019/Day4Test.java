package org.anita.adventofcode.year2019;

import org.junit.Assert;
import org.junit.Test;

public class Day4Test {

    @Test
    public void task1() {
        Assert.assertEquals(1675, new Day4().task1());
    }

    @Test
    public void task2Test1() {
        Assert.assertEquals(1, new Day4().task2(112233, 112233));
        Assert.assertEquals(0, new Day4().task2(123444, 123444));
        Assert.assertEquals(1, new Day4().task2(111122, 111122));
    }

    @Test
    public void task2() {
        Assert.assertEquals(1142, new Day4().task2(172930, 683082));
    }
}
