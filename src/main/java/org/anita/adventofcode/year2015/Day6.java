package org.anita.adventofcode.year2015;

import org.anita.adventofcode.structures.Position2D;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Day6 {

    private int[][] grid = new int[1000][1000];

    public void performInstructions(List<Instruction> instructions) {
        instructions.forEach(Instruction::perform);
    }

    public void performInstructions2(List<Instruction> instructions) {
        instructions.forEach(Instruction::perform2);
    }

    public int numberOfLightsLit() {
        int numberLit = 0;
        for (int i = 0; i < 1000; ++i) {
            for (int j = 0; j < 1000; ++j) {
                numberLit += grid[i][j];
            }
        }
        return numberLit;
    }

    class Instruction {
        int operation;
        Position2D from, to;

        public Instruction(int operation, Position2D from, Position2D to) {
            this.operation = operation;
            this.from = from;
            this.to = to;
        }

        public void perform() {
            for (int i = from.x; i <= to.x; ++i) {
                for (int j = from.y; j <= to.y; ++j) {
                    switch (operation) {
                        case 0:
                            grid[i][j] = 0;
                            break;
                        case 1:
                            grid[i][j] = 1;
                            break;
                        case 2:
                            grid[i][j] = grid[i][j] == 0 ? 1 : 0;
                            break;
                    }
                }
            }
        }

        public void perform2() {
            for (int i = from.x; i <= to.x; ++i) {
                for (int j = from.y; j <= to.y; ++j) {
                    switch (operation) {
                        case 0:
                            grid[i][j] = Math.max(0, grid[i][j] - 1);
                            break;
                        case 1:
                            grid[i][j] += 1;
                            break;
                        case 2:
                            grid[i][j] += 2;
                            break;
                    }
                }
            }
        }
    }

    public static String instructionRegex = "(\\D+)(\\d+),(\\d+) through (\\d+),(\\d+)$";
    private Pattern instructionPattern = Pattern.compile(instructionRegex);

    public Instruction parseInstruction(String line) {
        Matcher matcher = instructionPattern.matcher(line);
        if (matcher.find()) {
            return new Instruction(
                    parseCommand(matcher.group(1)),
                    new Position2D(Integer.parseInt(matcher.group(2)), Integer.parseInt(matcher.group(3))),
                    new Position2D(Integer.parseInt(matcher.group(4)), Integer.parseInt(matcher.group(5)))
            );
        }
        return null;
    }

    private int parseCommand(String command) {
        switch (command.trim()) {
            case "turn off":
                return 0;
            case "turn on":
                return 1;
            case "toggle":
                return 2;
            default:
                throw new IllegalArgumentException("Wrong command " + command);
        }
    }
}
