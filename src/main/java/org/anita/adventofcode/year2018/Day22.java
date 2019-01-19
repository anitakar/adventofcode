package org.anita.adventofcode.year2018;

import org.anita.adventofcode.structures.Position;

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

    private static final long ROCKY = 0;
    private static final long WET = 1;
    private static final long NARROW = 2;

    private static final int TORCH = 0;
    private static final int CLIMBING_GEAR = 1;
    private static final int NEITHER = 2;

    public int task2(int x, int y, int depth) {
        calculateMap(7 * x, 7 * y, depth);

        Position currentPosition = new Position(0, 0);
        int currentGear = TORCH;
        Position targetPosition = new Position(x, y);
        int targetGear = TORCH;


        return 0;
    }

    public int cost(Position target, int currentEquipment) {
        long targetRegionType = map[target.x][target.y];
        if (targetRegionType == ROCKY) {
            if (currentEquipment == CLIMBING_GEAR || currentEquipment == TORCH) {
                return 1;
            } else {
                return 7;
            }
        } else if (targetRegionType == WET) {
            if (currentEquipment == CLIMBING_GEAR || currentEquipment == NEITHER) {
                return 1;
            } else {
                return 7;
            }
        } else if (targetRegionType == NARROW) {
            if (currentEquipment == TORCH || currentEquipment == NEITHER) {
                return 1;
            } else {
                return 7;
            }
        }
        return Integer.MAX_VALUE;
    }
}
