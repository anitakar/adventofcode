package org.anita.adventofcode.year2020;

public class Day12 {
    public static class Ship {
        private int direction = 1;
        private int x = 0;
        private int y = 0;

        private int wayPointX = 10;
        private int wayPointY = 1;

        public int move2(String instruction) {
            char command = instruction.charAt(0);
            if (command == 'N') {
                wayPointY += Integer.parseInt(instruction.substring(1));
            } else if (command == 'E') {
                wayPointX += Integer.parseInt(instruction.substring(1));
            } else if (command == 'S') {
                wayPointY -= Integer.parseInt(instruction.substring(1));
            } else if (command == 'W') {
                wayPointX -= Integer.parseInt(instruction.substring(1));
            } else if (command == 'R') {
                int dirs = Integer.parseInt(instruction.substring(1)) / 90;
                if (dirs == 1) {
                    int temp = wayPointX;
                    wayPointX = wayPointY;
                    wayPointY = -temp;
                } else if (dirs == 2) {
                    wayPointY = -wayPointY;
                    wayPointX = -wayPointX;
                } else if (dirs == 3) {
                    int temp = wayPointX;
                    wayPointX = -wayPointY;
                    wayPointY = temp;
                }
            } else if (command == 'L') {
                int dirs = Integer.parseInt(instruction.substring(1)) / 90;
                if (dirs == 3) {
                    int temp = wayPointX;
                    wayPointX = wayPointY;
                    wayPointY = -temp;
                } else if (dirs == 2) {
                    wayPointY = - wayPointY;
                    wayPointX = - wayPointX;
                } else if (dirs == 1) {
                    int temp = wayPointX;
                    wayPointX = -wayPointY;
                    wayPointY = temp;
                }
            } else if (command == 'F') {
                int distance = Integer.parseInt(instruction.substring(1));
                x += distance * wayPointX;
                y += distance * wayPointY;
            }
            return Math.abs(x) + Math.abs(y);
        }

        public int move(String instruction) {
            char command = instruction.charAt(0);
            if (command == 'N') {
                y += Integer.parseInt(instruction.substring(1));
            } else if (command == 'E') {
                x += Integer.parseInt(instruction.substring(1));
            } else if (command == 'S') {
                y -= Integer.parseInt(instruction.substring(1));
            } else if (command == 'W') {
                x -= Integer.parseInt(instruction.substring(1));
            } else if (command == 'L') {
                direction = (direction + 4 - (Integer.parseInt(instruction.substring(1)) / 90)) % 4;
            } else if (command == 'R') {
                direction = (direction + (Integer.parseInt(instruction.substring(1)) / 90)) % 4;
            } else if (command == 'F') {
                int distance = Integer.parseInt(instruction.substring(1));
                if (direction == 0) {
                    y += distance;
                } else if (direction == 1) {
                    x += distance;
                } else if (direction == 2) {
                    y -= distance;
                } else if (direction == 3) {
                    x -= distance;
                }
            }
            return Math.abs(x) + Math.abs(y);
        }

        public int getManhattanDistance() {
            return Math.abs(x) + Math.abs(y);
        }
    }
}
