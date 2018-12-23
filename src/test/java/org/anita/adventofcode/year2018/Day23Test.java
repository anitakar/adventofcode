package org.anita.adventofcode.year2018;

import org.anita.adventofcode.util.FileUtils;
import org.junit.Assert;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

public class Day23Test {

    @Test
    public void shouldParseNanobot() {
        String line = "pos=<-66538252,24214519,54774103>, r=94247941";

        Day23.Nanobot nanobot = new Day23().parseNanobot(line);

        Assert.assertEquals(nanobot.x, -66538252);
        Assert.assertEquals(nanobot.y, 24214519);
        Assert.assertEquals(nanobot.z, 54774103);
        Assert.assertEquals(nanobot.range, 94247941);
    }

    @Test
    public void task1Test1() throws IOException {
        Day23 day23 = new Day23();
        InputStream inputStream = getClass().getResourceAsStream("/testday23.txt");
        List<Day23.Nanobot> nanobots = FileUtils.readElementsLineByLine(inputStream, day23::parseNanobot);

        Assert.assertEquals(7, new Day23().task1(nanobots));
    }

    @Test
    public void task1() throws IOException {
        Day23 day23 = new Day23();
        InputStream inputStream = getClass().getResourceAsStream("/day23.txt");
        List<Day23.Nanobot> nanobots = FileUtils.readElementsLineByLine(inputStream, day23::parseNanobot);

        Assert.assertEquals(595, new Day23().task1(nanobots));
    }
}
