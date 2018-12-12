package org.anita.adventofcode.year2018;

import org.anita.adventofcode.util.FileUtils;
import org.junit.Assert;
import org.junit.Ignore;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.stream.Collectors;

public class Day12Test {

    @Test
    public void task1Test1() throws IOException {
        InputStream inputStream = getClass().getResourceAsStream("/testday12.txt");
        List<String> strings = FileUtils.readStringsLineByLine(inputStream);
        String[] patterns = strings.stream().filter(s -> s.endsWith("=> #")).map(s -> s.substring(0, 5)).collect(Collectors.toList()).toArray(new String[]{});
        Assert.assertEquals(325, new Day12().task1("#..#.#..##......###...###", patterns, 20));
    }

    @Test
    public void task1() throws IOException {
        InputStream inputStream = getClass().getResourceAsStream("/potpatterns.txt");
        List<String> strings = FileUtils.readStringsLineByLine(inputStream);
        String[] patterns = strings.stream().filter(s -> s.endsWith("=> #")).map(s -> s.substring(0, 5)).collect(Collectors.toList()).toArray(new String[]{});
        Assert.assertEquals(2063, new Day12().task1("####....#...######.###.#...##....#.###.#.###.......###.##..##........##..#.#.#..##.##...####.#..##.#", patterns, 20));
    }

    @Test
    @Ignore
    public void task2() throws IOException {
        InputStream inputStream = getClass().getResourceAsStream("/potpatterns.txt");
        List<String> strings = FileUtils.readStringsLineByLine(inputStream);
        String[] patterns = strings.stream().filter(s -> s.endsWith("=> #")).map(s -> s.substring(0, 5)).collect(Collectors.toList()).toArray(new String[]{});
        Assert.assertEquals(2063, new Day12().task1("####....#...######.###.#...##....#.###.#.###.......###.##..##........##..#.#.#..##.##...####.#..##.#", patterns, 50000000000L));
    }
}
