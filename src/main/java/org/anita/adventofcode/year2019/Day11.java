package org.anita.adventofcode.year2019;

import org.anita.adventofcode.structures.Position2D;

import java.util.*;

public class Day11 {

    private static final int UP = 0;
    private static final int RIGHT = 1;
    private static final int DOWN = 2;
    private static final int LEFT = 3;

    public int task1(long[] memory) {
        IntCodeComputer robotProgram = new IntCodeComputer();
        Environment env = new Environment(robotProgram.getOutput());
        Robot robot = new Robot(env);
        robotProgram.addOutputListener(robot);
        robotProgram.interpret(memory, robot);
        return env.paintedPanels.size();
    }

    public void task2(long[] memory) {
        IntCodeComputer robotProgram = new IntCodeComputer();
        Environment env = new Environment(robotProgram.getOutput());
        env.whitePanels.add(new Position2D(0, 0));
        Robot robot = new Robot(env);
        robotProgram.addOutputListener(robot);
        robotProgram.interpret(memory, robot);

        int minX = env.whitePanels.stream().mapToInt(p -> p.x).min().getAsInt();
        int maxX = env.whitePanels.stream().mapToInt(p -> p.x).max().getAsInt();
        int minY = env.whitePanels.stream().mapToInt(p -> p.y).min().getAsInt();
        int maxY = env.whitePanels.stream().mapToInt(p -> p.y).max().getAsInt();

        for (int y = maxY; y >= minY; --y) {
            for (int x = minX; x <= maxX; ++x) {
                if (env.whitePanels.contains(new Position2D(x, y))) {
                    System.out.print("X");
                } else {
                    System.out.print(" ");
                }
            }
            System.out.println();
        }
    }

    public static class Environment {
        public Set<Position2D> paintedPanels = new HashSet<>();
        public Set<Position2D> whitePanels = new HashSet<>();
        public List<Long> output;

        public Environment(List<Long> output) {
            this.output = output;
        }
    }

    public static class Robot implements Iterator<Long>, IntCodeComputer.OutputListener {

        private Environment env;

        private Position2D currentPosition= new Position2D(0,0);
        private int currentDirection = 0; // 0 - up, 1 - right, 2 - down, 3 - left
        private int phase = 0; // 0 - color, 1 - direction

        public Robot(Environment env) {
            this.env = env;
        }

        @Override
        public boolean hasNext() {
            return true;
        }

        @Override
        public Long next() {
            return env.whitePanels.contains(currentPosition) ? 1L : 0L;
        }

        @Override
        public void onOutput(long value) {
            if (phase == 0) {
                paint(value);
                phase = 1;
            } else if (phase == 1) {
                move(value);
                phase = 0;
            }
        }

        private void move(long direction) {
            if (direction == 0) {
                if (currentDirection == UP) {
                    currentPosition = new Position2D(currentPosition.x - 1, currentPosition.y);
                    currentDirection = LEFT;
                } else if (currentDirection == RIGHT) {
                    currentPosition = new Position2D(currentPosition.x, currentPosition.y + 1);
                    currentDirection = UP;
                } else if (currentDirection == DOWN) {
                    currentPosition = new Position2D(currentPosition.x + 1, currentPosition.y);
                    currentDirection = RIGHT;
                } else if (currentDirection == LEFT) {
                    currentPosition = new Position2D(currentPosition.x, currentPosition.y - 1);
                    currentDirection = DOWN;
                }
            } else if (direction == 1) {
                if (currentDirection == UP) {
                    currentPosition = new Position2D(currentPosition.x + 1, currentPosition.y);
                    currentDirection = RIGHT;
                } else if (currentDirection == RIGHT) {
                    currentPosition = new Position2D(currentPosition.x, currentPosition.y - 1);
                    currentDirection = DOWN;
                } else if (currentDirection == DOWN) {
                    currentPosition = new Position2D(currentPosition.x - 1, currentPosition.y);
                    currentDirection = LEFT;
                } else if (currentDirection == LEFT) {
                    currentPosition = new Position2D(currentPosition.x, currentPosition.y + 1);
                    currentDirection = UP;
                }
            }
        }

        private void paint(long color) {
            env.paintedPanels.add(currentPosition);
            if (color == 0) {
                env.whitePanels.remove(currentPosition);
            } else if (color == 1) {
                env.whitePanels.add(currentPosition);
            }
        }
    }
}
