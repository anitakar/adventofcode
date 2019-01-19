package org.anita.adventofcode.year2018;

import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Day23 {

    public long task1(List<Nanobot> nanobots) {
        Nanobot max = nanobots.stream().max(Comparator.comparingInt(n -> n.range)).get();
        return nanobots.stream().filter(n ->
                Math.abs(max.x - n.x) + Math.abs(max.y - n.y) + Math.abs(max.z - n.z) <= max.range
        ).count();
    }

    public long task2(List<Nanobot> nanobots) {
        List<List<Nanobot>> intersect = new ArrayList<>();
        for (int i = 0; i < nanobots.size(); ++i) {
            intersect.add(new ArrayList<>());
        }
        for (int i = 0; i < nanobots.size(); ++i) {
            Nanobot n1 = nanobots.get(i);
            intersect.get(i).add(n1);
            for (Nanobot n2 : nanobots) {
                if (n1 != n2) {
                    if (intersect(n1, n2)) {
                        intersect.get(i).add(n2);
                    }
                }
            }
        }

        Set<Set<Nanobot>> cliquesComputedFor = new HashSet<>();

        int maxCliqueSize = 0;
        int maxCliqueIndex = 0;
        int maxNeighIndex = 0;
        for (int cliqueIndex = 0; cliqueIndex < intersect.size(); ++cliqueIndex) {
            List<Nanobot> clique = intersect.get(cliqueIndex);
            if (clique.size() <= maxCliqueSize) {
                continue;
            }
            Set<Nanobot> currentSet = new HashSet<>(clique);
            if (cliquesComputedFor.contains(currentSet)) {
                continue;
            }
            if (inSubset(currentSet, cliquesComputedFor)) {
                continue;
            }
            cliquesComputedFor.add(currentSet);
            int maxSize = 0;
            int neighIndex;
            for (neighIndex = 1; neighIndex < clique.size(); ++neighIndex) {
                if (clique.size() - neighIndex - 1 <= maxCliqueSize) {
                    break;
                }
                Nanobot neigh = clique.get(neighIndex);
                int size = 0;
                for (int neighNeighIndex = neighIndex + 1; neighNeighIndex < clique.size(); ++neighNeighIndex) {
                    Nanobot neighNeigh = clique.get(neighNeighIndex);
                    if (intersect(neigh, neighNeigh)) {
                        size += 1;
                    }
                }
                if (size > maxSize) {
                    maxSize = size;
                    maxNeighIndex = neighIndex;
                }
            }
            if (maxSize > maxCliqueSize) {
                maxCliqueSize = maxSize;
                maxCliqueIndex = cliqueIndex;
            }
        }
        List<Nanobot> maxCliqueNanobots = intersect.get(maxCliqueIndex);
        List<Nanobot> clique = new ArrayList<>();
        Nanobot maxNeighNanobot = maxCliqueNanobots.get(maxNeighIndex);
        for (Nanobot neigh : maxCliqueNanobots) {
            if (intersect(neigh, maxNeighNanobot)) {
                clique.add(neigh);
            }
        }
        return findIntersectionPointClosestToZero(clique);
    }

    private int findIntersectionPointClosestToZero(List<Nanobot> clique) {
        int minx = clique.stream().mapToInt(n -> n.x - n.range).max().getAsInt();
        int maxx = clique.stream().mapToInt(n -> n.x + n.range).min().getAsInt();

        int miny = clique.stream().mapToInt(n -> n.y - n.range).max().getAsInt();
        int maxy = clique.stream().mapToInt(n -> n.y + n.range).min().getAsInt();

        int minz = clique.stream().mapToInt(n -> n.z - n.range).max().getAsInt();
        int maxz = clique.stream().mapToInt(n -> n.z + n.range).min().getAsInt();

        int xDistance = 0;
        if (minx < 0 && maxx < 0) {
            xDistance = -maxx;
        }
        if (minx > 0 && maxx > 0) {
            xDistance = minx;
        }
        int yDistance = 0;
        if (miny < 0 && maxy < 0) {
            yDistance = -maxy;
        }
        if (miny > 0 && maxy > 0) {
            yDistance = miny;
        }
        int zDistance = 0;
        if (minz < 0 && maxz < 0) {
            zDistance = -maxz;
        }
        if (minz > 0 && maxz > 0) {
            zDistance = minz;
        }

        return xDistance + yDistance + zDistance;
    }

    private long findMinManhattanDistanceFromZeroToIntersection(List<Nanobot> clique) {
        int middleX = 0, middleY = 0;
        double middleZ = 0;
        for (Nanobot nanobot : clique) {
            middleX += nanobot.x;
            middleY += nanobot.y;
        }
        middleX /= clique.size();
        middleY /= clique.size();
        int incrementX = middleX > 0 ? -1 : 1;
        int incrementY = middleY > 0 ? -1 : 1;
        while (true) {
            int newX = middleX + incrementX;
            int newY = middleY + incrementY;
            double newZ = (1.0 * middleZ/middleX * newX + 1.0 * middleZ/middleY * newY) / 2;
            boolean insideAll = true;
            for (Nanobot n : clique) {
                if (Math.abs(n.x - newX) > n.range || Math.abs(n.y - newY) > n.range || Math.abs(n.z - newZ) > n.range) {
                    insideAll = false;
                }
            }
            if (!insideAll) {
                break;
            }
            middleX = newX;
            middleY = newY;
            middleZ = newZ;
        }

        return (long) (Math.abs(middleX) + Math.abs(middleY) + Math.abs(middleZ));
    }

    private boolean inSubset(Set<Nanobot> current, Set<Set<Nanobot>> computed) {
        for (Set<Nanobot> comp : computed) {
            if (comp.containsAll(current)) {
                return true;
            }
        }
        return false;
    }

    public boolean intersect(Nanobot n1, Nanobot n2) {
        if (n1.x > n2.x) {
            Nanobot temp = n1;
            n1 = n2;
            n2 = temp;
        }
        boolean xIntersect = n1.x + n1.range >= n2.x - n2.range;
        if (n1.y > n2.y) {
            Nanobot temp = n1;
            n1 = n2;
            n2 = temp;
        }
        boolean yIntersect = n1.y + n1.range >= n2.y - n2.range;
        if (n1.z > n2.z) {
            Nanobot temp = n1;
            n1 = n2;
            n2 = temp;
        }
        boolean zIntersect = n1.z + n1.range >= n2.z - n2.range;

        return xIntersect && yIntersect && zIntersect;
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

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Nanobot nanobot = (Nanobot) o;
            return x == nanobot.x &&
                    y == nanobot.y &&
                    z == nanobot.z &&
                    range == nanobot.range;
        }

        @Override
        public int hashCode() {
            return Objects.hash(x, y, z, range);
        }
    }
}
