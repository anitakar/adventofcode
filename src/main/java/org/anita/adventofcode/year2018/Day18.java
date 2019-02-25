package org.anita.adventofcode.year2018;

import org.anita.adventofcode.structures.Position2D;

import java.util.List;

public class Day18 {

    char[][] map;

    public Day18(List<String> lines) {
        readMap(lines);
        //printMap();
    }

    private void readMap(List<String> lines) {
        map = new char[lines.size()][lines.get(0).length()];
        for (int x = 0; x < lines.size(); ++x) {
            for (int y = 0; y < lines.get(0).length(); ++y) {
                map[x][y] = lines.get(x).charAt(y);
            }
        }
    }

    private void printMap() {
        for (int y = 0; y < map[0].length; ++y) {
            for (int x = 0; x < map.length; ++x) {
                System.out.print(map[x][y]);
            }
            System.out.println();
        }
        System.out.println("---------------");
    }

    public int task1(int numberOfIterations) {
        //long before = System.currentTimeMillis();
        for (int i = 0; i < numberOfIterations; ++i) {
            char[][] newMap = new char[map.length][map[0].length];
            for (int y = 0; y < map[0].length; ++y) {
                for (int x = 0; x < map.length; ++x) {
                    String neighboursSummary = getNeighbours(new Position2D(x, y));
                    if (map[x][y] == '.') {
                        int treesCount = 0;
                        for (Character c : neighboursSummary.toCharArray()) {
                            if (c.equals('|')) treesCount += 1;
                        }
                        if (treesCount >= 3) {
                            newMap[x][y] = '|';
                        } else {
                            newMap[x][y] = '.';
                        }
                    } else if (map[x][y] == '|') {
                        int lumberCount = 0;
                        for (Character c : neighboursSummary.toCharArray()) {
                            if (c.equals('#')) lumberCount += 1;
                        }
                        if (lumberCount >= 3) {
                            newMap[x][y] = '#';
                        } else {
                            newMap[x][y] = '|';
                        }
                    } else if (map[x][y] == '#') {
                        int lumberCount = 0;
                        int treesCount = 0;
                        for (Character c : neighboursSummary.toCharArray()) {
                            if (c.equals('|')) treesCount += 1;
                            if (c.equals('#')) lumberCount += 1;
                        }
                        if (lumberCount >= 1 && treesCount >= 1) {
                            newMap[x][y] = '#';
                        } else {
                            newMap[x][y] = '.';
                        }
                    }
                }
            }
            map = newMap;
            if (i % 1000 == 0) {
                //System.out.println("Iteration " + i + " after " + (System.currentTimeMillis() - before) + " millis");
                //System.out.println(totalResourceValue());
                //before = System.currentTimeMillis();
            }
            //printMap();
        }
        return totalResourceValue();
    }

    private int totalResourceValue() {
        int woodSum = 0, lumberSum = 0;
        for (int y = 0; y < map[0].length; ++y) {
            for (int x = 0; x < map.length; ++x) {
                if (map[x][y] == '|') {
                    woodSum += 1;
                } else if (map[x][y] == '#') {
                    lumberSum += 1;
                }
            }
        }
        return woodSum * lumberSum;
    }

    private String getNeighbours(Position2D position) {
        StringBuilder result = new StringBuilder();
        for (Position2D neighbour : position.allNeighbours()) {
            if (inArea(neighbour)) {
                result.append(map[neighbour.x][neighbour.y]);
            }
        }
        return result.toString();
    }

    private boolean inArea(Position2D position) {
        return position.x >= 0 && position.x < map.length && position.y >= 0 && position.y < map[0].length;
    }

}
