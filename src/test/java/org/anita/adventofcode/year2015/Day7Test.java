package org.anita.adventofcode.year2015;

import org.anita.adventofcode.util.FileUtils;
import org.junit.Assert;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

public class Day7Test {

    @Test
    public void test1() throws IOException {
        Day7 day7 = new Day7();
        InputStream inputStream = getClass().getResourceAsStream("/2015_7.txt");
        List<Day7.Operation> operations = FileUtils.readElementsLineByLine(inputStream, day7::parseOperation);

        day7.evaluate(operations);

        Assert.assertEquals(day7.getValue("a"), 3176);
    }

    @Test
    public void test2() throws IOException {
        Day7 day7 = new Day7();
        InputStream inputStream = getClass().getResourceAsStream("/2015_7.txt");
        List<Day7.Operation> operations = FileUtils.readElementsLineByLine(inputStream, day7::parseOperation);

        day7.evaluate(operations);

        int aGateValue = day7.getValue("a");

        day7 = new Day7();
        inputStream = getClass().getResourceAsStream("/2015_7.txt");
        operations = FileUtils.readElementsLineByLine(inputStream, day7::parseOperation);
        day7.setGate("b", aGateValue);

        day7.evaluate(operations);

        Assert.assertEquals(day7.getValue("a"), 14710);
    }
}
