package org.anita.adventofcode.year2018;

import org.anita.adventofcode.structures.Position;

import java.util.*;
import java.util.stream.Collectors;

public class Day15 {

    TreeMap<Position, Unit> units = new TreeMap<>();
    char[][] map;

    public Day15(List<String> lines) {
        parseMap(lines);
        printMap();
    }

    private void parseMap(List<String> lines) {
        int maxy = lines.size();
        int maxx = 0;
        for (String line : lines) {
            maxx = Math.max(maxx, line.length());
        }
        map = new char[maxx][maxy];
        int y = 0;
        for (String line : lines) {
            for (int x = 0; x < line.length(); ++x) {
                if (line.charAt(x) == 'E' || line.charAt(x) == 'G') {
                    units.put(new Position(x, y), new Unit(3, 200, line.charAt(x), new Position(x, y)));
                    map[x][y] = '.';
                } else {
                    map[x][y] = line.charAt(x);
                }
            }
            y += 1;
        }
    }

    public long task1() {
        int round = 1;
        while (true) {
            ArrayList<Unit> currentUnits = new ArrayList<>(this.units.values());
            for (int i = 0; i < currentUnits.size(); ++i) {
                Unit unit = currentUnits.get(i);
                if (!unit.isAlive()) {
                    units.remove(unit.position);
                    continue;
                }
                Position curPosition = new Position(unit.position.x, unit.position.y);
                Unit killed = unit.round();
                if (killed != null) {
                    units.remove(killed.position);
                }
                units.remove(curPosition);
                units.put(new Position(unit.position.x, unit.position.y), unit);
                long elfCount = units.values().stream().filter(u -> u.type == 'E').count();
                if (elfCount == units.size() || elfCount == 0) {
                    System.out.println(round);
                    printMap();
                    printUnits();
                    if (i == currentUnits.size() - 1) {
                        return round * totalPoints();
                    } else {
                        return (round - 1) * totalPoints();
                    }
                }
            }

            System.out.println(round);
            printMap();
            printUnits();
            round += 1;
        }
    }

    public long totalPoints() {
        return units.values().stream().mapToInt(u -> u.hitPoints).sum();
    }

    public void printMap() {
        for (int y = 0; y < map[0].length; ++y) {
            for (int x = 0; x < map.length; ++x) {
                if (units.containsKey(new Position(x, y))) {
                    System.out.print(units.get(new Position(x, y)).type);
                } else {
                    System.out.print(map[x][y]);
                }
            }
            System.out.println();
        }
    }

    public void printUnits() {
        for (Unit unit : units.values()) {
            System.out.println(unit);
        }
        System.out.println("-----");
    }

    private int dijkstra(Position from, Position to) {
        if (from.equals(to)) {
            return 0;
        }
        LinkedList<Position> queue = new LinkedList<>();
        Set<Position> visited = new HashSet<>();
        queue.add(from);
        int minLen[][] = new int[map.length][map[0].length];
        for (int x = 0; x < map.length; ++x) {
            for (int y = 0; y < map[0].length; ++y) {
                minLen[x][y] = Integer.MAX_VALUE;
            }
        }
        minLen[from.x][from.y] = 0;
        while (!queue.isEmpty()) {
            Position cur = queue.poll();
            List<Position> neighs = generateNeighbours(cur);
            visited.add(cur);
            for (Position neigh : neighs) {
                if (!isValidPosition(neigh)) {
                    continue;
                }
                if (isTaken(neigh) && !neigh.equals(to)) {
                    continue;
                }
                int newMinLen = minLen[cur.x][cur.y] + 1;
                if (newMinLen < minLen[neigh.x][neigh.y]) {
                    minLen[neigh.x][neigh.y] = newMinLen;
                    if (to.equals(neigh)) {
                        return newMinLen;
                    }
                }
            }

            for (Position neigh : neighs) {
                if (!queue.contains(neigh) && !visited.contains(neigh) && isValidPosition(neigh) && !isTaken(neigh)) {
                    queue.add(neigh);
                }
            }
        }
        return Integer.MAX_VALUE;
    }

    private boolean isTaken(Position pos) {
        if (units.containsKey(pos) && units.get(pos).isAlive()) {
            return true;
        }
        return false;
    }

    private List<Position> generateNeighbours(Position cur) {
        return Arrays.asList(
                cur.up(),
                cur.left(),
                cur.right(),
                cur.down()
        );
    }

    private boolean isValidPosition(Position pos) {
        if (pos.x < 0 || pos.x >= map.length || pos.y < 0 || pos.y >= map[0].length) {
            return false;
        }
        if (map[pos.x][pos.y] == '#') {
            return false;
        }
        return true;
    }

    public class Unit {
        int attackPower;
        int hitPoints;
        char type;
        Position position;

        public Unit(int attackPower, int hitPoints, char type, Position position) {
            this.attackPower = attackPower;
            this.hitPoints = hitPoints;
            this.type = type;
            this.position = position;
        }

        public Unit round() {
            // you are dead
            if (!isAlive()) {
                return null;
            }
            if (!isAttacking()) {
                // move
                move2();
            }
            // attack
            return attack();
        }

        public boolean isAlive() {
            return hitPoints > 0;
        }

