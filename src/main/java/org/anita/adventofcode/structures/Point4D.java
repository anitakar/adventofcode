package org.anita.adventofcode.structures;

import java.util.Objects;

public class Point4D {
    int x, y, z, t;

    public static Point4D parseLine(String line) {
        String[] splitted = line.split(",");
        return new Point4D(
            Integer.parseInt(splitted[0]),
            Integer.parseInt(splitted[1]),
            Integer.parseInt(splitted[2]),
            Integer.parseInt(splitted[3])
        );
    }

    public Point4D(int x, int y, int z, int t) {
        this.x = x;
        this.y = y;
        this.z = z;
        this.t = t;
    }

    public int manhattanDistance(Point4D other) {
        return Math.abs(this.x - other.x) + Math.abs(this.y - other.y) + Math.abs(this.z - other.z) + Math.abs(this.t - other.t);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Point4D point4D = (Point4D) o;
        return x == point4D.x &&
                y == point4D.y &&
                z == point4D.z &&
                t == point4D.t;
    }

    @Override
    public int hashCode() {
        return Objects.hash(x, y, z, t);
    }

    @Override
    public String toString() {
        return "Point4D{" +
                "x=" + x +
                ", y=" + y +
                ", z=" + z +
                ", t=" + t +
                '}';
    }
}
