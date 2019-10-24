package org.anita.adventofcode.year2018;

import javafx.util.Pair;
import org.anita.adventofcode.structures.Position2D;

import java.util.*;

public class Day22 {

    long[][] map;

    public int task1(int x, int y, int depth) {
        calculateMap(x, y, depth);

        int totalRiskLevel = 0;
        for (int i = 0; i <= x; ++i) {
            for (int j = 0; j <= y; ++j) {
                totalRiskLevel += map[i][j];
            }
        }
        return totalRiskLevel;
    }

    private void calculateMap(int x, int y, int depth) {
        map = new long[x + 1][y + 1];

        map[0][0] = 0;
        map[x][y] = 0;
        for (int i = 0; i <= x; ++i) {
            map[i][0] = i * 16807;
        }
        for (int i = 0; i <= y; ++i) {
            map[0][i] = i * 48271;
        }
        for (int i = 1; i <= x; ++i) {
            for (int j = 1; j <= y; ++j) {
                map[i][j] = erosionLevel(map[i - 1][j], depth) * erosionLevel(map[i][j - 1], depth);
            }
        }
        map[x][y] = 0;

        for (int i = 0; i <= x; ++i) {
            for (int j = 0; j <= y; ++j) {
                map[i][j] = erosionLevel(map[i][j], depth) % 3;
            }
        }
    }

    private long erosionLevel(long index, int depth) {
        return (index + depth) % 20183;
    }

    private static final long ROCKY = 0;
    private static final long WET = 1;
    private static final long NARROW = 2;

    private static final int TORCH = 0;
    private static final int CLIMBING_GEAR = 1;
    private static final int NEITHER = 2;

    private HashMap<CavePosition, CavePosition> visited = new HashMap<>();
    private Position2D targetPosition;
    private int currentMinDistance = Integer.MAX_VALUE;
    HashMap<CavePosition, CavePosition> toVisitElems = new HashMap<>();
    PriorityQueue<CavePosition> toVisit = new PriorityQueue<>(new CavePositionComparator());
    public int task2(int x, int y, int depth) {
        calculateMap(x + 3, y + 3, depth);

        targetPosition = new Position2D(x, y);
        CavePosition start = new CavePosition(new Position2D(0, 0), 0, TORCH);
        toVisit.add(start);
        toVisitElems.put(start, start);

        while (!toVisit.isEmpty()) {
            CavePosition currentPosition = toVisit.poll();
            toVisit.removeIf(e -> e.equals(currentPosition));
            toVisitElems.remove(currentPosition);
            if (visited.containsKey(currentPosition)) {
                CavePosition prev = visited.get(currentPosition);
                if (prev.cost < currentPosition.cost) {
                    continue;
                }
            }

            addNeighbours(currentPosition);
            visited.put(currentPosition, currentPosition);
            if (currentPosition.pos.equals(targetPosition)) {
                int minDistance = currentPosition.cost;
                if (currentPosition.gear != TORCH) {
                    minDistance += 7;
                }
                if (minDistance < currentMinDistance) {
                    currentMinDistance = minDistance;
                }
            }
        }

        return currentMinDistance;
    }

    private void addNeighbours(CavePosition current) {
        add(current.pos.down(), current);
        add(current.pos.right(), current);
        add(current.pos.up(), current);
        add(current.pos.left(), current);
    }

    private void add(Position2D toAdd, CavePosition current) {
        if (withinBounds(toAdd)) {
            List<Pair<Integer, Integer>> costs = cost(toAdd, current.gear);
            for (Pair<Integer, Integer> cost : costs) {
                CavePosition caveToVisit = new CavePosition(toAdd, current.cost + cost.getKey(), cost.getValue());
                if (caveToVisit.cost > currentMinDistance) {
                    continue;
                }
                if (toVisit.contains(caveToVisit)) {
                    CavePosition prev = toVisitElems.get(caveToVisit);
                    if (prev.cost < caveToVisit.cost) {
                        continue;
                    } else if (!betterThanVisited(caveToVisit)) {
                        continue;
                    } else {
                        toVisit.removeIf(e -> e.equals(caveToVisit));
                    }
                }
                if (visited.containsKey(caveToVisit)) {
                    CavePosition prev = this.visited.get(caveToVisit);
                    if (caveToVisit.cost < prev.cost) {
                        this.toVisit.add(caveToVisit);
                        this.toVisitElems.put(caveToVisit, caveToVisit);
                        this.visited.remove(prev);
                        this.visited.put(caveToVisit, caveToVisit);
                    }
                } else {
                    this.toVisit.add(caveToVisit);
                    this.toVisitElems.put(caveToVisit, caveToVisit);
                }
            }
        }
    }

    private boolean betterThanVisited(CavePosition caveToVisit) {
        if (!visited.containsKey(caveToVisit)) {
            return true;
        }

        CavePosition prev = this.visited.get(caveToVisit);
        return caveToVisit.cost < prev.cost;
    }

    private boolean withinBounds(Position2D current) {
        return current.x >= 0 && current.y >= 0 && current.x < map.length && current.y < map[0].length;
    }

    private List<Pair<Integer, Integer>> cost(Position2D target, int currentEquipment) {
        long targetRegionType = map[target.x][target.y];
        if (targetRegionType == ROCKY) {
            if (currentEquipment == CLIMBING_GEAR || currentEquipment == TORCH) {
                return Collections.singletonList(new Pair<>(1, currentEquipment));
            } else {
                return Arrays.asList(new Pair<>(8, CLIMBING_GEAR), new Pair<>(8, TORCH));
            }
        } else if (targetRegionType == WET) {
            if (currentEquipment == CLIMBING_GEAR || currentEquipment == NEITHER) {
                return Collections.singletonList(new Pair<>(1, currentEquipment));
            } else {
                return Arrays.asList(new Pair<>(8, CLIMBING_GEAR), new Pair<>(8, NEITHER));
            }
        } else {
            if (currentEquipment == TORCH || currentEquipment == NEITHER) {
                return Collections.singletonList(new Pair<>(1, currentEquipment));
            } else {
                return Arrays.asList(new Pair<>(8, TORCH), new Pair<>(8, NEITHER));
            }
        }
    }

    private static class CavePosition {
        final Position2D pos;
        final int cost;
        final int gear;

        CavePosition(Position2D pos, int cost, int gear) {
            this.pos = pos;
            this.cost = cost;
            this.gear = gear;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            CavePosition that = (CavePosition) o;
            return gear == that.gear &&
                    pos.equals(that.pos);
        }

        @Override
        public int hashCode() {
            return Objects.hash(pos, gear);
        }

        @Override
        public String toString() {
            return "CavePosition{" +
                    "pos=" + pos +
                    ", cost=" + cost +
                    ", gear=" + gear +
                    '}';
        }
    }

    private class CavePositionComparator implements Comparator<CavePosition> {

        @Override
        public int compare(CavePosition o1, CavePosition o2) {
            if (o1.pos.equals(o2.pos)) {
                if (o1.cost != o2.cost) {
                    return o1.cost - o2.cost;
                } else {
                    return o1.gear - o2.gear;
                }
            }

            int curDist = o1.pos.manhattanDistance(targetPosition);
            int otherDist = o2.pos.manhattanDistance(targetPosition);

            if (curDist != otherDist) {
                return curDist - otherDist;
            }

            if (o1.cost != o2.cost) {
                return o1.cost - o2.cost;
            }

            return o1.gear - o2.gear;
        }
    }

}
