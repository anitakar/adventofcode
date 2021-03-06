package org.anita.adventofcode.year2018;

import org.anita.adventofcode.structures.Position2D;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.TreeMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class Day17 {

    int maxx = 500, maxy = 0, minx = 500, miny = 0;
    int realMinY = Integer.MAX_VALUE;
    char[][] map;
    TreeMap<Position2D, Drop> drops = new TreeMap<>(new Comparator<Position2D>() {
        @Override
        public int compare(Position2D o1, Position2D o2) {
            int diffy = o2.y - o1.y;
            if (diffy != 0) {
                return diffy;
            }
            return o2.x - o1.x;
        }
    });


    public Day17(List<String> lines) {
        parseMap(lines);
        //printMap();
    }

    public long task1() {
        int time = 0;
        while (true) {
            time += 1;
            drops.put(new Position2D(500, 1), new Drop(new Position2D(500, 1), '|'));
            int prevDropsSize = drops.size();

            List<Drop> currentDrops = new ArrayList<>(this.drops.values());
            currentDrops = currentDrops.stream().filter(d -> d.type == '|').collect(Collectors.toList());
            for (int i = 0; i < currentDrops.size(); ++i) {
                Drop drop = currentDrops.get(i);
                Position2D curPosition = new Position2D(drop.position.x, drop.position.y);
                drop.move();
                drops.remove(curPosition);
                if (drop.type == '|' && !drop.isBelow()) {
                    drops.put(new Position2D(curPosition.x, curPosition.y), new Drop(new Position2D(curPosition.x, curPosition.y), '|'));
                }
                if (!drop.isBelow()) {
                    drops.put(new Position2D(drop.position.x, drop.position.y), drop);
                }
            }
            //System.out.println(time);
            //printMap();

            if (prevDropsSize == drops.size()) {
                printMap();
                return drops.values().stream().filter(v -> v.position.y >= realMinY).count();
            }
        }
    }

    public long task2() {
        task1();
        return drops.values().stream().filter(v -> v.type == '~').count();
    }

    private static final String verticalRegex = "^x=(\\d+), y=(\\d+)..(\\d+)$";
    private static final String horizontalRegex = "^y=(\\d+), x=(\\d+)..(\\d+)$";
    private static final Pattern verticalPattern = Pattern.compile(verticalRegex);
    private static final Pattern horizontalPattern = Pattern.compile(horizontalRegex);

    private void parseMap(List<String> lines) {
        for (String line : lines) {
            Matcher verticalMatcher = verticalPattern.matcher(line);
            if (verticalMatcher.find()) {
                int x = Integer.parseInt(verticalMatcher.group(1));
                int y1 = Integer.parseInt(verticalMatcher.group(2));
                int y2 = Integer.parseInt(verticalMatcher.group(3));
                maxx = Math.max(maxx, x);
                minx = Math.min(minx, x);
                maxy = Math.max(maxy, y2);
                miny = Math.min(miny, y1);
                realMinY = Math.min(realMinY, y1);
            } else {
                Matcher horizontalMatcher = horizontalPattern.matcher(line);
                if (horizontalMatcher.find()) {
                    int y = Integer.parseInt(horizontalMatcher.group(1));
                    int x1 = Integer.parseInt(horizontalMatcher.group(2));
                    int x2 = Integer.parseInt(horizontalMatcher.group(3));
                    maxy = Math.max(maxy, y);
                    miny = Math.min(miny, y);
                    maxx = Math.max(maxx, x2);
                    minx = Math.min(minx, x1);
                    realMinY = Math.min(realMinY, y);
                }
            }
        }
        maxx += 1;
        minx -= 1;
        map = new char[maxx - minx + 1][maxy - miny + 1];
        for (int x = 0; x < map.length; ++x) {
            for (int y = 0; y < map[0].length; ++y) {
                map[x][y] = '.';
            }
        }
        map[500 - minx][0] = '+';
        for (String line : lines) {
            Matcher verticalMatcher = verticalPattern.matcher(line);
            if (verticalMatcher.find()) {
                int x = Integer.parseInt(verticalMatcher.group(1));
                int y1 = Integer.parseInt(verticalMatcher.group(2));
                int y2 = Integer.parseInt(verticalMatcher.group(3));
                for (int y = y1; y <= y2; ++y) {
                    map[x - minx][y - miny] = '#';
                }
            } else {
                Matcher horizontalMatcher = horizontalPattern.matcher(line);
                if (horizontalMatcher.find()) {
                    int y = Integer.parseInt(horizontalMatcher.group(1));
                    int x1 = Integer.parseInt(horizontalMatcher.group(2));
                    int x2 = Integer.parseInt(horizontalMatcher.group(3));
                    for (int x = x1; x <= x2; ++x) {
                        map[x - minx][y - miny] = '#';
                    }
                }
            }
        }
    }

    private void printMap() {
        for (int y = 0; y < map[0].length; ++y) {
            for (int x = 0; x < map.length; ++x) {
                if (drops.containsKey(new Position2D(x + minx, y + miny))) {
                    System.out.print(drops.get(new Position2D(x + minx, y + miny)).type);
                } else {
                    System.out.print(map[x][y]);
                }
            }
            System.out.println();
        }
        System.out.println("-----------------------------------------");
    }

    public class Drop {
        Position2D position;
        char type;

        public Drop(Position2D position, char type) {
            this.position = position;
            this.type = type;
        }

        public boolean move() {
            if (isSettled()) {
                return false;
            }

            Position2D down = position.down();
            if (down.y > maxy || (map[down.x - minx][down.y - miny] == '.' && !drops.containsKey(down))) {
                position = down;
            } else {
                Position2D below = position.down();
                if (map[below.x - minx][below.y - miny] == '#' || (drops.containsKey(below) && drops.get(below).type == '~')) {
                    int leftEnd = -1, rightEnd = -1;
                    boolean rightSpilling = false;
                    for (int x = position.x + 1; x <= maxx; ++x) {
                        if (map[x - minx][position.y - miny] == '#') {
                            rightEnd = x;
                            break;
                        } else if (map[x - minx][position.y - miny + 1] != '#') {
                            Position2D justBelow = new Position2D(x, position.y + 1);
                            if (!drops.containsKey(justBelow) || drops.get(justBelow).type != '~') {
                                rightEnd = x + 1;
                                rightSpilling = true;
                                break;
                            }
                        }
                    }
                    boolean leftSpilling = false;
                    for (int x = position.x - 1; x >= minx; --x) {
                        if (map[x - minx][position.y - miny] == '#') {
                            leftEnd = x;
                            break;
                        } else if (map[x - minx][position.y - miny + 1] == '.') {
                            Position2D justBelow = new Position2D(x, position.y + 1);
                            if (!drops.containsKey(justBelow) || drops.get(justBelow).type != '~') {
                                leftEnd = x - 1;
                                leftSpilling = true;
                                break;
                            }
                        }
                    }
                    if (rightEnd != -1 && leftEnd != -1) {
                        char dropType = (leftSpilling || rightSpilling) ? '|' : '~';
                        this.type = dropType;
                        for (int x = leftEnd + 1; x < rightEnd; ++x) {
                            drops.put(new Position2D(x, position.y), new Drop(new Position2D(x, position.y), dropType));
                        }
                        return true;
                    }
                }
            }
            return false;
        }

        public boolean isSettled() {
            return type == '~';
        }

        public boolean isBelow() {
            return position.x < minx || position.x > maxx || position.y > maxy;
        }
    }

}
