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

        int maxSum = Integer.MIN_VALUE;
        int maxX = 0, maxY = 0, maxSize = 0;
        for (int x = 0; x < 300; ++x) {
            for (int y = 0; y < 300; ++y) {
                for (int size = 1; size < Math.min(300 - x, 300 - y); ++size) {
                    int sum = 0;
                    for (int i = 0; i < size; ++i) {
                        for (int j = 0; j < size; ++j) {
                            sum += powerLevels[x + i][y + j];
                        }
                    }
                    if (sum > maxSum) {
                        maxSum = sum;
                        maxX = x + 1;
                        maxY = y + 1;
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
