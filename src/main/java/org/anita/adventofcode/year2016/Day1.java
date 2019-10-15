package org.anita.adventofcode.year2016;

import org.anita.adventofcode.structures.Position2D;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Day1 {

    public int manhattanDistance(List<String> moves) {
        int xDistance = 0;
        int yDistance = 0;
        int currentDirection = 0; // N, E, S, W
        for (String move : moves) {
            char turn = move.charAt(0);
            if (turn == 'R') {
                currentDirection += 5;
            } else {
                currentDirection += 3;
            }
            currentDirection = currentDirection % 4;
            switch (currentDirection) {
                case 0:
                    yDistance += Integer.parseInt(move.substring(1));
                    break;
                case 1:
                    xDistance += Integer.parseInt(move.substring(1));
                    break;
                case 2:
                    yDistance -= Integer.parseInt(move.substring(1));
                    break;
                case 3:
                    xDistance -= Integer.parseInt(move.substring(1));
                    break;
            }
        }
        return Math.abs(xDistance) + Math.abs(yDistance);
    }

    public int manhattanDistanceFromVisitedTwice(List<String> moves) {
        Set<Position2D> visited = new HashSet<>();
        visited.add(new Position2D(0, 0));
        int xDistance = 0;
        int yDistance = 0;
        int currentDirection = 0; // N, E, S, W
        for (String move : moves) {
            char turn = move.charAt(0);
            if (turn == 'R') {
                currentDirection += 5;
            } else {
                currentDirection += 3;
            }
            currentDirection = currentDirection % 4;
            switch (currentDirection) {
                case 0: {
                    int steps = Integer.parseInt(move.substring(1));
                    for (int i = 1; i <= steps; ++i) {
                        Position2D pos = new Position2D(xDistance, yDistance + i);
                        if (visited.contains(pos)) {
                            return pos.manhattanDistance(new Position2D(0, 0));
                        } else {
                            visited.add(pos);
                        }
                    }
                    yDistance += steps;
                }
                break;
                case 1: {
                    int steps = Integer.parseInt(move.substring(1));
                    for (int i = 1; i <= steps; ++i) {
                        Position2D pos = new Position2D(xDistance + i, yDistance);
                        if (visited.contains(pos)) {
                            return pos.manhattanDistance(new Position2D(0, 0));
                        } else {
                            visited.add(pos);
                        }
                    }
                    xDistance += steps;
                }
                break;
                case 2: {
                    int steps = Integer.parseInt(move.substring(1));
                    for (int i = 1; i <= steps; ++i) {
                        Position2D pos = new Position2D(xDistance, yDistance - i);
                        if (visited.contains(pos)) {
                            return pos.manhattanDistance(new Position2D(0, 0));
                        } else {
                            visited.add(pos);
                        }
                    }
                    yDistance -= steps;
                }
                break;
                case 3: {
                    int steps = Integer.parseInt(move.substring(1));
                    for (int i = 1; i <= steps; ++i) {
                        Position2D pos = new Position2D(xDistance - i, yDistance);
                        if (visited.contains(pos)) {
                            return pos.manhattanDistance(new Position2D(0, 0));
                        } else {
                            visited.add(pos);
                        }
                    }
                    xDistance -= steps;
                }
                break;
            }
        }
        return Math.abs(xDistance) + Math.abs(yDistance);
    }
}
