package org.anita.adventofcode.year2018;

import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Day6 {

    private int minX = Integer.MAX_VALUE, minY = Integer.MAX_VALUE, maxX = 0, maxY = 0;
    private List<Point> points;

    public Day6() {
    }

    public long task1(List<Point> points) {
        this.points = points;
        calculateBoundingBox();
        LinkedList<Point> pointsQueue = new LinkedList<>();
        Object[][] winners = new Object[maxX - minX + 1][maxY - minY + 1];
        int[][] minLen = new int[maxX - minX + 1][maxY - minY + 1];

        for (int x = 0; x <= maxX - minX; ++x) {
            for (int y = 0; y <= maxY - minY; ++y) {
                winners[x][y] = new HashSet<>();
                minLen[x][y] = Integer.MAX_VALUE;
            }
        }

        for (Point p : points) {
            pointsQueue.add(p);
            winners[p.x - minX][p.y - minY] = new HashSet<>(Collections.singletonList(p));
            minLen[p.x - minX][p.y - minY] = 0;
        }

        Set<Point> visited = new HashSet<>();
        while (!pointsQueue.isEmpty()) {
            Point cur = pointsQueue.poll();
            List<Point> neighs = generateNeighbours(cur);

            for (Point neigh : neighs) {
                if (isOutsideAreaPoint(neigh)) {
                    continue;
                }

                if (minLen[neigh.x - minX][neigh.y - minY] != Integer.MAX_VALUE) {
                    int dist = minLen[neigh.x - minX][neigh.y - minY] + 1;
                    if (dist < minLen[cur.x - minX][cur.y - minY]) {
                        minLen[cur.x - minX][cur.y - minY] = dist;
                        winners[cur.x - minX][cur.y - minY] = new HashSet<>((HashSet<Point>) winners[neigh.x - minX][neigh.y - minY]);
                    } else if (dist == minLen[cur.x - minX][cur.y - minY] && !winners[cur.x - minX][cur.y - minY].equals(winners[neigh.x - minX][neigh.y - minY])) {
                        minLen[cur.x - minX][cur.y - minY] = dist;
                        ((HashSet<Point>) winners[cur.x - minX][cur.y - minY]).addAll((HashSet<Point>) winners[neigh.x - minX][neigh.y - minY]);
                    }
                }
            }

            visited.add(cur);

            for (Point neigh : neighs) {
                if (!pointsQueue.contains(neigh) && !visited.contains(neigh) && !isOutsideAreaPoint(neigh)) {
                    pointsQueue.add(neigh);
                }
            }
        }

        Set<Point> infinityPoints = new HashSet<>();
        for (int x = 0; x <= maxX - minX; ++x) {
            if (((HashSet<Point>)winners[x][0]).size() == 1) {
                infinityPoints.addAll((HashSet<Point>) winners[x][0]);
            }
            if (((HashSet<Point>)winners[x][maxY-minY]).size() == 1) {
                infinityPoints.addAll((HashSet<Point>) winners[x][maxY-minY]);
            }
        }
        for (int y = 0; y <= maxY - minY; ++y) {
            if (((HashSet<Point>)winners[0][y]).size() == 1) {
                infinityPoints.addAll((HashSet<Point>) winners[0][y]);
            }
            if (((HashSet<Point>)winners[maxX-minX][y]).size() == 1) {
                infinityPoints.addAll((HashSet<Point>) winners[maxX-minX][y]);
            }
        }

        Map<String, Long> winnersTotal = new HashMap<>();
        for (int x = 0; x <= maxX - minX; ++x) {
            for (int y = 0; y <= maxY - minY; ++y) {
                Set<Point> curWinners = ((HashSet<Point>) winners[x][y]);
                if (curWinners.size() == 1 && !infinityPoints.contains(curWinners.iterator().next())) {
                    Point winner = curWinners.iterator().next();
                    winnersTotal.compute(winner.toString(), (key, prev) -> Optional.ofNullable(prev).orElse(0L) + 1);
                }
            }
        }

        return winnersTotal.values().stream().mapToLong(i -> i).max().getAsLong();
    }

    public long task2(List<Point> points, int maxManhattanDistance) {
        this.points = points;
        calculateBoundingBox();
        int totalArea = 0;
        for (int x = minX; x <= maxX; ++x) {
            for (int y = minY; y <= maxY; ++y) {
                int totalManhattanDistance = 0;
                for (Point p : points) {
                    totalManhattanDistance += (Math.abs(p.x - x) + Math.abs(p.y - y));
                }
                if (totalManhattanDistance < maxManhattanDistance) {
                    totalArea += 1;
                }
            }
        }
        return totalArea;
    }

    private void calculateBoundingBox() {
        for (Point point : points) {
            if (point.x < minX) {
                minX = point.x;
            }
            if (point.y < minY) {
                minY = point.y;
            }
            if (point.x > maxX) {
                maxX = point.x;
            }
            if (point.y > maxY) {
                maxY = point.y;
            }
        }
    }

    private void printDistances(int[][] minLens) {
        for (int i = 0; i < minLens.length; ++ i) {
            for (int j = 0; j < minLens[i].length; ++j) {
                if (minLens[i][j] != Integer.MAX_VALUE) {
                    System.out.print(minLens[i][j]);
                } else {
                    System.out.print(".");
                }
            }
            System.out.println();
        }
        System.out.println("---------------------------");
    }

    private void printWinners(Object[][] winners) {
        for (int i = 0; i < winners.length; ++ i) {
            for (int j = 0; j < winners[i].length; ++j) {
                if (((HashSet<Point>)winners[i][j]).size() == 0) {
                    System.out.print(".");
                } else if (((HashSet<Point>)winners[i][j]).size() > 1) {
                    System.out.print("X");
                } else {
                    Point p = ((HashSet<Point>)winners[i][j]).iterator().next();
                    if (p.equals(new Point(1, 1))) {
                        if (i == 0 && j == 0) {
                            System.out.print("A");
                        } else {
                            System.out.print("a");
                        }
                    } else if (p.equals(new Point(1, 6))) {
                        if (i == 0 && j == 5) {
                            System.out.print("B");
                        } else {
                            System.out.print("b");
                        }
                    } else if (p.equals(new Point(8, 3))) {
                        if (i == 7 && j == 2) {
                            System.out.print("C");
                        } else {
                            System.out.print("c");
                        }
                    } else if (p.equals(new Point(3, 4))) {
                        if (i == 2 && j == 3) {
                            System.out.print("D");
                        } else {
                            System.out.print("d");
                        }
                    } else if (p.equals(new Point(5, 5))) {
                        if (i == 4 && j == 4) {
                            System.out.print("E");
                        } else {
                            System.out.print("e");
                        }
                    } else if (p.equals(new Point(8, 9))) {
                        if (i == 7 && j == 8) {
                            System.out.print("F");
                        } else {
                            System.out.print("f");
                        }
                    }
                }
            }
            System.out.println();
        }
        System.out.println("---------------------------");
    }

    private boolean isOutsideAreaPoint(Point x) {
        return x.x < minX || x.x > maxX || x.y < minY || x.y > maxY;
    }

    private List<Point> generateNeighbours(Point x) {
        return Arrays.asList(
                new Point(x.x, x.y + 1),
                new Point(x.x, x.y - 1),
                new Point(x.x + 1, x.y),
                new Point(x.x - 1, x.y)
        );
    }

    public static class Point {
        public final int x;
        public final int y;

        public Point(final int x, final int y) {
            this.x = x;
            this.y = y;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Point point = (Point) o;
            return x == point.x &&
                    y == point.y;
        }

        @Override
        public int hashCode() {
            return Objects.hash(x, y);
        }

        @Override
        public String toString() {
            return "Point{" +
                    "x=" + x +
                    ", y=" + y +
                    '}';
        }
    }

    public static String pointRegex = "^(\\d+), (\\d+)$";
    private Pattern pointPattern = Pattern.compile(pointRegex);

    public Point parsePoint(String line) {
        Matcher matcher = pointPattern.matcher(line);
        if (matcher.find()) {
            return new Point(
                    Integer.parseInt(matcher.group(1)),
                    Integer.parseInt(matcher.group(2))
            );
        }
        return null;
    }
}
