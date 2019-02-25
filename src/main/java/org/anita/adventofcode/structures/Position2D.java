package org.anita.adventofcode.structures;

import java.util.Objects;

public class Position2D implements Comparable<Position2D> {
    public int x, y;

    public Position2D(int x, int y) {
        this.x = x;
        this.y = y;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Position2D position = (Position2D) o;
        return x == position.x &&
                y == position.y;
    }

    public Position2D up() {
        return new Position2D(x, y - 1);
    }

    public Position2D down() {
        return new Position2D(x, y + 1);
    }

    public Position2D left() {
        return new Position2D(x - 1, y);
    }

    public Position2D right() {
        return new Position2D(x + 1, y);
    }

    public Position2D upLeft() {
        return new Position2D(x - 1, y - 1);
    }

    public Position2D upRight() {
        return new Position2D(x + 1, y - 1);
    }

    public Position2D downLeft() {
        return new Position2D(x - 1, y + 1);
    }

    public Position2D downRight() {
        return new Position2D(x + 1, y + 1);
    }

    public Position2D[] allNeighbours() {
        return new Position2D[] {
                up(), down(), left(), right(), upLeft(), upRight(), downLeft(), downRight()
        };
    }

    public Position2D[] perpendicularNeighbours() {
        return new Position2D[] {
                up(), right(), down(), left()
        };
    }

    public Position2D[] readingNeighbours() {
        return new Position2D[] {
                up(), left(), right(), down()
        };
    }

    public int manhattanDistance(Position2D other) {
        return Math.abs(this.x - other.x) + Math.abs(this.y - other.y);
    }

    @Override
    public int hashCode() {
        return Objects.hash(x, y);
    }

    @Override
    public String toString() {
        return "Position2D{" +
                "x=" + x +
                ", y=" + y +
                '}';
    }

    @Override
    public int compareTo(Position2D o) {
        int diffy = y - o.y;
        if (diffy != 0) {
            return diffy;
        }
        return x - o.x;
    }
}
