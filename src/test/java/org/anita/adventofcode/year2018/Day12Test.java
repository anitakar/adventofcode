package org.anita.adventofcode.year2018;

import org.anita.adventofcode.util.FileUtils;
import org.junit.Assert;
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
    public void task2() throws IOException {
        InputStream inputStream = getClass().getResourceAsStream("/potpatterns.txt");
        List<String> strings = FileUtils.readStringsLineByLine(inputStream);
        String[] patterns = strings.stream().filter(s -> s.endsWith("=> #")).map(s -> s.substring(0, 5)).collect(Collectors.toList()).toArray(new String[]{});
        Assert.assertEquals(3528, new Day12().task1("####....#...######.###.#...##....#.###.#.###.......###.##..##........##..#.#.#..##.##...####.#..##.#", patterns, 100L));
        Assert.assertEquals(6728, new Day12().task1("####....#...######.###.#...##....#.###.#.###.......###.##..##........##..#.#.#..##.##...####.#..##.#", patterns, 200L));
        Assert.assertEquals(9928, new Day12().task1("####....#...######.###.#...##....#.###.#.###.......###.##..##........##..#.#.#..##.##...####.#..##.#", patterns, 300L));
        Assert.assertEquals(13128, new Day12().task1("####....#...######.###.#...##....#.###.#.###.......###.##..##........##..#.#.#..##.##...####.#..##.#", patterns, 400L));
        Assert.assertEquals(1600000000328L, (50_000_000_000L-100)/100*3200 + 3528);
        //progrmatically it would be - wait until middle element stabilizes and a recurring pattern is added at the beginning and at the end
        //calculate how the string would look like after x iterations based on the middle, and recurring pattern at the beginning and the end
        //or easier: calculate sum after 100, 200, 300, try to make linear function y = ax + b, calculate y for given x of iterations
    }
}
