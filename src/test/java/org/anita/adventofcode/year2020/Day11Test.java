package org.anita.adventofcode.year2020;

import org.anita.adventofcode.util.FileUtils;
import org.junit.Assert;
import org.junit.Test;

import java.util.List;

public class Day11Test {
    @Test
    public void task1Test() throws Exception {
        final List<char[]> chars = FileUtils.readElementsLineByLine(getClass().getResourceAsStream("/2020_11_test.txt"), String::toCharArray);
        char[][] board = new char[chars.size()][chars.get(0).length];
        for (int i = 0; i < board.length; ++i) {
            board[i] = chars.get(i);
        }

        final Day11.GameOfLifeModified game = new Day11.GameOfLifeModified(board);
        game.play();
        Assert.assertEquals(37, game.getOccupiedSeats());
    }

    @Test
    public void task1() throws Exception {
        final List<char[]> chars = FileUtils.readElementsLineByLine(getClass().getResourceAsStream("/2020_11.txt"), String::toCharArray);
        char[][] board = new char[chars.size()][chars.get(0).length];
        for (int i = 0; i < board.length; ++i) {
            board[i] = chars.get(i);
        }

        final Day11.GameOfLifeModified game = new Day11.GameOfLifeModified(board);
        game.play();
        Assert.assertEquals(2316, game.getOccupiedSeats());
    }

    @Test
    public void task2Test() throws Exception {
        final List<char[]> chars = FileUtils.readElementsLineByLine(getClass().getResourceAsStream("/2020_11_test.txt"), String::toCharArray);
        char[][] board = new char[chars.size()][chars.get(0).length];
        for (int i = 0; i < board.length; ++i) {
            board[i] = chars.get(i);
        }

        final Day11.GameOfLifeModified game = new Day11.GameOfLifeModified(board);
        game.play2();
        Assert.assertEquals(26, game.getOccupiedSeats());
    }

    @Test
    public void task2() throws Exception {
        final List<char[]> chars = FileUtils.readElementsLineByLine(getClass().getResourceAsStream("/2020_11.txt"), String::toCharArray);
        char[][] board = new char[chars.size()][chars.get(0).length];
        for (int i = 0; i < board.length; ++i) {
            board[i] = chars.get(i);
        }

        final Day11.GameOfLifeModified game = new Day11.GameOfLifeModified(board);
        game.play2();
        Assert.assertEquals(2128, game.getOccupiedSeats());
    }
}
