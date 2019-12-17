package org.anita.adventofcode.year2019;

import org.anita.adventofcode.util.FileUtils;
import org.junit.Assert;
import org.junit.Test;

import java.io.IOException;
import java.net.URISyntaxException;

public class Day17Test {

    @Test
    public void task1() throws IOException, URISyntaxException {
        long[] memory = FileUtils.readLongArrayFromSingleLine("/2019_17.txt", ",");
        Assert.assertEquals(6520, new Day17().task1(memory));
    }
}
