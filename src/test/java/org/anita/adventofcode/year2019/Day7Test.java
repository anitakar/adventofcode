package org.anita.adventofcode.year2019;

import org.anita.adventofcode.util.FileUtils;
import org.junit.Assert;
import org.junit.Test;

import java.io.IOException;
import java.net.URISyntaxException;

public class Day7Test {

    @Test
    public void task1Test1() throws IOException, URISyntaxException {
        int[] memory = FileUtils.readIntArrayFromSingleLine("/2019_7_test_1.txt", ",");

        Assert.assertEquals(43210, new Day7.Program(memory, new int[] {4,3,2,1,0}).interpret());
    }

    @Test
    public void task1Test2() throws IOException, URISyntaxException {
        int[] memory = FileUtils.readIntArrayFromSingleLine("/2019_7_test_2.txt", ",");

        Assert.assertEquals(54321, new Day7.Program(memory, new int[] {0,1,2,3,4}).interpret());
    }

    @Test
    public void task1Test3() throws IOException, URISyntaxException {
        int[] memory = FileUtils.readIntArrayFromSingleLine("/2019_7_test_3.txt", ",");

        Assert.assertEquals(65210, new Day7.Program(memory, new int[] {1,0,4,3,2}).interpret());
    }

    @Test
    public void task1Test4() throws IOException, URISyntaxException {
        int[] memory = FileUtils.readIntArrayFromSingleLine("/2019_7_test_3.txt", ",");

        int maxOutput = Integer.MIN_VALUE;
        for (int p1 = 0; p1 <= 4; ++p1) {
            for (int p2 = 0; p2 <= 4; ++p2) {
                if (p1 != p2) {
                    for (int p3 = 0; p3 <= 4; ++p3) {
                        if (p3 != p1 && p3 != p2) {
                            for (int p4 = 0; p4 <= 4; ++p4) {
                                if (p4 != p1 && p4 != p2 && p4 != p3) {
                                    for (int p5 = 0; p5 <= 4; ++p5) {
                                        if (p5 != p1 && p5 != p2 && p5 != p3 && p5 != p4) {
                                            int output = new Day7.Program(memory, new int[]{p1, p2, p3, p4, p5}).interpret();
                                            if (output > maxOutput) {
                                                maxOutput = output;
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }

        Assert.assertEquals(65210, maxOutput);
    }

    @Test
    public void task1() throws IOException, URISyntaxException {
        int[] memory = FileUtils.readIntArrayFromSingleLine("/2019_7.txt", ",");

        int maxOutput = Integer.MIN_VALUE;
        for (int p1 = 0; p1 <= 4; ++p1) {
            for (int p2 = 0; p2 <= 4; ++p2) {
                if (p1 != p2) {
                    for (int p3 = 0; p3 <= 4; ++p3) {
                        if (p3 != p1 && p3 != p2) {
                            for (int p4 = 0; p4 <= 4; ++p4) {
                                if (p4 != p1 && p4 != p2 && p4 != p3) {
                                    for (int p5 = 0; p5 <= 4; ++p5) {
                                        if (p5 != p1 && p5 != p2 && p5 != p3 && p5 != p4) {
                                            int output = new Day7.Program(memory, new int[]{p1, p2, p3, p4, p5}).interpret();
                                            if (output > maxOutput) {
                                                maxOutput = output;
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }

        Assert.assertEquals(118936, maxOutput);
    }
}
