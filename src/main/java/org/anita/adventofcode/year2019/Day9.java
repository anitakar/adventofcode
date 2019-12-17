package org.anita.adventofcode.year2019;

public class Day9 {

    public long[] task1(long[] memory, long input) {
        IntCodeComputer computer = new IntCodeComputer();
        return computer.interpret(memory, input);
    }

}
