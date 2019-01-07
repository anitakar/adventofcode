package org.anita.adventofcode.year2018;

public class Day22 {

    long[][] map;

    public int task1(int x, int y, int depth) {
        calculateMap(x, y, depth);

        int totalRiskLevel = 0;
        for (int i = 0; i <= x; ++i) {
            for (int j = 0; j <= y; ++j) {
                totalRiskLevel += map[i][j];
            }
        }
        return totalRiskLevel;
    }

    private void calculateMap(int x, int y, int depth) {
        map = new long[x + 1][y + 1];

        map[0][0] = 0;
        map[x][y] = 0;
        for (int i = 0; i <= x; ++i) {
            map[i][0] = i * 16807;
        }
        for (int i = 0; i <= y; ++i) {
            map[0][i] = i * 48271;
        }
        for (int i = 1; i <= x; ++i) {
            for (int j = 1; j <= y; ++j) {
                map[i][j] = erosionLevel(map[i - 1][j], depth) * erosionLevel(map[i][j - 1], depth);
            }
        }
        map[x][y] = 0;

        for (int i = 0; i <= x; ++i) {
            for (int j = 0; j <= y; ++j) {
                map[i][j] = erosionLevel(map[i][j], depth) % 3;
            }
        }
    }

    private long erosionLevel(long index, int depth) {
        return (index + depth) % 20183;
    }

    public int task2(int x, int y, int depth) {
        calculateMap(7 * x, 7 * y, depth);


        return 0;
    }
}
