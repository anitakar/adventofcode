package org.anita.adventofcode.year2015;

import org.anita.adventofcode.util.FileUtils;
import org.junit.Assert;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

public class Day6Test {

    @Test
    public void testNumberLitStrategy1() throws IOException {
        Day6 day6 = new Day6();
        InputStream inputStream = getClass().getResourceAsStream("/2015_6.txt");
        List<Day6.Instruction> instructions = FileUtils.readElementsLineByLine(inputStream, day6::parseInstruction);

        day6.performInstructions(instructions);
        Assert.assertEquals(day6.numberOfLightsLit(), 400410);
        day6.printMap();
    }

    @Test
    public void testNumberLitStrategy2() throws IOException {
        Day6 day6 = new Day6();
        InputStream inputStream = getClass().getResourceAsStream("/2015_6.txt");
        List<Day6.Instruction> instructions = FileUtils.readElementsLineByLine(inputStream, day6::parseInstruction);

        day6.performInstructions2(instructions);
        Assert.assertEquals(day6.numberOfLightsLit(), 15343601);
        day6.printMap();
    }
}
