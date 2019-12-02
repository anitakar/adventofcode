package org.anita.adventofcode.year2019;

import org.anita.adventofcode.util.FileUtils;
import org.junit.Assert;
import org.junit.Test;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.Arrays;

public class Day2Test {

    @Test
    public void task1() throws IOException, URISyntaxException {
        int[] regs = FileUtils.readIntArrayFromSingleLine("/2019_2.txt", ",");
        regs[1] = 12;
        regs[2] = 2;

        Assert.assertEquals(4484226, new Day2().task1(regs));
    }

    @Test
    public void task2() throws IOException, URISyntaxException {
        int[] regs = FileUtils.readIntArrayFromSingleLine("/2019_2.txt", ",");
        int result = 0;

        theloop:
        for (int noun = 0; noun < 100; ++noun) {
            for (int verb = 0; verb < 100; ++verb) {
                int[] copy = Arrays.copyOf(regs, regs.length);
                copy[1] = noun;
                copy[2] = verb;
                int reg0 = new Day2().task1(copy);
                if (reg0 == 19690720) {
                    result = noun * 100 + verb;
                    break theloop;
                }
            }
        }

        Assert.assertEquals(5696, result);
    }
}
