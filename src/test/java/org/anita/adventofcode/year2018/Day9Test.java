package org.anita.adventofcode.year2018;

import org.junit.Assert;
import org.junit.Test;

public class Day9Test {

    @Test
    public void task1Test1() {
        Assert.assertEquals(32, new Day9().task1(9, 25));
    }

    @Test
    public void task1Test2() {
        Assert.assertEquals(8317, new Day9().task1(10, 1618));
    }

    @Test
    public void task1Test3() {
        Assert.assertEquals(146373, new Day9().task1(13, 7999));
    }

    @Test
    public void task1Test4() {
        Assert.assertEquals(2764, new Day9().task1(17, 1104));
    }

    @Test
    public void task1Test5() {
        Assert.assertEquals(54718, new Day9().task1(21, 6111));
    }

    @Test
    public void task1Test6() {
        Assert.assertEquals(37305, new Day9().task1(30, 5807));
    }

    @Test
    public void task1() {
        Assert.assertEquals(429943, new Day9().task1(411, 72059));
    }

    @Test
    public void task2() {
        Assert.assertEquals(3615691746L, new Day9().task1(411, 100*72059));
    }
}
