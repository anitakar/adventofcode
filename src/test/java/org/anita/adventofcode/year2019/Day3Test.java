package org.anita.adventofcode.year2019;

import org.anita.adventofcode.util.FileUtils;
import org.junit.Assert;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

public class Day3Test {

    @Test
    public void task1() throws IOException {
        InputStream stream = getClass().getResourceAsStream("/2019_3.txt");
        List<String> paths = FileUtils.readElementsLineByLine(stream, s -> s);

        Assert.assertEquals(221, new Day3().task1(paths.get(0).split(","), paths.get(1).split(",")));
    }

    @Test
    public void task2() throws IOException {
        InputStream stream = getClass().getResourceAsStream("/2019_3.txt");
        List<String> paths = FileUtils.readElementsLineByLine(stream, s -> s);

        Assert.assertEquals(18542, new Day3().task2(paths.get(0).split(","), paths.get(1).split(",")));
    }
}
