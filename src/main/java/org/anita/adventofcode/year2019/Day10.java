package org.anita.adventofcode.year2019;

import org.anita.adventofcode.structures.Position2D;

import java.util.*;

public class Day10 {

    private Set<Position2D> asteroids = new HashSet<>();

    public int task1(List<String> map) {
        readMap(map);
        int maxVisible = -1;
        for (Position2D asteroid : asteroids) {
            int visible = 0;
            for (Position2D other : asteroids) {
                if (!other.equals(asteroid) && seeEachOther(asteroid, other)) {
                    visible++;
                }
            }
            
            if (visible > maxVisible) {
                maxVisible = visible;
            }
        }
        return maxVisible;
    }

    public int task2(List<String> map) {
        readMap(map);
        int maxVisible = -1;
        Position2D bestPosition = asteroids.iterator().next();
        for (Position2D asteroid : asteroids) {
            int visible = 0;
            for (Position2D other : asteroids) {
                if (!other.equals(asteroid) && seeEachOther(asteroid, other)) {
                    visible++;
                }
            }

            if (visible > maxVisible) {
                maxVisible = visible;
                bestPosition = asteroid;
            }
        }

        TreeMap<Double, Position2D> angleDist = new TreeMap<>();
        for (Position2D asteroid : asteroids) {
            if (!asteroid.equals(bestPosition) && seeEachOther(asteroid, bestPosition)) {
                angleDist.put(angleDist(bestPosition, asteroid), asteroid);
            }
        }

        int elem = 1;
        Iterator<Double> iterator = angleDist.keySet().iterator();
        while (elem < 198) {
            iterator.next();
            ++elem;
        }

        Position2D element200 = angleDist.get(iterator.next());
        return element200.x * 100 + element200.y;
    }

    private Double angleDist(Position2D pos1, Position2D pos2) {
        double atan2 = Math.atan2(pos1.y - pos2.y, pos2.x - pos1.x);
        if (atan2 >= 0 && atan2 < Math.PI / 2) {
            return Math.PI / 2 - atan2;
        } else if (atan2 > 0) {
            return 1.5 * Math.PI + Math.PI - atan2;
        } else if (atan2 <= 0 && atan2 > - Math.PI / 2) {
            return Math.PI / 2 - atan2;
        } else {
            return Math.PI + Math.PI + atan2;
        }
    }

    private void readMap(List<String> map) {
        int maxX = map.get(0).length();
        int maxY = map.size();
        for (int y = 0; y < maxY; ++y) {
            for (int x = 0; x < maxX; ++x) {
                if (map.get(y).charAt(x) == '#') {
                    asteroids.add(new Position2D(x, y));
                }
            }
        }
    }

    private boolean seeEachOther(Position2D pos1, Position2D pos2) {
        double angle = ((double)(pos2.y - pos1.y)) / (pos2.x - pos1.x);
        int increment = pos1.x < pos2.x ? 1 : -1;
        for (int x = pos1.x + increment; increment == 1 ? x < pos2.x : x > pos2.x; x += increment) {
            double y = pos1.y + angle * (x - pos1.x);
            if (y - ((int)y) < 0.000001) {
                if (asteroids.contains(new Position2D(x, (int)y))) {
                    return false;
                }
            }
        }
        angle = ((double)(pos2.x - pos1.x)) / (pos2.y - pos1.y);
        increment = pos1.y < pos2.y ? 1 : -1;
        for (int y = pos1.y + increment; increment == 1 ? y < pos2.y : y > pos2.y; y += increment) {
            double x = pos1.x + angle * (y - pos1.y);
            if (x - ((int)x) < 0.000001) {
                if (asteroids.contains(new Position2D((int)x, y))) {
                    return false;
                }
            }
        }
        return true;
    }
}
