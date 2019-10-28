package org.anita.adventofcode.year2018;

import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Day23 {

    public long task1(List<Nanobot> nanobots) {
        Nanobot max = nanobots.stream().max(Comparator.comparingLong(n -> n.range)).get();
        return nanobots.stream().filter(n ->
                Math.abs(max.x - n.x) + Math.abs(max.y - n.y) + Math.abs(max.z - n.z) <= max.range
        ).count();
    }

    public long task2(List<Nanobot> nanobots) {
        List<HashSet<Nanobot>> intersect = new ArrayList<>();
        for (int i = 0; i < nanobots.size(); ++i) {
            intersect.add(new HashSet<>());
        }
        for (int i = 0; i < nanobots.size(); ++i) {
            Nanobot n1 = nanobots.get(i);
            for (Nanobot n2 : nanobots) {
                if (intersect(n1, n2)) {
                    intersect.get(i).add(n2);
                }
            }
        }

        Set<Set<Nanobot>> cliquesComputedFor = new HashSet<>();
        HashSet<Nanobot> maxClique = new HashSet<>();
        for (int cliqueIndex = 0; cliqueIndex < intersect.size(); ++cliqueIndex) {
            HashSet<Nanobot> clique = intersect.get(cliqueIndex);
            if (clique.size() <= maxClique.size()) {
                continue;
            }
            if (cliquesComputedFor.contains(clique)) {
                continue;
            }
            if (inSubset(clique, cliquesComputedFor)) {
                continue;
            }
            cliquesComputedFor.add(clique);

            boolean remove = true;
            HashSet<Nanobot> currentClique = new HashSet<>(clique);
            while (remove) {
                int minCommonElems = currentClique.size();
                int toRemoveId = -1;
                for (Nanobot neigh : currentClique) {
                    if (neigh.id == cliqueIndex) {
                        continue;
                    }
                    HashSet<Nanobot> copy = new HashSet<>(clique);
                    copy.retainAll(intersect.get(neigh.id));
                    int commonElemsSize = copy.size();
                    if (commonElemsSize < minCommonElems) {
                        minCommonElems = commonElemsSize;
                        toRemoveId = neigh.id;
                    }
                }
                remove = toRemoveId >= 0;
                currentClique.remove(new Nanobot(toRemoveId, 0, 0, 0, 0));
            }
            if (currentClique.size() > maxClique.size()) {
                maxClique = currentClique;
            }
        }

        return findDistanceRangeWithMostBots(maxClique);
    }

    // checks all the points but way too slow for my input
    private long findIntersectionPointClosestToZero(HashSet<Nanobot> clique) {
        long minx = clique.stream().mapToLong(n -> n.x - n.range).max().getAsLong();
        long maxx = clique.stream().mapToLong(n -> n.x + n.range).min().getAsLong();

        long miny = clique.stream().mapToLong(n -> n.y - n.range).max().getAsLong();
        long maxy = clique.stream().mapToLong(n -> n.y + n.range).min().getAsLong();

        long minz = clique.stream().mapToLong(n -> n.z - n.range).max().getAsLong();
        long maxz = clique.stream().mapToLong(n -> n.z + n.range).min().getAsLong();

        long minDistance = Long.MAX_VALUE;
        for (long x = minx; x <= maxx; ++x) {
            for (long y = miny; y <= maxy; ++y) {
                for (long z = minz; z <= maxz; ++z) {
                    if (insideAll(x, y, z, clique)) {
                        long curDistance = Math.abs(x) + Math.abs(y) + Math.abs(z);
                        if (curDistance < minDistance) {
                            minDistance = curDistance;
                        }
                    }
                }
            }
        }

        return minDistance;
    }

    // should not work in general but works most of the time
    // https://www.reddit.com/r/adventofcode/comments/a8s17l/2018_day_23_solutions/
    private long findDistanceRangeWithMostBots(Set<Nanobot> clique) {
        TreeMap<Long, Long> ranges = new TreeMap<>();
        for (Nanobot n : clique) {
            long distFromZero = Math.abs(n.x) + Math.abs(n.y) + Math.abs(n.z);
            ranges.put(Math.max(0, distFromZero - (int) n.range), 1L);
            ranges.put(distFromZero + (int) n.range, -1L);
        }
        int count = 0;
        long result = 0;
        int maxCount = 0;
        for (Map.Entry<Long, Long> each : ranges.entrySet()) {
            count += each.getValue();
            if (count > maxCount) {
                result = each.getKey();
                maxCount = count;
            }
        }
        return result;
    }

    private boolean insideAll(long x, long y, long z, HashSet<Nanobot> nanobots) {
        for (Nanobot nanobot : nanobots) {
            if (Math.abs(nanobot.x - x) + Math.abs(nanobot.y - y) + Math.abs(nanobot.z - z) > nanobot.range) {
                return false;
            }
        }
        return true;
    }

    private boolean inSubset(Set<Nanobot> current, Set<Set<Nanobot>> computed) {
        for (Set<Nanobot> comp : computed) {
            if (comp.containsAll(current)) {
                return true;
            }
        }
        return false;
    }

    private boolean intersect(Nanobot n1, Nanobot n2) {
        return Math.abs(n1.x - n2.x) + Math.abs(n1.y - n2.y) + Math.abs(n1.z - n2.z) <= n1.range + n2.range;
    }

    private static String nanobotRegex = "^pos=<(\\-?\\d*),(\\-?\\d*),(\\-?\\d*)>, r=(\\d*)$";
    private Pattern nanobotPattern = Pattern.compile(nanobotRegex);

    public Nanobot parseNanobot(String line, int lineNum) {
        Matcher matcher = nanobotPattern.matcher(line);
        if (matcher.find()) {
            return new Nanobot(
                    lineNum,
                    Long.parseLong(matcher.group(1)),
                    Long.parseLong(matcher.group(2)),
                    Long.parseLong(matcher.group(3)),
                    Long.parseLong(matcher.group(4))
            );
        }
        return null;
    }

    public static class Nanobot {
        int id;
        long x, y, z;
        long range;

        Nanobot(int id, long x, long y, long z, long range) {
            this.id = id;
            this.x = x;
            this.y = y;
            this.z = z;
            this.range = range;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            return this.id == ((Nanobot) o).id;
        }

        @Override
        public int hashCode() {
            return id;
        }
    }
}
