package org.anita.adventofcode.year2018;

import org.anita.adventofcode.util.FileUtils;
import org.junit.Assert;
import org.junit.Ignore;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;
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

        int[] registers = new Day19().execute(new int[]{0, 0, 0, 0, 0, 0}, 1, program);
        System.out.println(Arrays.toString(registers));
        Assert.assertEquals(1440, registers[0]);
    }

    @Test
    @Ignore
    public void task2BruteForce() throws IOException {
        InputStream inputStream = getClass().getResourceAsStream("/day19.txt");
        List<String> input = FileUtils.readStringsLineByLine(inputStream);
        List<Day19.Instruction> program = Day19.readProgram(input.subList(1, input.size()));

        Assert.assertEquals(1440, new Day19().execute(new int[] {1,0,0,0,0,0}, 1, program)[0]);
    }

    @Test
    public void programPrint() throws IOException {
        InputStream inputStream = getClass().getResourceAsStream("/day19.txt");
        List<String> input = FileUtils.readStringsLineByLine(inputStream);
        List<Day19.Instruction> program = Day19.readProgram(input.subList(1, input.size()));

        System.out.println(new Day19().print(1, program));
    }

    @Test
    public void task1Print() throws IOException {
        InputStream inputStream = getClass().getResourceAsStream("/day19.txt");
        List<String> input = FileUtils.readStringsLineByLine(inputStream);
        List<Day19.Instruction> program = Day19.readProgram(input.subList(1, input.size()));

        new Day19().executeAndPrint(new int[] {0,0,0,0,0,0}, 1, program, 200);
    }

    @Test
    public void task2Print() throws IOException {
        InputStream inputStream = getClass().getResourceAsStream("/day19.txt");
        List<String> input = FileUtils.readStringsLineByLine(inputStream);
        List<Day19.Instruction> program = Day19.readProgram(input.subList(1, input.size()));

        new Day19().executeAndPrint(new int[] {1,0,0,0,0,0}, 1, program, 200);
    }

    @Test
    public void task1PredictionTest() throws IOException {
        InputStream inputStream = getClass().getResourceAsStream("/day19.txt");
        List<String> input = FileUtils.readStringsLineByLine(inputStream);
        List<Day19.Instruction> program = Day19.readProgram(input.subList(1, input.size()));

        int[] registers = new Day19().execute(new int[]{1, 3, 0, 479, 958, 2}, 1, program);
        System.out.println(Arrays.toString(registers));
        Assert.assertEquals(1440, registers[0]);
    }

    @Test
    public void task1PredictionPrint() throws IOException {
        InputStream inputStream = getClass().getResourceAsStream("/day19.txt");
        List<String> input = FileUtils.readStringsLineByLine(inputStream);
        List<Day19.Instruction> program = Day19.readProgram(input.subList(1, input.size()));

        int[] registers = new Day19().executeAndPrint(new int[]{1, 3, 0, 479, 958, 2}, 1, program, 100);
        System.out.println(Arrays.toString(registers));
        //Assert.assertEquals(1440, registers[0]);
    }

    @Test
    public void task2Test1() {
        Assert.assertEquals(1440, new Day19().task2(958));
    }

    @Test
    public void task2() {
        Assert.assertEquals(15827040, new Day19().task2(10551358));
    }
}
