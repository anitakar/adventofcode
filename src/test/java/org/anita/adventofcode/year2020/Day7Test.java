package org.anita.adventofcode.year2020;

import javafx.util.Pair;
import org.anita.adventofcode.util.FileUtils;
import org.junit.Assert;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class Day7Test {

    @Test
    public void shouldParseLine() {
        Assert.assertEquals(
                new Pair("shiny gold", new HashSet<>(Arrays.asList(new Pair("dark olive", 1), new Pair("vibrant plum", 2)))),
                new Day7.BagParser().parse("shiny gold bags contain 1 dark olive bag, 2 vibrant plum bags."));
        Assert.assertEquals(
                new Pair("dotted black", new HashSet<>(Arrays.asList(new Pair("no other", 0)))),
                new Day7.BagParser().parse("dotted black bags contain no other bags."));
        Assert.assertEquals(
                new Pair("bright white", new HashSet<>(Arrays.asList(new Pair("shiny gold", 1)))),
                new Day7.BagParser().parse("bright white bags contain 1 shiny gold bag."));
    }

    @Test
    public void task1Test() throws IOException {
        Day7.BagParser bagParser = new Day7.BagParser();

        InputStream inputStream = getClass().getResourceAsStream("/2020_7_test.txt");
        FileUtils.readElementsLineByLine(inputStream, bagParser::parse);
        final Map<String, Set<Pair<String, Integer>>> result = bagParser.getResult();

        Assert.assertEquals(4, new Day7().task1(result).size());
    }

    @Test
    public void task1() throws IOException {
        Day7.BagParser bagParser = new Day7.BagParser();

        InputStream inputStream = getClass().getResourceAsStream("/2020_7.txt");
        FileUtils.readElementsLineByLine(inputStream, bagParser::parse);
        final Map<String, Set<Pair<String, Integer>>> result = bagParser.getResult();

        Assert.assertEquals(144, new Day7().task1(result).size());
    }

    @Test
    public void task2() throws IOException {
        Day7.BagParser bagParser = new Day7.BagParser();

        InputStream inputStream = getClass().getResourceAsStream("/2020_7.txt");
        FileUtils.readElementsLineByLine(inputStream, bagParser::parse);
        final Map<String, Set<Pair<String, Integer>>> result = bagParser.getResult();

        Assert.assertEquals(0, new Day7().task2(result));
    }
}
