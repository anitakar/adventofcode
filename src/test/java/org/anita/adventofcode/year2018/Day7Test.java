package org.anita.adventofcode.year2018;

import org.anita.adventofcode.util.FileUtils;
import org.junit.Assert;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;

public class Day7Test {

    @Test
    public void task1Test1() throws IOException {
        Day7 day7 = new Day7();
        InputStream inputStream = getClass().getResourceAsStream("/testassemblyinstructions.txt");
        FileUtils.consumeElementsLineByLine(inputStream, day7::parseDependency);

        Assert.assertEquals("CABDFE", day7.task1());
    }

    @Test
    public void task1() throws IOException {
        Day7 day7 = new Day7();
        InputStream inputStream = getClass().getResourceAsStream("/assemblyinstructions.txt");
        FileUtils.consumeElementsLineByLine(inputStream, day7::parseDependency);

        Assert.assertEquals("LAPFCRGHVZOTKWENBXIMSUDJQY", day7.task1());
    }

    @Test
    public void task2Test1() throws IOException {
        Day7 day7 = new Day7();
        InputStream inputStream = getClass().getResourceAsStream("/testassemblyinstructions.txt");
        FileUtils.consumeElementsLineByLine(inputStream, day7::parseDependency);

        Assert.assertEquals(15, day7.task2(0, 2));
    }

    @Test
    public void task2() throws IOException {
        Day7 day7 = new Day7();
        InputStream inputStream = getClass().getResourceAsStream("/assemblyinstructions.txt");
        FileUtils.consumeElementsLineByLine(inputStream, day7::parseDependency);

        Assert.assertEquals(936, day7.task2(60, 5));
    }
}
