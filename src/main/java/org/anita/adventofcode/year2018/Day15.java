package org.anita.adventofcode.year2018;

import org.anita.adventofcode.structures.Position;

import java.util.*;

public class Day15 {

    TreeMap<Position, Unit> units = new TreeMap<>();
    char[][] map;

    public Day15(List<String> lines) {
        parseMap(lines);
        printMap();
    }

    private void parseMap(List<String> lines) {
        int maxy = lines.size() - 1;
        int maxx = 0;
        for (String line : lines) {
            maxx = Math.max(maxx, line.length() - 1);
        }
        map = new char[maxx + 1][maxy + 1];
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

    public int task1() {
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
                if (units.values().stream().filter(u -> u.type == unit.type).count() == units.size()) {
                    System.out.println(round);
                    printMap();
                    //printUnits();
                    if (i == currentUnits.size() - 1) {
                        return round * totalPoints();
                    } else {
                        return (round - 1) * totalPoints();
                    }
                }
            }

            System.out.println(round);
            printMap();
            //printUnits();
            round += 1;
        }
    }

    public int totalPoints() {
        int sum = 0;
        for (Unit unit : units.values()) {
            if (unit.isAlive()) {
                sum += unit.hitPoints;
            }
        }
        return sum;
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
            // move
            move();
            // attack
            return attack();
        }

        public boolean isAlive() {
            return hitPoints > 0;
        }

        private void move() {
            LinkedList<Position> queue = new LinkedList<>();
            Set<Position> visited = new HashSet<>();
            queue.add(position);
            int minLen[][] = new int[map.length][map[0].length];
            Position prev[][] = new Position[map.length][map[0].length];
            for (int x = 0; x < map.length; ++ x) {
                for (int y = 0; y < map[0].length; ++y) {
                    minLen[x][y] = Integer.MAX_VALUE - 1;
                }
            }
            minLen[position.x][position.y] = 0;
            while (!queue.isEmpty()) {
                Position cur = queue.poll();
                List<Position> neighs = generateNeighbours(cur);
                visited.add(cur);

                for (Position neigh : neighs) {
                    if (!isValidPosition(neigh)) {
                        continue;
                    }
                    if (isTaken(neigh)) {
                        if (units.get(neigh).type != this.type) {

                        } else {
                            continue;
                        }
                    }

                    int newMinLen = minLen[cur.x][cur.y] + 1;
                    if (newMinLen < minLen[neigh.x][neigh.y]) {
                        minLen[neigh.x][neigh.y] = newMinLen;
                        prev[neigh.x][neigh.y] = cur;
                        if (units.containsKey(neigh) && units.get(neigh).type != this.type && units.get(neigh).isAlive()) {
                            Position prevInPath = cur;
                            while (prevInPath.manhattanDistance(this.position) > 1){
                                prevInPath = prev[prevInPath.x][prevInPath.y];
                            }
                            this.position = new Position(prevInPath.x, prevInPath.y);
                            return;
                        }
                    }
                }

                for (Position neigh : neighs) {
                    if (!queue.contains(neigh) && !visited.contains(neigh) && isValidPosition(neigh) && !isTaken(neigh)) {
                        queue.add(neigh);
                    }
                }
            }

        }

//        private List<Position> dijkstra(Position from, Position to) {
//            LinkedList<Position> queue = new LinkedList<>();
//            Set<Position> visited = new HashSet<>();
//            queue.add(from);
//            int minLen[][] = new int[map.length][map[0].length];
//            for (int x = 0; x < map.length; ++ x) {
//                for (int y = 0; y < map[0].length; ++y) {
//                    minLen[x][y] = Integer.MAX_VALUE - 1;
//                }
//            }
//            minLen[from.x][from.y] = 0;
//            while (!queue.isEmpty()) {
//                Position cur = queue.poll();
//                List<Position> neighs = generateNeighbours(cur);
//                visited.add(cur);
//                for (Position neigh : neighs) {
//                    if (isTaken(neigh) && units.get(neigh).type == this.type) {
//                        continue;
//                    }
//                    if (!isValidPosition(neigh)) {
//                        continue;
//                    }
//                    int newMinLen = minLen[cur.x][cur.y] + 1;
//                    if (newMinLen < minLen[neigh.x][neigh.y]) {
//                        minLen[neigh.x][neigh.y] = newMinLen;
//                        if (units.containsKey(neigh) && units.get(neigh).type != this.type && units.get(neigh).isAlive()) {
//
//
//
//
//                            return;
//                        }
//                    }
//                }
//
//                for (Position neigh : neighs) {
//                    if (!queue.contains(neigh) && !visited.contains(neigh) && isValidPosition(neigh) && !isTaken(neigh)) {
//                        queue.add(neigh);
//                    }
//                }
//            }
//        }

        private boolean isValidPosition(Position pos) {
            if (pos.x < 0 || pos.x >= map.length || pos.y < 0 || pos.y >= map[0].length) {
                return false;
            }
            if (map[pos.x][pos.y] == '#') {
                return false;
            }
            return true;
        }

        public boolean isTaken(Position pos) {
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

        private Unit attack() {
            int minHitPoints = Integer.MAX_VALUE;
            Unit unitToAttack = null;
            if (units.containsKey(position.up())) {
                Unit other = units.get(position.up());
                if (other.type != this.type && other.isAlive() && other.hitPoints < minHitPoints) {
                    unitToAttack = other;
                    minHitPoints = unitToAttack.hitPoints;
                }
            }
            if (units.containsKey(position.left())) {
                Unit other = units.get(position.left());
                if (other.type != this.type && other.isAlive() && other.hitPoints < minHitPoints) {
                    unitToAttack = other;
                    minHitPoints = unitToAttack.hitPoints;
                }
            }
            if (units.containsKey(position.right())) {
                Unit other = units.get(position.right());
                if (other.type != this.type && other.isAlive() && other.hitPoints < minHitPoints) {
                    unitToAttack = other;
                    minHitPoints = unitToAttack.hitPoints;
                }
            }
            if (units.containsKey(position.down())) {
                Unit other = units.get(position.down());
                if (other.type != this.type && other.isAlive() && other.hitPoints < minHitPoints) {
                    unitToAttack = other;
                    minHitPoints = unitToAttack.hitPoints;
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
            return "Unit{" +
                    "attackPower=" + attackPower +
                    ", hitPoints=" + hitPoints +
                    ", type=" + type +
                    ", position=" + position +
                    '}';
        }
    }

}
