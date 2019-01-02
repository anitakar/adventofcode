package org.anita.adventofcode.year2018;

import org.anita.adventofcode.util.FileUtils;
import org.junit.Assert;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

public class Day15Test {

    @Test
    public void task1Test1() throws IOException {
        InputStream inputStream = getClass().getResourceAsStream("/test1fightmap.txt");
        List<String> map = FileUtils.readStringsLineByLine(inputStream);

        Assert.assertEquals(27730, new Day15(map).task1());
    }

    @Test
    public void task1Test2() throws IOException {
        InputStream inputStream = getClass().getResourceAsStream("/test2fightmap.txt");
        List<String> map = FileUtils.readStringsLineByLine(inputStream);

        Assert.assertEquals(36334, new Day15(map).task1());
    }

    @Test
    public void task1Test3() throws IOException {
        InputStream inputStream = getClass().getResourceAsStream("/test3fightmap.txt");
        List<String> map = FileUtils.readStringsLineByLine(inputStream);

        Assert.assertEquals(39514, new Day15(map).task1());
    }

    @Test
    public void task1Test4() throws IOException {
        InputStream inputStream = getClass().getResourceAsStream("/test4fightmap.txt");
        List<String> map = FileUtils.readStringsLineByLine(inputStream);

        Assert.assertEquals(27755, new Day15(map).task1());
    }

    @Test
    public void task1Test5() throws IOException {
        InputStream inputStream = getClass().getResourceAsStream("/test5fightmap.txt");
        List<String> map = FileUtils.readStringsLineByLine(inputStream);

        Assert.assertEquals(28944, new Day15(map).task1());
    }

    @Test
    public void task1Test6() throws IOException {
        InputStream inputStream = getClass().getResourceAsStream("/test6fightmap.txt");
        List<String> map = FileUtils.readStringsLineByLine(inputStream);

        Assert.assertEquals(18740, new Day15(map).task1());
    }

    @Test
    public void task1Test7() throws IOException {
        InputStream inputStream = getClass().getResourceAsStream("/test7fightmap.txt");
        List<String> map = FileUtils.readStringsLineByLine(inputStream);

        Assert.assertEquals(27828, new Day15(map).task1());
    }

    @Test
    public void task1Test8() throws IOException {
        InputStream inputStream = getClass().getResourceAsStream("/test8fightmap.txt");
        List<String> map = FileUtils.readStringsLineByLine(inputStream);

        Assert.assertEquals(16533, new Day15(map).task1());
    }

    @Test
    public void task1Test9() throws IOException {
        InputStream inputStream = getClass().getResourceAsStream("/test9fightmap.txt");
        List<String> map = FileUtils.readStringsLineByLine(inputStream);

        Assert.assertEquals(134, new Day15(map).task1());
    }

    @Test
    public void task1() throws IOException {
        InputStream inputStream = getClass().getResourceAsStream("/fightmap.txt");
        List<String> map = FileUtils.readStringsLineByLine(inputStream);

        Assert.assertEquals(263327, new Day15(map).task1());
    }
}
