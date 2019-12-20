package org.anita.adventofcode.year2019;

import org.anita.adventofcode.util.FileUtils;
import org.junit.Assert;
import org.junit.Test;

import java.io.IOException;
import java.net.URISyntaxException;

public class Day19Test {

    @Test
    public void task1() throws IOException, URISyntaxException {
        long[] memory = FileUtils.readLongArrayFromSingleLine("/2019_19.txt", ",");
        Assert.assertEquals(211, new Day19().task1(memory));
    }
}
