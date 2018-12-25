package org.anita.adventofcode.year2018;

import org.anita.adventofcode.util.FileUtils;
import org.junit.Assert;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;

public class Day20Test {

    @Test
    public void task1Test1() {
        Day20 day20 = new Day20();
        day20.readMap("WNE");
        day20.findAllShortestPaths();
        Assert.assertEquals(3, day20.getLongestShortestPathLength());
    }

    @Test
    public void task1Test2() {
        Day20 day20 = new Day20();
        day20.readMap("ENWWW(NEEE|SSE(EE|N))");
        day20.findAllShortestPaths();
        Assert.assertEquals(10, day20.getLongestShortestPathLength());
    }

    @Test
    public void readMapTest1() {
        new Day20().readMap("EE|N");
    }

    @Test
    public void readMapTest2() {
        new Day20().readMap("E(NN|SS)E");
    }

    @Test
    public void readMapTest3() {
        new Day20().readMap("E(S|)E|N");
    }

    @Test
    public void readMapTest4() {
        new Day20().readMap("E(NN|S)E");
    }

    @Test
    public void task1Test3() {
        Day20 day20 = new Day20();
        day20.readMap("ENNWSWW(NEWS|)SSSEEN(WNSE|)EE(SWEN|)NNN");
        day20.findAllShortestPaths();
        Assert.assertEquals(18, day20.getLongestShortestPathLength());
    }

    @Test
    public void task1Test4() {
        Day20 day20 = new Day20();
        day20.readMap("ESSWWN(E|NNENN(EESS(WNSE|)SSS|WWWSSSSE(SW|NNNE)))");
        day20.findAllShortestPaths();
        Assert.assertEquals(23, day20.getLongestShortestPathLength());
    }

    @Test
    public void task1Test5() {
        Day20 day20 = new Day20();
        day20.readMap("WSSEESWWWNW(S|NENNEEEENN(ESSSSW(NWSW|SSEN)|WSWWN(E|WWS(E|SS))))");
        day20.findAllShortestPaths();
        Assert.assertEquals(31, day20.getLongestShortestPathLength());
    }

    @Test
    public void task1() throws IOException {
        InputStream inputStream = getClass().getResourceAsStream("/day20.txt");
        String regex = FileUtils.readStringsLineByLine(inputStream).get(0);
        regex = regex.substring(1, regex.length() - 1);

        Day20 day20 = new Day20();
        day20.readMap(regex);
        day20.findAllShortestPaths();
        Assert.assertEquals(3014, day20.getLongestShortestPathLength());
    }

    @Test
    public void task2() throws IOException {
        InputStream inputStream = getClass().getResourceAsStream("/day20.txt");
        String regex = FileUtils.readStringsLineByLine(inputStream).get(0);
        regex = regex.substring(1, regex.length() - 1);

        Day20 day20 = new Day20();
        day20.readMap(regex);
        day20.findAllShortestPaths();
        Assert.assertEquals(8279, day20.countAllPathsLargerOrEqualThan(1000));
    }
}
