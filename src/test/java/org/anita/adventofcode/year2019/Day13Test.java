package org.anita.adventofcode.year2019;

import org.anita.adventofcode.util.FileUtils;
import org.junit.Assert;
import org.junit.Test;

import java.io.IOException;
import java.net.URISyntaxException;

public class Day13Test {

    @Test
    public void task1() throws IOException, URISyntaxException {
        final long[] memory = FileUtils.readLongArrayFromSingleLine("/2019_13.txt", ",");
        Assert.assertEquals(284, new Day13().task1(memory));
    }

    @Test
    public void task2() throws IOException, URISyntaxException {
        final long[] memory = FileUtils.readLongArrayFromSingleLine("/2019_13.txt", ",");
        Assert.assertEquals(13581, new Day13().task2(memory));
    }
}
