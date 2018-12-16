package org.anita.adventofcode.year2018;

import org.anita.adventofcode.util.FileUtils;
import org.junit.Assert;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Map;

public class Day16Test {

    @Test
    public void testSingleNumOpsMatch() {
        Assert.assertEquals(3, new Day16().matchingOps(new int[]{3, 2, 1, 1}, new int[]{9, 2, 1, 2}, new int[]{3, 2, 2, 1}).size());
    }

    @Test
    public void task1() throws IOException {
        InputStream inputStream = getClass().getResourceAsStream("/day16.txt");
        List<String> input = FileUtils.readStringsLineByLine(inputStream);
        List<Day16.Operation> operations = Day16.readOperations(input);

        Assert.assertEquals(646, new Day16().task1(operations));
    }

    @Test
    public void task2() throws IOException {
        InputStream inputStream = getClass().getResourceAsStream("/day16.txt");
        List<String> input = FileUtils.readStringsLineByLine(inputStream);
        List<Day16.Operation> operations = Day16.readOperations(input);

        Map<Integer, Integer> opCodeToOperation = new Day16().opsDecoding(operations);

        inputStream = getClass().getResourceAsStream("/day16part2.txt");
        input = FileUtils.readStringsLineByLine(inputStream);
        List<int[]> program = Day16.readProgram(input);

        Assert.assertEquals(681, new Day16().execute(new int[] {0,0,0,0}, program, opCodeToOperation)[0]);
    }
}
