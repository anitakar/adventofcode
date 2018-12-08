package org.anita.adventofcode.year2018;

import org.anita.adventofcode.util.FileUtils;
import org.junit.Assert;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;
import java.util.List;

public class Day3Test {

    @Test
    public void shouldParseClaim() {
        String line = "#1 @ 565,109: 14x24";

        Day3.Claim claim =new Day3().parseClaim(line);

        Assert.assertEquals(claim.id, 1);
        Assert.assertEquals(claim.left, 565);
        Assert.assertEquals(claim.top, 109);
        Assert.assertEquals(claim.width, 14);
        Assert.assertEquals(claim.height, 24);
    }

    @Test
    public void task1Test1() {
        List<Day3.Claim> claims = Arrays.asList(
                new Day3.Claim(1, 1, 3, 4, 4),
                new Day3.Claim(2, 3, 1, 4, 4),
                new Day3.Claim(3, 5, 5, 2, 2)
        );

        Assert.assertEquals(4, new Day3().task1(claims));
    }

    @Test
    public void task1() throws IOException {
        Day3 day3 = new Day3();
        InputStream inputStream = getClass().getResourceAsStream("/claims.txt");
        List<Day3.Claim> claims = FileUtils.readElementsLineByLine(inputStream, day3::parseClaim);

        Assert.assertEquals(97218, new Day3().task1(claims));
    }

    @Test
    public void task2Test1() {
        List<Day3.Claim> claims = Arrays.asList(
                new Day3.Claim(1, 1, 3, 4, 4),
                new Day3.Claim(2, 3, 1, 4, 4),
                new Day3.Claim(3, 5, 5, 2, 2)
        );

        Assert.assertEquals(3, new Day3().task2(claims));
    }

    @Test
    public void task2() throws IOException {
        Day3 day3 = new Day3();
        InputStream inputStream = getClass().getResourceAsStream("/claims.txt");
        List<Day3.Claim> claims = FileUtils.readElementsLineByLine(inputStream, day3::parseClaim);

        Assert.assertEquals(717, new Day3().task2(claims));
    }
}
