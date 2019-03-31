package org.anita.adventofcode.year2015;

import org.junit.Assert;
import org.junit.Test;

public class Day10Test {

    @Test
    public void test111221() {
        Assert.assertEquals("312211", Day10.lookAndSay("111221"));
    }

    @Test
    public void task1() {
        String input = "1113222113";
        for (int i = 0; i < 40; ++i) {
            input = Day10.lookAndSay(input);
        }
        Assert.assertEquals(252594, input.length());
    }

    @Test
    public void task2() {
        String input = "1113222113";
        for (int i = 0; i < 50; ++i) {
            input = Day10.lookAndSay(input);
        }
        Assert.assertEquals(3579328, input.length());
    }
}
