package org.anita.adventofcode.year2019;

import org.anita.adventofcode.structures.Position2D;

import java.util.HashSet;

public class Day3 {

    public int task1(String[] path1, String[] path2) {
        HashSet<Position2D> path1Points = pathPoints(path1);
        HashSet<Position2D> path2Points = pathPoints(path2);

        path1Points.retainAll(path2Points);

        Position2D origin = new Position2D(0, 0);
        return path1Points.stream().mapToInt(p -> p.manhattanDistance(origin)).min().orElse(Integer.MAX_VALUE);
    }

    public int task2(String[] path1, String[] path2) {
        HashSet<Position2D> path1Points = pathPoints(path1);
        HashSet<Position2D> path2Points = pathPoints(path2);

        path1Points.retainAll(path2Points);

        int minTotalSteps = Integer.MAX_VALUE;
        for (Position2D intersection : path1Points) {
            int firstSteps = stepsUntil(path1, intersection);
            int secondSteps = stepsUntil(path2, intersection);
            if (firstSteps + secondSteps < minTotalSteps) {
                minTotalSteps = firstSteps + secondSteps;
            }
        }
        return minTotalSteps;
    }

    private HashSet<Position2D> pathPoints(String[] path) {
        HashSet<Position2D> result = new HashSet<>();
        Position2D cur = new Position2D(0, 0);
        for (String move : path) {
            char d = move.charAt(0);
            int steps = Integer.parseInt(move.substring(1));
            for (int i = 0; i < steps; ++i) {
                if (d == 'U') {
                    cur = cur.up();
                    result.add(cur);
                } else if (d == 'D') {
                    cur = cur.down();
                    result.add(cur);
                } else if (d == 'L') {
                    cur = cur.left();
                    result.add(cur);
                } else if (d == 'R') {
                    cur = cur.right();
                    result.add(cur);
                }
            }
        }
        return result;
    }

    private int stepsUntil(String[] path, Position2D pos) {
        Position2D cur = new Position2D(0, 0);
        int stepsTaken = 0;
        for (String move : path) {
            char d = move.charAt(0);
            int steps = Integer.parseInt(move.substring(1));
            for (int i = 0; i < steps; ++i) {
                stepsTaken += 1;
                if (d == 'U') {
                    cur = cur.up();
                } else if (d == 'D') {
                    cur = cur.down();
                } else if (d == 'L') {
                    cur = cur.left();
                } else if (d == 'R') {
                    cur = cur.right();
                }
                if (pos.equals(cur)) {
                    return stepsTaken;
                }
            }
        }
        return -1;
    }
}