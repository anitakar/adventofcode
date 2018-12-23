package org.anita.adventofcode.year2018;

import java.util.Comparator;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Day23 {

    public long task1(List<Nanobot> nanobots) {
        Nanobot max = nanobots.stream().max(Comparator.comparingInt(n -> n.range)).get();
        return nanobots.stream().filter(n ->
            Math.abs(max.x - n.x) + Math.abs(max.y - n.y) + Math.abs(max.z - n.z) <= max.range
        ).count();
    }


    private static String nanobotRegex = "^pos=<(\\-?\\d*),(\\-?\\d*),(\\-?\\d*)>, r=(\\d*)$";
    private Pattern nanobotPattern = Pattern.compile(nanobotRegex);

    public Nanobot parseNanobot(String line) {
        Matcher matcher = nanobotPattern.matcher(line);
        if (matcher.find()) {
            return new Nanobot(
                    Integer.parseInt(matcher.group(1)),
                    Integer.parseInt(matcher.group(2)),
                    Integer.parseInt(matcher.group(3)),
                    Integer.parseInt(matcher.group(4))
            );
        }
        return null;
    }

    public static class Nanobot {
        int x, y, z;
        int range;

        public Nanobot(int x, int y, int z, int range) {
            this.x = x;
            this.y = y;
            this.z = z;
            this.range = range;
        }
    }
}
