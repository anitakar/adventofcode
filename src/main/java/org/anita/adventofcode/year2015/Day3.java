package org.anita.adventofcode.year2015;

import org.anita.adventofcode.structures.Position2D;

import java.util.HashSet;

public class Day3 {

    public int numUniqueHouses(String path) {
        HashSet<Position2D> visitedPositions = new HashSet<>();
        Position2D current = new Position2D(0, 0);
        visitedPositions.add(current);
        for (char c : path.toCharArray()) {
            if (c == '^') {
                current = current.up();
            } else if (c == 'v') {
                current = current.down();
            } else if (c == '>') {
                current = current.right();
            } else {
                current = current.left();
            }
            visitedPositions.add(current);
        }
        return visitedPositions.size();
    }

    public int numUniqueHousesWithRobo(String path) {
        HashSet<Position2D> visitedPositions = new HashSet<>();
        Position2D santa = new Position2D(0, 0);
        Position2D roboSanta = new Position2D(0, 0);
        visitedPositions.add(santa);
        visitedPositions.add(roboSanta);
        boolean santasTurn = true;
        for (char c : path.toCharArray()) {
            Position2D current = santasTurn ? santa : roboSanta;
            if (c == '^') {
                current = current.up();
            } else if (c == 'v') {
                current = current.down();
            } else if (c == '>') {
                current = current.right();
            } else {
                current = current.left();
            }
            visitedPositions.add(current);
            if (santasTurn) {
                santa = current;
            } else {
                roboSanta = current;
            }
            santasTurn = !santasTurn;
        }
        return visitedPositions.size();
    }
}