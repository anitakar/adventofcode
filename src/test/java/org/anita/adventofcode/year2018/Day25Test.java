package org.anita.adventofcode.year2018;

import org.anita.adventofcode.structures.Point4D;
import org.anita.adventofcode.util.FileUtils;
import org.junit.Assert;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

public class Day25Test {

    @Test
    public void task1Test1() throws IOException {
        InputStream inputStream = getClass().getResourceAsStream("/test1day25.txt");
        List<Point4D> points = FileUtils.readElementsLineByLine(inputStream, Point4D::parseLine);

        Assert.assertEquals(2, new Day25().task1(points));
    }

    @Test
    public void task1Test2() throws IOException {
        InputStream inputStream = getClass().getResourceAsStream("/test2day25.txt");
        List<Point4D> points = FileUtils.readElementsLineByLine(inputStream, Point4D::parseLine);

        Assert.assertEquals(4, new Day25().task1(points));
    }

    @Test
    public void task1Test3() throws IOException {
        InputStream inputStream = getClass().getResourceAsStream("/test3day25.txt");
        List<Point4D> points = FileUtils.readElementsLineByLine(inputStream, Point4D::parseLine);

        Assert.assertEquals(3, new Day25().task1(points));
    }

    @Test
    public void task1Test4() throws IOException {
        InputStream inputStream = getClass().getResourceAsStream("/test4day25.txt");
        List<Point4D> points = FileUtils.readElementsLineByLine(inputStream, Point4D::parseLine);

        Assert.assertEquals(8, new Day25().task1(points));
    }

    @Test
    public void task1() throws IOException {
        InputStream inputStream = getClass().getResourceAsStream("/day25.txt");
        List<Point4D> points = FileUtils.readElementsLineByLine(inputStream, Point4D::parseLine);

        Assert.assertEquals(375, new Day25().task1(points));
    }
}
