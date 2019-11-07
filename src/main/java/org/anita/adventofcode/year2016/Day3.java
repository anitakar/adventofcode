package org.anita.adventofcode.year2016;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Day3 {

    public long task1(List<Triangle> triangles) {
        return triangles.stream().filter(t -> t.isATraingle()).count();
    }

    public List<Triangle> transposeList(List<Triangle> input) {
        List<Triangle> result = new ArrayList<>(input.size());
        for (int i = 0; i < input.size(); i += 3) {
            result.add(new Triangle(input.get(i).x, input.get(i + 1).x, input.get(i + 2).x));
            result.add(new Triangle(input.get(i).y, input.get(i + 1).y, input.get(i + 2).y));
            result.add(new Triangle(input.get(i).z, input.get(i + 1).z, input.get(i + 2).z));
        }
        return result;
    }

    private static String triangleRegex = "^\\s*(\\d*)\\s*(\\d*)\\s*(\\d*)$";
    private Pattern trianglePattern = Pattern.compile(triangleRegex);

    public Triangle parseTriangle(String line) {
        Matcher matcher = trianglePattern.matcher(line);
        if (matcher.find()) {
            return new Triangle(
                    Integer.parseInt(matcher.group(1)),
                    Integer.parseInt(matcher.group(2)),
                    Integer.parseInt(matcher.group(3))
            );
        }
        return null;
    }

    public static class Triangle {
        final int x;
        final int y;
        final int z;

        public Triangle(int x, int y, int z) {
            this.x = x;
            this.y = y;
            this.z = z;
        }

        public boolean isATraingle() {
            int max = x;
            if (y > max) max = y;
            if (z > max) max = z;

            return x + y + z - max > max;
        }
    }
}
