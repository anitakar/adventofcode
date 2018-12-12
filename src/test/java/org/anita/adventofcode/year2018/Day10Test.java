package org.anita.adventofcode.year2018;

import org.anita.adventofcode.util.FileUtils;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

public class Day10Test {

    @Test
    public void task1Test1() throws IOException {
        Day10 day10 = new Day10();
        InputStream inputStream = getClass().getResourceAsStream("/testday10.txt");
        List<Day10.PositionAndVelocity> pvs = FileUtils.readElementsLineByLine(inputStream, day10::parsePointAndVelocity);

        // programatically, iterate while height gets lower
        // the solution is one before height starts getting higher again
        // return the solution as string
        // compare to make assertion
        day10.task1(pvs, 4);
    }

    @Test
    public void task1() throws IOException {
        Day10 day10 = new Day10();
        InputStream inputStream = getClass().getResourceAsStream("/day10.txt");
        List<Day10.PositionAndVelocity> pvs = FileUtils.readElementsLineByLine(inputStream, day10::parsePointAndVelocity);

        day10.task1(pvs, 100000);
    }
}
