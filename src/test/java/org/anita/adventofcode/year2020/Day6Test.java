package org.anita.adventofcode.year2020;

import org.anita.adventofcode.util.FileUtils;
import org.junit.Assert;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;

public class Day6Test {

    @Test
    public void task1Test() throws IOException {
        Day6.AnswersScanner answersScanner = new Day6.AnswersScanner();

        InputStream inputStream = getClass().getResourceAsStream("/2020_6_test.txt");
        FileUtils.readElementsLineByLine(inputStream, answersScanner::readLine);
        Assert.assertEquals(11, answersScanner.getTotalAnswers());
    }

    @Test
    public void task1() throws IOException {
        Day6.AnswersScanner answersScanner = new Day6.AnswersScanner();

        InputStream inputStream = getClass().getResourceAsStream("/2020_6.txt");
        FileUtils.readElementsLineByLine(inputStream, answersScanner::readLine);
        Assert.assertEquals(6430, answersScanner.getTotalAnswers());
    }

    @Test
    public void task2Test() throws IOException {
        Day6.AnswersScanner answersScanner = new Day6.AnswersScanner(true);

        InputStream inputStream = getClass().getResourceAsStream("/2020_6_test.txt");
        FileUtils.readElementsLineByLine(inputStream, answersScanner::readLine);
        Assert.assertEquals(6, answersScanner.getTotalAnswers());
    }

    @Test
    public void task2() throws IOException {
        Day6.AnswersScanner answersScanner = new Day6.AnswersScanner(true);

        InputStream inputStream = getClass().getResourceAsStream("/2020_6.txt");
        FileUtils.readElementsLineByLine(inputStream, answersScanner::readLine);
        Assert.assertEquals(3125, answersScanner.getTotalAnswers());
    }
}
