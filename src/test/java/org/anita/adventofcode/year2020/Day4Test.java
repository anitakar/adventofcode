package org.anita.adventofcode.year2020;

import org.anita.adventofcode.util.FileUtils;
import org.junit.Assert;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;

public class Day4Test {

//    @Test
//    public void task1Test() throws IOException {
//        Day4.PassportScanner passportScanner = new Day4.PassportScanner();
//
//        InputStream inputStream = getClass().getResourceAsStream("/2020_4_test.txt");
//        FileUtils.readElementsLineByLine(inputStream, passportScanner::readLine);
//        Assert.assertEquals(2, passportScanner.getValidCount());
//    }
//
//    @Test
//    public void task1() throws IOException {
//        Day4.PassportScanner passportScanner = new Day4.PassportScanner();
//
//        InputStream inputStream = getClass().getResourceAsStream("/2020_4.txt");
//        FileUtils.readElementsLineByLine(inputStream, passportScanner::readLine);
//        Assert.assertEquals(221, passportScanner.getValidCount());
//    }

    @Test
    public void task2Test() throws IOException {
        Day4.PassportScanner passportScanner = new Day4.PassportScanner();

        InputStream inputStream = getClass().getResourceAsStream("/2020_4_test.txt");
        FileUtils.readElementsLineByLine(inputStream, passportScanner::readLine);
        Assert.assertEquals(2, passportScanner.getValidCount());
    }

    @Test
    public void task2() throws IOException {
        Day4.PassportScanner passportScanner = new Day4.PassportScanner();

        InputStream inputStream = getClass().getResourceAsStream("/2020_4.txt");
        FileUtils.readElementsLineByLine(inputStream, passportScanner::readLine);
        Assert.assertEquals(140, passportScanner.getValidCount());
    }
}
