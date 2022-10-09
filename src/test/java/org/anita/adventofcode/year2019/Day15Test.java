package org.anita.adventofcode.year2019;

import org.anita.adventofcode.util.FileUtils;
import org.junit.Test;

import java.io.IOException;
import java.net.URISyntaxException;

public class Day15Test {

    @Test
    public void task1() throws IOException, URISyntaxException {
        Day15.Robot robot = new Day15.Robot();

        long[] memory = FileUtils.readLongArrayFromSingleLine("/2019_15.txt", ",");
        IntCodeComputer computer = new IntCodeComputer();
        computer.addOutputListener(robot);

        computer.interpret(memory, robot);
    }
}
