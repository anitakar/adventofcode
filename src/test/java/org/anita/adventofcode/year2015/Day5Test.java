package org.anita.adventofcode.year2015;

import org.anita.adventofcode.util.FileUtils;
import org.anita.adventofcode.year2018.Day3;
import org.junit.Assert;
import org.junit.Test;

import javax.xml.bind.DatatypeConverter;
import java.io.IOException;
import java.io.InputStream;
import java.security.NoSuchAlgorithmException;
import java.util.List;

public class Day5Test {

    private Day5 day5 = new Day5();

    @Test
    public void testIsNice() {
        Assert.assertTrue(day5.isNice("ugknbfddgicrmopn"));
        Assert.assertTrue(day5.isNice("aaa"));
        Assert.assertFalse(day5.isNice("jchzalrnumimnmhp"));
        Assert.assertFalse(day5.isNice("haegwjzuvuyypxyu"));
        Assert.assertFalse(day5.isNice("dvszwmarrgswjxmb"));
    }

    @Test
    public void testTask1() throws IOException {
        InputStream inputStream = getClass().getResourceAsStream("/2015_5.txt");
        List<String> words = FileUtils.readStringsLineByLine(inputStream);
        long count = words.stream().filter(w -> day5.isNice(w)).count();
        System.out.println(count);
    }

    @Test
    public void testIsNice2() {
        Assert.assertTrue(day5.isNice2("qjhvhtzxzqqjkmpb"));
        Assert.assertTrue(day5.isNice2("xxyxx"));
        Assert.assertFalse(day5.isNice2("uurcxstgmygtbstg"));
        Assert.assertFalse(day5.isNice2("ieodomkazucvgmuy"));
    }

    @Test
    public void testTask2() throws IOException {
        InputStream inputStream = getClass().getResourceAsStream("/2015_5.txt");
        List<String> words = FileUtils.readStringsLineByLine(inputStream);
        long count = words.stream().filter(w -> day5.isNice2(w)).count();
        System.out.println(count);
    }

}