        public boolean isAttacking() {
            for (Position neigh : generateNeighbours(this.position)) {
                if (units.containsKey(neigh) && units.get(neigh).type != this.type && units.get(neigh).isAlive()) {
                    return true;
                }
            }
            return false;
        }

        private void move2() {
            int minPathLen = Integer.MAX_VALUE;
            List<Position> inSameDistance = new ArrayList<>();
            List<Unit> enemies = units.values().stream().filter(u -> u.type != this.type && u.isAlive()).collect(Collectors.toList());
            List<Position> enemiesNeighbours = new ArrayList<>();
            for (Unit enemy : enemies) {
                List<Position> neighs = generateNeighbours(enemy.position);
                for (Position neigh : neighs) {
                    if (isValidPosition(neigh) && !isTaken(neigh)) {
                        enemiesNeighbours.add(neigh);
                    }
                }
            }
            for (Position targetPos : enemiesNeighbours) {
                int curMinLen = dijkstra(this.position, targetPos);
                if (curMinLen != Integer.MAX_VALUE && curMinLen < minPathLen) {
                    minPathLen = curMinLen;
                    inSameDistance.clear();
                    inSameDistance.add(targetPos);
                } else if (curMinLen != Integer.MAX_VALUE && curMinLen == minPathLen) {
                    inSameDistance.add(targetPos);
                }
            }
            if (inSameDistance.isEmpty()) {
                return;
            }
            inSameDistance.sort(Comparator.naturalOrder());
            Position thePos = inSameDistance.get(0);
            List<Position> nextMoves = generateNeighbours(this.position);
            for (Position move : nextMoves) {
                if (!isValidPosition(move)) {
                    continue;
                }
                if (isTaken(move)) {
                    continue;
                }
                if (dijkstra(move, thePos) == minPathLen - 1) {
                    this.position = move;
                    return;
                }
            }
            return;
        }

        private void move() {
            LinkedList<Position> queue = new LinkedList<>();
            Set<Position> visited = new HashSet<>();
            queue.add(position);
            int minLen[][] = new int[map.length][map[0].length];
            for (int x = 0; x < map.length; ++x) {
                for (int y = 0; y < map[0].length; ++y) {
                    minLen[x][y] = Integer.MAX_VALUE;
                }
            }
            minLen[position.x][position.y] = 0;
            int globalMinLen = Integer.MAX_VALUE;
            List<Position> targets = new ArrayList<>();
            boolean allInDistanceFound = false;
            while (!queue.isEmpty()) {
                Position cur = queue.poll();
                visited.add(cur);
                List<Position> neighs = generateNeighbours(cur);
                for (Position neigh : neighs) {
                    if (!isValidPosition(neigh)) {
                        continue;
                    }
                    if (isTaken(neigh)) {
                        if (units.get(neigh).type != this.type) {
                            // found
                        } else {
                            continue;
                        }
                    }
                    int newMinLen = minLen[cur.x][cur.y] + 1;
                    if (newMinLen < minLen[neigh.x][neigh.y]) {
                        minLen[neigh.x][neigh.y] = newMinLen;
                        if (units.containsKey(neigh) && units.get(neigh).type != this.type && units.get(neigh).isAlive()) {
                            // we have found a target
                            if (newMinLen < globalMinLen) {
                                globalMinLen = newMinLen;
                            }
                            if (newMinLen > globalMinLen) {
                                // distances are already bigger than global minimal distance
                                allInDistanceFound = true;
                                break;
                            }
                            targets.add(neigh);
                        }
                    }
                }

                for (Position neigh : neighs) {
                    if (!queue.contains(neigh) && !visited.contains(neigh) && isValidPosition(neigh) && !isTaken(neigh)) {
                        queue.add(neigh);
                    }
                }

                if (globalMinLen != Integer.MAX_VALUE && (allInDistanceFound || queue.isEmpty())) {
                    targets.sort(Position::compareTo);
                    Position target = targets.get(0);
                    List<Position> nextMoves = generateNeighbours(this.position);
                    for (Position move : nextMoves) {
                        if (!isValidPosition(move)) {
                            continue;
                        }
                        if (isTaken(move)) {
                            continue;
                        }
                        if (dijkstra(move, target) == globalMinLen - 1) {
                            this.position = move;
                            return;
                        }
                    }
                }
            }
        }

        private Unit attack() {
            int minHitPoints = Integer.MAX_VALUE;
            Unit unitToAttack = null;
            for (Position neigh : generateNeighbours(this.position)) {
                if (units.containsKey(neigh)) {
                    Unit other = units.get(neigh);
                    if (other.type != this.type && other.isAlive() && other.hitPoints < minHitPoints) {
                        unitToAttack = other;
                        minHitPoints = other.hitPoints;
                    }
                }
            }
            if (unitToAttack != null) {
                unitToAttack.hitPoints -= attackPower;
                if (!unitToAttack.isAlive()) {
                    return unitToAttack;
                }
            }
            return null;
        }

        @Override
        public String toString() {
            return "Group{" +
                    "attackPower=" + attackPower +
                    ", hitPoints=" + hitPoints +
                    ", type=" + type +
                    ", position=" + position +
                    '}';
        }
    }

}
