package org.anita.adventofcode.year2018;

import org.anita.adventofcode.util.FileUtils;
import org.junit.Assert;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

public class Day21Test {

    @Test
    public void task1Test1() throws IOException {
        InputStream inputStream = getClass().getResourceAsStream("/testday19.txt");
        List<String> input = FileUtils.readStringsLineByLine(inputStream);
        List<Day21.Instruction> program = Day21.readProgram(input.subList(1, input.size()));

        Assert.assertEquals(5, new Day21().execute(new int[] {0,0,0,0,0,0}, 0, program));
    }

    @Test
    public void task1() throws IOException {
        InputStream inputStream = getClass().getResourceAsStream("/day21.txt");
        List<String> input = FileUtils.readStringsLineByLine(inputStream);
        List<Day21.Instruction> program = Day21.readProgram(input.subList(1, input.size()));

        for (int i = 0; i < 1000; ++i) {
            int numberOfInstructions = new Day21().execute(new int[]{i, 0, 0, 0, 0, 0}, 2, program);
            System.out.println("i=" + i + ", numberOfInstructions=" + numberOfInstructions);
        }
    }

}
