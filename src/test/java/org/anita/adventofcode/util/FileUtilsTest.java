package org.anita.adventofcode.util;

import org.junit.Assert;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

public class FileUtilsTest {

    @Test
    public void shouldReadFile() throws IOException {
        InputStream inputStream = getClass().getResourceAsStream("/frequencies.csv");

        List<Integer> result = FileUtils.readIntsLineByLine(inputStream);

        Assert.assertEquals(953, result.size());
    }

}