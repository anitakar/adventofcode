package org.anita.adventofcode.year2019;

import org.anita.adventofcode.util.FileUtils;
import org.junit.Assert;
import org.junit.Test;

import java.io.IOException;
import java.util.List;

public class Day14Test {

    @Test
    public void task1Test1() throws IOException {
        Day14 day14 = new Day14();
        final List<Day14.Resource> resources = FileUtils.readElementsLineByLine(getClass().getResourceAsStream("/2019_14_test1.txt"), day14::parseResource);
        Assert.assertEquals(31, day14.task1(resources));
    }

    @Test
    public void task1Test2() throws IOException {
        Day14 day14 = new Day14();
        final List<Day14.Resource> resources = FileUtils.readElementsLineByLine(getClass().getResourceAsStream("/2019_14_test2.txt"), day14::parseResource);
        Assert.assertEquals(165, day14.task1(resources));
    }

    @Test
    public void task1Test3() throws IOException {
        Day14 day14 = new Day14();
        final List<Day14.Resource> resources = FileUtils.readElementsLineByLine(getClass().getResourceAsStream("/2019_14_test3.txt"), day14::parseResource);
        Assert.assertEquals(13312, day14.task1(resources));
    }

    @Test
    public void task1Test4() throws IOException {
        Day14 day14 = new Day14();
        final List<Day14.Resource> resources = FileUtils.readElementsLineByLine(getClass().getResourceAsStream("/2019_14_test4.txt"), day14::parseResource);
        Assert.assertEquals(180697, day14.task1(resources));
    }

    @Test
    public void task1Test5() throws IOException {
        Day14 day14 = new Day14();
        final List<Day14.Resource> resources = FileUtils.readElementsLineByLine(getClass().getResourceAsStream("/2019_14_test5.txt"), day14::parseResource);
        Assert.assertEquals(2210736, day14.task1(resources));
    }

    @Test
    public void task1() throws IOException {
        Day14 day14 = new Day14();
        final List<Day14.Resource> resources = FileUtils.readElementsLineByLine(getClass().getResourceAsStream("/2019_14.txt"), day14::parseResource);
        Assert.assertEquals(0, day14.task1(resources));
    }
}
