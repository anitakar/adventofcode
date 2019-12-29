package org.anita.adventofcode.year2019;

public class Day17 {

    public char[][] createMap(long[] memory) {
        IntCodeComputer computer = new IntCodeComputer();
        long[] output = computer.interpret(memory, new long[]{});

        int cols = 0;
        while (((char)(output[cols])) != '\n') {
            ++cols;
        }
        int rows = output.length / (cols + 1);

        char[][] result = new char[rows][cols];

        for (int r = 0; r < rows; ++r) {
            for (int c = 0; c < cols; ++c) {
                result[r][c] = (char)output[r * (cols + 1) + c];
            }
        }

        return result;
    }

    public void printMap(char[][] map) {
        for (int r = 0; r < map.length; ++r) {
            for (int c = 0; c < map[0].length; ++c) {
                System.out.print(map[r][c]);
            }
            System.out.println();
        }
    }

    public long task1(long[] memory) {
        char[][] map = createMap(memory);

        printMap(map);

        long sum = 0;

        for (int r = 0; r < map.length; ++r) {
            for (int c = 0; c < map[0].length; ++c) {
                if(map[r][c] == '#' &&
                        (r > 0 && map[r-1][c] == '#') &&
                        (r < map.length - 1 && map[r + 1][c] == '#') &&
                        (c > 0 && map[r][c-1] == '#') &&
                        (c < map[0].length - 1 && map[r][c+1] == '#')
                ) {
                    sum += (r * c);
                }
            }
        }

        return sum;
    }
}
