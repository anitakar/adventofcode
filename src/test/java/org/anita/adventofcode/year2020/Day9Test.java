package org.anita.adventofcode.year2020;

import org.anita.adventofcode.util.FileUtils;
import org.junit.Assert;
import org.junit.Test;

import java.util.List;
import java.util.function.Function;

public class Day9Test {

    @Test
    public void task1() throws Exception {
        final List<Long> numbers = FileUtils.readElementsLineByLine(getClass().getResourceAsStream("/2020_9.txt"), (Function<String, Long>) Long::parseLong);

        Assert.assertEquals(1930745883, new Day9.XMASEncoder().findWeakness(numbers));
    }

    @Test
    public void task2() throws Exception {
        final List<Long> numbers = FileUtils.readElementsLineByLine(getClass().getResourceAsStream("/2020_9.txt"), (Function<String, Long>) Long::parseLong);

        Assert.assertEquals(268878261, new Day9.XMASEncoder().findEncryptionWeakness(numbers));
    }
}
