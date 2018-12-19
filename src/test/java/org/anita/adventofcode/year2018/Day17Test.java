package org.anita.adventofcode.year2018;

import org.anita.adventofcode.util.FileUtils;
import org.junit.Assert;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

public class Day17Test {

    @Test
    public void task1Test1() throws IOException {
        InputStream inputStream = getClass().getResourceAsStream("/testwater.txt");
        List<String> map = FileUtils.readStringsLineByLine(inputStream);

        Assert.assertEquals(57, new Day17(map).task1());
    }

    @Test
    public void task1() throws IOException {
        InputStream inputStream = getClass().getResourceAsStream("/water.txt");
        List<String> map = FileUtils.readStringsLineByLine(inputStream);

        Assert.assertEquals(39592, new Day17(map).task1());
    }
}
