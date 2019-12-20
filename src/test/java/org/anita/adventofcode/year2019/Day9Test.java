package org.anita.adventofcode.year2019;

import org.anita.adventofcode.util.FileUtils;
import org.junit.Assert;
import org.junit.Test;

import java.io.IOException;
import java.net.URISyntaxException;

public class Day9Test {

    @Test
    public void task1Test1() {
        Assert.assertArrayEquals(
                new long[]{109,1,204,-1,1001,100,1,100,1008,100,16,101,1006,101,0,99},
                new IntCodeComputer().interpret(new long[]{109,1,204,-1,1001,100,1,100,1008,100,16,101,1006,101,0,99}, new long[]{})
        );
    }

    @Test
    public void task1Test2() {
        Assert.assertArrayEquals(
                new long[]{1219070632396864L},
                new IntCodeComputer().interpret(new long[]{1102,34915192,34915192,7,4,7,99,0}, new long[]{})
        );
    }

    @Test
    public void task1Test3() {
        Assert.assertArrayEquals(
                new long[]{1125899906842624L},
                new IntCodeComputer().interpret(new long[]{104,1125899906842624L,99}, new long[]{})
        );
    }

    @Test
    public void task1Test4() {
        Assert.assertArrayEquals(
                new long[]{109},
                new IntCodeComputer().interpret(new long[]{109, 2000, 109, 19, 204,-2019, 99}, new long[]{})
        );
    }

    @Test
    public void task1() throws IOException, URISyntaxException {
        long[] memory = FileUtils.readLongArrayFromSingleLine("/2019_9.txt", ",");
        Assert.assertArrayEquals(
                new long[]{3380552333L},
                new IntCodeComputer().interpret(memory, new long[]{1})
        );
    }

    @Test
    public void task2() throws IOException, URISyntaxException {
        long[] memory = FileUtils.readLongArrayFromSingleLine("/2019_9.txt", ",");
        Assert.assertArrayEquals(
                new long[]{78831},
                new IntCodeComputer().interpret(memory, new long[]{2})
        );
    }
}
