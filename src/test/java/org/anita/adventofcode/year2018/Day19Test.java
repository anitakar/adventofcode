package org.anita.adventofcode.year2018;

import org.anita.adventofcode.util.FileUtils;
import org.junit.Assert;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

public class Day19Test {

    @Test
    public void task1Test1() throws IOException {
        InputStream inputStream = getClass().getResourceAsStream("/testday19.txt");
        List<String> input = FileUtils.readStringsLineByLine(inputStream);
        List<Day19.Instruction> program = Day19.readProgram(input.subList(1, input.size()));

        Assert.assertEquals(6, new Day19().execute(new int[] {0,0,0,0,0,0}, 0, program)[0]);
    }

    @Test
    public void task1() throws IOException {
        InputStream inputStream = getClass().getResourceAsStream("/day19.txt");
        List<String> input = FileUtils.readStringsLineByLine(inputStream);
        List<Day19.Instruction> program = Day19.readProgram(input.subList(1, input.size()));

        Assert.assertEquals(1440, new Day19().execute(new int[] {0,0,0,0,0,0}, 1, program)[0]);
    }

    @Test
    public void task2() throws IOException {
        InputStream inputStream = getClass().getResourceAsStream("/day19.txt");
        List<String> input = FileUtils.readStringsLineByLine(inputStream);
        List<Day19.Instruction> program = Day19.readProgram(input.subList(1, input.size()));

        Assert.assertEquals(1440, new Day19().execute(new int[] {1,0,0,0,0,0}, 1, program)[0]);
    }
}
