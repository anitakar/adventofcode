package org.anita.adventofcode.year2018;

import org.junit.Assert;
import org.junit.Test;

public class Day14Test {

    @Test
    public void task1Test1() {
        Assert.assertEquals("5158916779", new Day14().task1(9));
    }

    @Test
    public void task1Test2() {
        Assert.assertEquals("0124515891", new Day14().task1(5));
    }

    @Test
    public void task1Test3() {
        Assert.assertEquals("9251071085", new Day14().task1(18));
    }

    @Test
    public void task1Test4() {
        Assert.assertEquals("5941429882", new Day14().task1(2018));
    }

    @Test
    public void task1() {
        Assert.assertEquals("2107929416", new Day14().task1(556061));
    }

    @Test
    public void task2Test1() {
        Assert.assertEquals(9, new Day14().task2("51589"));
    }

    @Test
    public void task2Test2() {
        Assert.assertEquals(5, new Day14().task2("01245"));
    }

    @Test
    public void task2Test3() {
        Assert.assertEquals(18, new Day14().task2("92510"));
    }

    @Test
    public void task2Test4() {
        Assert.assertEquals(2018, new Day14().task2("59414"));
    }

    @Test
    public void task2() {
        Assert.assertEquals(20307394, new Day14().task2("556061"));
    }
}
