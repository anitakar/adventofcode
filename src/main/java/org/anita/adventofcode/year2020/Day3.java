package org.anita.adventofcode.year2020;

import java.util.LinkedList;
import java.util.List;

public class Day3 {

    public int task1(Map map, int stepsRight, int stepsDown) {
        int treesCount = 0;
        int y = 0;
        int x = 0;
        do {
            y += stepsDown;
            x += stepsRight;
            if (y < map.height() && map.read(x, y) == '#') {
                treesCount += 1;
            }
        } while (y < map.height() - 1);
        return treesCount;
    }

    public static class Map {
        private char[][] pattern;

        public Map(char[][] pattern) {
            this.pattern = pattern;
        }

        public int height() {
            return pattern.length;
        }

        public int recurrenceWidth() {
            return pattern[0].length;
        }

        public char read(int x, int y) {
            return pattern[y][x % recurrenceWidth()];
        }
    }

    public static class MapReader {
        private List<char[]> lines = new LinkedList<>();

        public boolean readLine(String line) {
            lines.add(line.toCharArray());
            return true;
        }

        public Map createMap() {
            char[][] result = new char[lines.size()][lines.get(0).length];
            for (int i = 0; i < lines.size(); ++i) {
                result[i] = lines.get(i);
            }
            return new Map(result);
        }
    }
}
