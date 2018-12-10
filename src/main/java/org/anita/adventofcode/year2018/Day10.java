package org.anita.adventofcode.year2018;

import org.anita.adventofcode.util.FileUtils;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Day10 {

    public static void main(String[] args) throws IOException {
        Day10 day10 = new Day10();
        InputStream inputStream = Day10.class.getResourceAsStream("/day10.txt");
        List<Day10.PositionAndVelocity> pvs = FileUtils.readElementsLineByLine(inputStream, day10::parsePointAndVelocity);

        day10.task1(pvs, 100000);
    }

    public void task1(List<PositionAndVelocity> positionAndVelocities, int maxIteration) {
        for (int i = 0; i < maxIteration; ++i) {
            for (PositionAndVelocity pv : positionAndVelocities) {
                pv.x += pv.dx;
                pv.y += pv.dy;
            }
            print(positionAndVelocities, i+1);
        }
    }

    private void print(List<PositionAndVelocity> positionAndVelocities, int iteration) {
        int minx = Integer.MAX_VALUE, maxx = Integer.MIN_VALUE, miny= Integer.MAX_VALUE, maxy = Integer.MIN_VALUE;
        for (PositionAndVelocity pv : positionAndVelocities) {
            minx = Math.min(minx, pv.x);
            maxx = Math.max(maxx, pv.x);
            miny = Math.min(miny, pv.y);
            maxy = Math.max(maxy, pv.y);
        }

        if (maxx - minx > 500 || maxy - miny > 20) {
            return;
        }

        System.out.println(iteration);

        HashSet<PositionAndVelocity> pvsSet = new HashSet<>(positionAndVelocities);
        for (int y = miny; y <= maxy; ++y) {
            for (int x = minx; x <= maxx; ++x) {
                if (pvsSet.contains(new PositionAndVelocity(x, y, 0, 0))) {
                    System.out.print("#");
                } else {
                    System.out.print(".");
                }
            }
            System.out.println();
        }
        System.out.println("----------------------------------");
    }

    public static class PositionAndVelocity {
        int x, y, dx, dy;

        public PositionAndVelocity(int x, int y, int dx, int dy) {
            this.x = x;
            this.y = y;
            this.dx = dx;
            this.dy = dy;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            PositionAndVelocity that = (PositionAndVelocity) o;
            return x == that.x &&
                    y == that.y;
        }

        @Override
        public int hashCode() {
            return Objects.hash(x, y);
        }
    }

    public static String pointAndVelocityRegex = "^position=<\\s*(-?\\d+),\\s*(-?\\d+)> velocity=<\\s*(-?\\d+),\\s*(-?\\d+)>$";
    private Pattern pointPattern = Pattern.compile(pointAndVelocityRegex);

    public PositionAndVelocity parsePointAndVelocity(String line) {
        Matcher matcher = pointPattern.matcher(line);
        if (matcher.find()) {
            return new PositionAndVelocity(
                    Integer.parseInt(matcher.group(1)),
                    Integer.parseInt(matcher.group(2)),
                    Integer.parseInt(matcher.group(3)),
                    Integer.parseInt(matcher.group(4))
            );
        }
        return null;
    }
}
