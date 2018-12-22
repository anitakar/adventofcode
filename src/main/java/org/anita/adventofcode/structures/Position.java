package org.anita.adventofcode.structures;

import java.util.Objects;

public class Position implements Comparable<Position> {
    public int x, y;

    public Position(int x, int y) {
        this.x = x;
        this.y = y;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Position position = (Position) o;
        return x == position.x &&
                y == position.y;
    }

    public Position up() {
        return new Position(x, y - 1);
    }

    public Position down() {
        return new Position(x, y + 1);
    }

    public Position left() {
        return new Position(x - 1, y);
    }

    public Position right() {
        return new Position(x + 1, y);
    }

    public Position upLeft() {
        return new Position(x - 1, y - 1);
    }

    public Position upRight() {
        return new Position(x + 1, y - 1);
    }

    public Position downLeft() {
        return new Position(x - 1, y + 1);
    }

    public Position downRight() {
        return new Position(x + 1, y + 1);
    }

    public Position[] allNeighbours() {
        return new Position[] {
                up(), down(), left(), right(), upLeft(), upRight(), downLeft(), downRight()
        };
    }

    public Position[] perpendicularNeighbours() {
        return new Position[] {
                up(), right(), down(), left()
        };
    }

    public Position[] readingNeighbours() {
        return new Position[] {
                up(), left(), right(), down()
        };
    }

    public int manhattanDistance(Position other) {
        return Math.abs(this.x - other.x) + Math.abs(this.y - other.y);
    }

    @Override
    public int hashCode() {
        return Objects.hash(x, y);
    }

    @Override
    public String toString() {
        return "Position{" +
                "x=" + x +
                ", y=" + y +
                '}';
    }

    @Override
    public int compareTo(Position o) {
        int diffy = y - o.y;
        if (diffy != 0) {
            return diffy;
        }
        return x - o.x;
    }
}
