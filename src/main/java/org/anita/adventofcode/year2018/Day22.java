package org.anita.adventofcode.year2018;

public class Day22 {

    public int task1(int x, int y, int depth) {
        long[][] index = new long[x + 1][y + 1];

        index[0][0] = 0;
        index[x][y] = 0;
        for (int i = 0; i <= x; ++i) {
            index[i][0] = i * 16807;
        }
        for (int i = 0; i <= y; ++i) {
            index[0][i] = i * 48271;
        }
        for (int i = 1; i <= x; ++i) {
            for (int j = 1; j <= y; ++j) {
                index[i][j] = erosionLevel(index[i - 1][j], depth) * erosionLevel(index[i][j - 1], depth);
            }
        }
        index[x][y] = 0;

        int totalRiskLevel = 0;
        for (int i = 0; i <= x; ++i) {
            for (int j = 0; j <= y; ++j) {
                totalRiskLevel += (erosionLevel(index[i][j], depth) % 3);
            }
        }
        return totalRiskLevel;
    }

    private long erosionLevel(long index, int depth) {
        return (index + depth) % 20183;
    }
}
