package org.anita.adventofcode.year2018;

import org.anita.adventofcode.structures.Position;

import java.util.*;

public class Day20 {

    int minx = 0, miny = 0, maxx = 0, maxy = 0;
    char[][] map;
    int[][] shortestPaths;

    public Day20() {

    }

    public void readMap(String regex) {
        findBox(new Position(0, 0), regex);
        prepareEmptyMap();
        Position position = new Position(0, 0);
        fillMap(position, regex);
        printMap();
    }

    public void findAllShortestPaths() {
        shortestPaths = new int[map.length][map[0].length];
        for (int x = 1; x <= maxx - minx; x += 2) {
            for (int y = 1; y <= maxy - miny; y += 2) {
                shortestPaths[x][y] = Integer.MAX_VALUE;
            }
        }
        shortestPaths[0 - minx][0 - miny] = 0;

        LinkedList<Position> queue = new LinkedList<>();
        queue.add(new Position(0 - minx, 0 - miny));
        Set<Position> visited = new HashSet<>();
        while (!queue.isEmpty()) {
            //printShortestPaths();
            Position current = queue.pop();
            visited.add(current);
            int currentMinLen = shortestPaths[current.x][current.y];

            // down
            if (current.y + 2 < map[0].length) {
                Position door = current.down();
                if (map[door.x][door.y] != '#') {
                    Position next = door.down();
                    if (currentMinLen + 1 < shortestPaths[next.x][next.y]) {
                        shortestPaths[next.x][next.y] = currentMinLen + 1;
                    }
                    if (!visited.contains(next)) {
                        queue.add(next);
                    }
                }
            }

            // up
            if (current.y - 2 > 0) {
                Position door = current.up();
                if (map[door.x][door.y] != '#') {
                    Position next = door.up();
                    if (currentMinLen + 1 < shortestPaths[next.x][next.y]) {
                        shortestPaths[next.x][next.y] = currentMinLen + 1;
                    }
                    if (!visited.contains(next)) {
                        queue.add(next);
                    }
                }
            }

            // right
            if (current.x + 2 < map.length) {
                Position door = current.right();
                if (map[door.x][door.y] != '#') {
                    Position next = door.right();
                    if (currentMinLen + 1 < shortestPaths[next.x][next.y]) {
                        shortestPaths[next.x][next.y] = currentMinLen + 1;
                    }
                    if (!visited.contains(next)) {
                        queue.add(next);
                    }
                }
            }

            // left
            if (current.x - 2 > 0) {
                Position door = current.left();
                if (map[door.x][door.y] != '#') {
                    Position next = door.left();
                    if (currentMinLen + 1 < shortestPaths[next.x][next.y]) {
                        shortestPaths[next.x][next.y] = currentMinLen + 1;
                    }
                    if (!visited.contains(next)) {
                        queue.add(next);
                    }
                }
            }
        }

    }

    public int getLongestShortestPathLength() {
        int longestShortestPath = 0;
        for (int x = 1; x < maxx - minx; x += 2) {
            for (int y = 1; y < maxy - miny; y += 2) {
                if (shortestPaths[x][y] != Integer.MAX_VALUE) {
                    longestShortestPath = Math.max(longestShortestPath, shortestPaths[x][y]);
                }
            }
        }
        return longestShortestPath;
    }

