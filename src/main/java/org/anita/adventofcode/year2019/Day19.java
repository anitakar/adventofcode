package org.anita.adventofcode.year2019;

import java.util.Arrays;

public class Day19 {

    public int task1(long[] memory) {
        int sum = 0;
        for (int r = 0; r < 50; ++r) {
            for (int c = 0; c < 50; ++c) {
                IntCodeComputer computer = new IntCodeComputer();
                sum += computer.interpret(Arrays.copyOf(memory, memory.length), new long[] {r, c})[0];
            }
        }
        return sum;
    }
}
