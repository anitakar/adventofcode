package org.anita.adventofcode.year2018;

import java.util.Objects;

public class Day11 {

    int serialNumber;

    public Day11(int serialNumber) {
        this.serialNumber = serialNumber;
    }

    public Point task1() {
        int[][] powerLevels = new int[300][300];
        for (int x = 0; x < 300; ++x) {
            for (int y = 0; y < 300; ++y) {
                powerLevels[x][y] = powerLevel(x + 1, y + 1);
            }
        }

        int maxSum = Integer.MIN_VALUE;
        int maxX = 0, maxY = 0;
        for (int x = 0; x < 300 - 3; ++x) {
            for (int y = 0; y < 300 - 3; ++y) {
                int sum = 0;
                for (int i = 0; i < 3; ++i) {
                    for (int j = 0; j < 3; ++j) {
                        sum += powerLevels[x + i][y + j];
                    }
                }
                if (sum > maxSum) {
                    maxSum = sum;
                    maxX = x + 1;
                    maxY = y + 1;
                }
            }
        }

        return new Point(maxX, maxY);
    }

    public Square task2() {
        int[][] powerLevels = new int[300][300];
        for (int x = 0; x < 300; ++x) {
            for (int y = 0; y < 300; ++y) {
                powerLevels[x][y] = powerLevel(x + 1, y + 1);
            }
        }

        int[][] xSoFar = new int[301][301];
        int[][] ySoFar = new int[301][301];
        for (int x = 1; x <= 300; ++x) {
            for (int y = 1; y <= 300; ++y) {
                xSoFar[x][y] = xSoFar[x - 1][y] + powerLevels[x - 1][y - 1];
            }
        }
        for (int y = 1; y <= 300; ++y) {
            for (int x = 1; x <= 300; ++x) {
                ySoFar[x][y] = ySoFar[x][y - 1] + powerLevels[x - 1][y - 1];
            }
        }

        int maxSum = Integer.MIN_VALUE;
        int maxX = 0, maxY = 0, maxSize = 0;
        for (int x = 1; x <= 300; ++x) {
            for (int y = 1; y <= 300; ++y) {
                int sum = 0;
                for (int size = 1; size < Math.min(301 - x, 301 - y); ++size) {
                    if (size == 1) {
                        sum = powerLevels[x - 1][y - 1];
                    } else {
                        sum += (xSoFar[x + size - 1][y + size - 1] - xSoFar[x - 1][y + size - 1] + ySoFar[x + size - 1][y + size - 1] - ySoFar[x + size - 1][y - 1] - powerLevels[x + size - 2][y + size - 2]);
                    }
                    if (sum > maxSum) {
                        maxSum = sum;
                        maxX = x;
                        maxY = y;
                        maxSize = size;
                    }
                }
            }
        }

        return new Square(maxX, maxY, maxSize);
    }

    public int powerLevel(int x, int y) {
        return ((((x + 10) * y + serialNumber) * (x + 10)) % 1000) / 100 - 5;
    }

    public static class Square {
        int x, y, size;

        public Square(int x, int y, int size) {
            this.x = x;
            this.y = y;
            this.size = size;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Square square = (Square) o;
            return x == square.x &&
                    y == square.y &&
                    size == square.size;
        }

        @Override
        public int hashCode() {
            return Objects.hash(x, y, size);
        }

        @Override
        public String toString() {
            return "Square{" +
                    "x=" + x +
                    ", y=" + y +
                    ", size=" + size +
                    '}';
        }
    }

    public static class Point {
        int x, y;

        public Point(int x, int y) {
            this.x = x;
            this.y = y;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Point point = (Point) o;
            return x == point.x &&
                    y == point.y;
        }

        @Override
        public int hashCode() {
            return Objects.hash(x, y);
        }

        @Override
        public String toString() {
            return "Point{" +
                    "x=" + x +
                    ", y=" + y +
                    '}';
        }
    }
}
