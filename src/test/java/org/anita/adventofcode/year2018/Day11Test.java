package org.anita.adventofcode.year2018;

import org.junit.Assert;
import org.junit.Test;

public class Day11Test {

    @Test
    public void cellPowerTest1() {
        Assert.assertEquals(4, new Day11(8).powerLevel(3, 5));
    }

    @Test
    public void cellPowerTest2() {
        Assert.assertEquals(-5, new Day11(57).powerLevel(122, 79));
    }

    @Test
    public void cellPowerTest3() {
        Assert.assertEquals(0, new Day11(39).powerLevel(217, 196));
    }

    @Test
    public void cellPowerTest4() {
        Assert.assertEquals(4, new Day11(71).powerLevel(101, 153));
    }

    @Test
    public void task1Test1() {
        Assert.assertEquals(new Day11.Point(33, 45), new Day11(18).task1());
    }

    @Test
    public void task1Test2() {
        Assert.assertEquals(new Day11.Point(21, 61), new Day11(42).task1());
    }

    @Test
    public void task1() {
        Assert.assertEquals(new Day11.Point(243, 49), new Day11(5093).task1());
    }

    @Test
    public void task2Test1() {
        Assert.assertEquals(new Day11.Square(90, 269, 16), new Day11(18).task2());
    }

    @Test
    public void task2Test2() {
        Assert.assertEquals(new Day11.Square(232, 251, 12), new Day11(42).task2());
    }

    @Test
    public void task2() {
        Assert.assertEquals(new Day11.Square(285, 169, 15), new Day11(5093).task2());
    }
}
