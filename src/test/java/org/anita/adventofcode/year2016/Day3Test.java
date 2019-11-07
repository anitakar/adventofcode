package org.anita.adventofcode.year2016;

import org.anita.adventofcode.util.FileUtils;
import org.junit.Assert;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

public class Day3Test {

    @Test
    public void shouldParseTriangle() {
        String line = "  440  460  145";

        Day3.Triangle triangle = new Day3().parseTriangle(line);

        Assert.assertEquals(triangle.x, 440);
        Assert.assertEquals(triangle.y, 460);
        Assert.assertEquals(triangle.z, 145);
    }

    @Test
    public void task1() throws IOException {
        Day3 day3 = new Day3();
        InputStream inputStream = getClass().getResourceAsStream("/2016_3.txt");
        List<Day3.Triangle> triangles = FileUtils.readElementsLineByLine(inputStream, day3::parseTriangle);

        Assert.assertEquals(869, new Day3().task1(triangles));
    }

    @Test
    public void task2() throws IOException {
        Day3 day3 = new Day3();
        InputStream inputStream = getClass().getResourceAsStream("/2016_3.txt");
        List<Day3.Triangle> input = FileUtils.readElementsLineByLine(inputStream, day3::parseTriangle);
        List<Day3.Triangle> triangles = day3.transposeList(input);

        Assert.assertEquals(1544, new Day3().task1(triangles));
    }
}
