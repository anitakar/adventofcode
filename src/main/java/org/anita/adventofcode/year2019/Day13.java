package org.anita.adventofcode.year2019;

import org.anita.adventofcode.structures.Position2D;

import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

public class Day13 {

    public int task1(long[] memory) {
        IntCodeComputer computer = new IntCodeComputer();
        computer.interpret(memory, new long[]{});
        List<Long> output = computer.getOutput();
        int numberOfBlockTiles = 0;
        for (int i = 2; i < output.size(); i += 3) {
            if (output.get(i) == 2) {
                numberOfBlockTiles += 1;
            }
        }
        return numberOfBlockTiles;
    }

    public long task2(long[] memory) {
        Arcade arcade = new Arcade(memory);
        return arcade.play();
    }

    private static class Arcade implements IntCodeComputer.OutputListener {

        long[] memory;
        IntCodeComputer computer;
        private int[][] map;

        public Arcade(long[] memory) {
            readMap(Arrays.copyOf(memory, memory.length));
            //drawMap();
            memory[0] = 2;
            computer = new IntCodeComputer();
            computer.addOutputListener(this);
            this.memory = memory;
        }

        public long play() {
            computer.interpret(memory, new Joystick());
            return lastScore;
        }

        private void readMap(long[] memory) {
            IntCodeComputer computer = new IntCodeComputer();
            computer.interpret(memory, new long[]{});
            List<Long> output = computer.getOutput();
            int maxx = 0, maxy = 0;
            for (int i = 0; i < output.size(); i += 3) {
                if (output.get(i) > maxx) {
                    maxx = output.get(i).intValue();
                }

                if (output.get(i + 1) > maxy) {
                    maxy = output.get(i + 1).intValue();
                }
            }
            maxx += 1; maxy += 1;
            map = new int[maxx][maxy];
            for (int i = 0; i < output.size(); i += 3) {
                int x = output.get(i).intValue();
                int y = output.get(i + 1).intValue();
                int object = output.get(i + 2).intValue();
                map[x][y] = object;
            }
        }

        private void drawMap() {
            for (int y = 0; y < map[0].length; ++y) {
                for (int x = 0; x < map.length; ++x) {
                    int object = map[x][y];
                    if (object == 0) {
                        System.out.print(" ");
                    } else if (object == 1) {
                        System.out.print("X");
                    } else if (object == 2) {
                        System.out.print("#");
                    } else if (object == 3) {
                        System.out.print("_");
                    } else if (object == 4) {
                        System.out.print("o");
                    }
                }
                System.out.println();
            }
        }

        int stage = 0;
        int lastx;
        int lasty;
        long lastScore;
        Position2D ballPosition;
        Position2D paddlePosition;

        @Override
        public void onOutput(long value) {
            if (stage == 0) {
                lastx = (int)value;
            } else if (stage == 1) {
                lasty = (int)value;
            } else if (stage == 2) {
                if (lastx == -1 && lasty == 0) {
                    lastScore = value;
                    //System.out.println("SCORE: " + lastScore);
                } else {
                    map[lastx][lasty] = (int) value;
                    if (value == 4) {
                        ballPosition = new Position2D(lastx, lasty);
                    }
                    if (value == 3) {
                        paddlePosition = new Position2D(lastx, lasty);
                    }
                }
            }

            stage = (stage + 1) % 3;
        }

        private class Joystick implements Iterator<Long> {

            @Override
            public boolean hasNext() {
                return true;
            }

            @Override
            public Long next() {
                //drawMap();
                if (paddlePosition.x < ballPosition.x) {
                    return 1L;
                } else if (paddlePosition.x > ballPosition.x) {
                    return -1L;
                } else {
                    return 0L;
                }
            }
        }
    }
}
