package org.anita.adventofcode.year2018;

import org.anita.adventofcode.structures.Position2D;

import java.util.*;

public class Day15 {

    TreeMap<Position2D, Unit> units = new TreeMap<>();
    char[][] map;

    public Day15(List<String> lines) {
        parseMap(lines, 3);
        //printMap();
    }

    public Day15(List<String> lines, int elfAttackPower) {
        parseMap(lines, elfAttackPower);
        //printMap();
    }

    private void parseMap(List<String> lines, int elfAttackPower) {
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
                    units.put(new Position2D(x, y), new Unit('E' == line.charAt(x) ? elfAttackPower : 3, 200, line.charAt(x), new Position2D(x, y)));
                    map[x][y] = '.';
                } else {
                    map[x][y] = line.charAt(x);
                }
            }
            y += 1;
        }
    }

    public TreeMap<Position2D, Unit> getUnits() {
        return units;
    }

    public long battle() {
        int round = 1;
        while (true) {
            ArrayList<Unit> currentUnits = new ArrayList<>(this.units.values());
            for (int i = 0; i < currentUnits.size(); ++i) {
                Unit unit = currentUnits.get(i);
                if (!unit.isAlive()) {
                    if (units.get(unit.position) == unit) {
                        units.remove(unit.position);
                    }
                    continue;
                }
                Position2D curPosition = new Position2D(unit.position.x, unit.position.y);
                Unit killed = unit.round();
                if (killed != null) {
                    units.remove(killed.position);
                }
                units.remove(curPosition);
                units.put(new Position2D(unit.position.x, unit.position.y), unit);
                long elfCount = units.values().stream().filter(u -> u.type == 'E').count();
                if (elfCount == units.size() || elfCount == 0) {
                    //System.out.println(round);
                    //printMap();
                    //printUnits();
                    if (i == currentUnits.size() - 1) {
                        return round * totalPoints();
                    } else {
                        return (round - 1) * totalPoints();
                    }
                }
            }

            //System.out.println(round);
            //printMap();
            //printUnits();
            round += 1;
        }
    }

    public long totalPoints() {
        return units.values().stream().mapToInt(u -> u.hitPoints).sum();
    }

    public void printMap() {
        for (int y = 0; y < map[0].length; ++y) {
            for (int x = 0; x < map.length; ++x) {
                if (units.containsKey(new Position2D(x, y))) {
                    System.out.print(units.get(new Position2D(x, y)).type);
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

    private boolean isTaken(Position2D pos) {
        if (units.containsKey(pos) && units.get(pos).isAlive()) {
            return true;
        }
        return false;
    }

    private List<Position2D> generateNeighbours(Position2D cur) {
        return Arrays.asList(
                cur.up(),
                cur.left(),
                cur.right(),
                cur.down()
        );
    }

    private boolean isValidPosition(Position2D pos) {
        if (pos.x < 0 || pos.x >= map.length || pos.y < 0 || pos.y >= map[0].length) {
            return false;
        }
        if (map[pos.x][pos.y] == '#') {
            return false;
        }
        return true;
    }

    public class Unit {
        final int attackPower;
        int hitPoints;
        final char type;
        Position2D position;

        public Unit(int attackPower, int hitPoints, char type, Position2D position) {
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
                move();
            }
            // attack
            return attack();
        }

        public boolean isAlive() {
            return hitPoints > 0;
        }

        public boolean isAttacking() {
            for (Position2D neigh : generateNeighbours(this.position)) {
                if (units.containsKey(neigh) && units.get(neigh).type != this.type && units.get(neigh).isAlive()) {
                    return true;
                }
            }
            return false;
        }

        private void move() {
            LinkedList<Position2D> queue = new LinkedList<>();
            Set<Position2D> visited = new HashSet<>();
            queue.add(position);
            int minLen[][] = new int[map.length][map[0].length];
            Position2D prev[][] = new Position2D[map.length][map[0].length];
            for (int x = 0; x < map.length; ++ x) {
                for (int y = 0; y < map[0].length; ++y) {
                    minLen[x][y] = Integer.MAX_VALUE;
                }
            }
            minLen[position.x][position.y] = 0;
            while (!queue.isEmpty()) {
                Position2D cur = queue.poll();
                List<Position2D> neighs = generateNeighbours(cur);
                visited.add(cur);

                for (Position2D neigh : neighs) {
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
                        prev[neigh.x][neigh.y] = cur;
                        if (units.containsKey(neigh) && units.get(neigh).type != this.type && units.get(neigh).isAlive()) {
                            Position2D prevInPath = cur;
                            while (prevInPath.manhattanDistance(this.position) > 1){
                                prevInPath = prev[prevInPath.x][prevInPath.y];
                            }
                            this.position = new Position2D(prevInPath.x, prevInPath.y);
                            return;
                        }
                    }
                }

                for (Position2D neigh : neighs) {
                    if (!queue.contains(neigh) && !visited.contains(neigh) && isValidPosition(neigh) && !isTaken(neigh)) {
                        queue.add(neigh);
                    }
                }
            }
        }

        private Unit attack() {
            int minHitPoints = Integer.MAX_VALUE;
            Unit unitToAttack = null;
            for (Position2D neigh : generateNeighbours(this.position)) {
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
