package org.anita.adventofcode.year2019;

import org.anita.adventofcode.util.FileUtils;
import org.junit.Assert;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;

public class Day8Test {

    @Test
    public void task1() throws IOException {
        InputStream stream = getClass().getResourceAsStream("/2019_8.txt");
        String image = FileUtils.readStringsLineByLine(stream).get(0);

        Assert.assertEquals(1560, new Day8().task1(image, 25, 6));
    }

    @Test
    public void task2Test() throws IOException {
        new Day8().task2("0222112222120000", 2, 2);
    }

    @Test
    public void task2() throws IOException {
        InputStream stream = getClass().getResourceAsStream("/2019_8.txt");
        String image = FileUtils.readStringsLineByLine(stream).get(0);

        new Day8().task2(image, 25, 6);
    }
}
