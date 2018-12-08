package org.anita.adventofcode.year2018;

import org.anita.adventofcode.util.FileUtils;
import org.junit.Assert;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;
import java.util.List;

public class Day4Test {

    private List<String> testLines = Arrays.asList(
            "[1518-11-01 00:00] Guard #10 begins shift",
            "[1518-11-01 00:05] falls asleep",
            "[1518-11-01 00:25] wakes up",
            "[1518-11-01 00:30] falls asleep",
            "[1518-11-01 00:55] wakes up",
            "[1518-11-01 23:58] Guard #99 begins shift",
            "[1518-11-02 00:40] falls asleep",
            "[1518-11-02 00:50] wakes up",
            "[1518-11-03 00:05] Guard #10 begins shift",
            "[1518-11-03 00:24] falls asleep",
            "[1518-11-03 00:29] wakes up",
            "[1518-11-04 00:02] Guard #99 begins shift",
            "[1518-11-04 00:36] falls asleep",
            "[1518-11-04 00:46] wakes up",
            "[1518-11-05 00:03] Guard #99 begins shift",
            "[1518-11-05 00:45] falls asleep",
            "[1518-11-05 00:55] wakes up"
    );

    @Test
    public void task1Test1() {
        Assert.assertEquals(240, new Day4().task1(testLines));
    }

    @Test
    public void task1() throws IOException {
        InputStream inputStream = getClass().getResourceAsStream("/sleeplog.txt" );
        List<String> lines = FileUtils.readStringsLineByLine(inputStream);

        Assert.assertEquals(48680, new Day4().task1(lines));
    }

    @Test
    public void task2Test1() {
        Assert.assertEquals(4455, new Day4().task2(testLines));
    }

    @Test
    public void task2() throws IOException {
        InputStream inputStream = getClass().getResourceAsStream("/sleeplog.txt" );
        List<String> lines = FileUtils.readStringsLineByLine(inputStream);

        Assert.assertEquals(94826, new Day4().task2(lines));
    }
}