    private Collection<Position> fillMap(Position position, String regex) {
        map[position.x - minx][position.y - miny] = '.';
        for (int i = 0; i < regex.length(); ++i) {
            if (regex.charAt(i) == 'N') {
                position = position.up();
                map[position.x - minx][position.y - miny] = '-';
                position = position.up();
                map[position.x - minx][position.y - miny] = '.';
            } else if (regex.charAt(i) == 'E') {
                position = position.right();
                map[position.x - minx][position.y - miny] = '|';
                position = position.right();
                map[position.x - minx][position.y - miny] = '.';
            } else if (regex.charAt(i) == 'S') {
                position = position.down();
                map[position.x - minx][position.y - miny] = '-';
                position = position.down();
                map[position.x - minx][position.y - miny] = '.';
            } else if (regex.charAt(i) == 'W') {
                position = position.left();
                map[position.x - minx][position.y - miny] = '|';
                position = position.left();
                map[position.x - minx][position.y - miny] = '.';
            } else if (regex.charAt(i) == '(') {
                List<String> subs = subexpression(regex.substring(i));
                int subsLength = subs.stream().mapToInt(String::length).sum() + subs.size();
                Set<Position> positions = new HashSet<>();
                for (String sub : subs) {
                    positions.addAll(fillMap(position, sub));
                }
                for (Position pos : positions) {
                    fillMap(pos, regex.substring(i + subsLength + 1));
                }
                i += regex.length();
            } else if (regex.charAt(i) == ')') {
                i += 1;
            }
        }
        return Collections.singletonList(position);
    }

    private void printMap() {
        for (int j = 0; j < maxy - miny + 1; ++j) {
            for (int i = 0; i < maxx - minx + 1; ++i) {
                System.out.print(map[i][j]);
            }
            System.out.println();
        }
        System.out.println("----------------------");
    }

    private void printShortestPaths() {
        for (int j = 0; j < maxy - miny + 1; ++j) {
            for (int i = 0; i < maxx - minx + 1; ++i) {
                System.out.print(shortestPaths[i][j] + "|");
            }
            System.out.println();
        }
        System.out.println("----------------------");
    }

    private Collection<Position> findBox(Position position, String regex) {
        for (int i = 0; i < regex.length(); ++i) {
            if (regex.charAt(i) == 'N') {
                position = position.up().up();
                recalculateBox(position);
            } else if (regex.charAt(i) == 'E') {
                position = position.right().right();
                recalculateBox(position);
            } else if (regex.charAt(i) == 'S') {
                position = position.down().down();
                recalculateBox(position);
            } else if (regex.charAt(i) == 'W') {
                position = position.left().left();
                recalculateBox(position);
            } else if (regex.charAt(i) == '(') {
                List<String> subs = subexpression(regex.substring(i));
                int subsLength = subs.stream().mapToInt(String::length).sum() + subs.size();
                Set<Position> positions = new HashSet<>();
                for (String sub : subs) {
                    positions.addAll(findBox(position, sub));
                }
                for (Position pos : positions) {
                    findBox(pos, regex.substring(i + subsLength + 1));
                }
                i += regex.length();
            } else if (regex.charAt(i) == ')') {
                i += 1;
            }
        }
        return Collections.singletonList(position);
    }

    private List<String> subexpression(String expression) {
        List<String> exprs = new ArrayList<>();
        int prevExprIdx = 0;
        int numOpen = 0, numClosed = 0;
        for (int i = 0; i < expression.length(); ++i) {
            if (expression.charAt(i) == '(') {
                numOpen += 1;
            } else if (expression.charAt(i) == ')') {
                numClosed += 1;
            } else if (expression.charAt(i) == '|' && numOpen - numClosed == 1) {
                exprs.add(expression.substring(prevExprIdx + 1, i));
                prevExprIdx = i;
            }
            if (numClosed == numOpen) {
                exprs.add(expression.substring(prevExprIdx + 1, i));
                return exprs;
            }
        }
        return Collections.singletonList(expression);
    }

    private void prepareEmptyMap() {
        minx = minx - 1;
        maxx = maxx + 1;
        miny = miny - 1;
        maxy = maxy + 1;
        map = new char[maxx - minx + 1][maxy - miny + 1];
        for (int i = 0; i < maxx - minx + 1; ++i) {
            for (int j = 0; j < maxy - miny + 1; ++j) {
                map[i][j] = '#';
            }
        }
        //printMap();
    }

    private void recalculateBox(Position position) {
        minx = Math.min(minx, position.x);
        maxx = Math.max(maxx, position.x);
        miny = Math.min(miny, position.y);
        maxy = Math.max(maxy, position.y);
    }
}
