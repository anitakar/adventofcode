package org.anita.adventofcode.year2020;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Day8 {
    public static class Computer {
        private int instructionPointer = 0;
        private long accumulator = 0;
        private Set<Integer> visitedLines = new HashSet<>();
        private List<String> code;

        public long interpret(List<String> code) {
            reset(code);

            while (!visitedLines.contains(instructionPointer)) {
                if (instructionPointer == code.size()) {
                    break;
                }
                String line = code.get(instructionPointer);
                final String[] instructionAndArgument = line.split("\\s");
                String instruction = instructionAndArgument[0];
                int argument = Integer.parseInt(instructionAndArgument[1]);
                visitedLines.add(instructionPointer);
                switch (instruction) {
                    case "nop":
                        instructionPointer += 1;
                        break;
                    case "acc":
                        instructionPointer += 1;
                        accumulator += argument;
                        break;
                    case "jmp":
                        instructionPointer += argument;
                        break;
                }
            }
            return accumulator;
        }

        private void reset(List<String> code) {
            this.code = code;
            this.instructionPointer = 0;
            this.visitedLines = new HashSet<>();
            this.accumulator = 0;
        }

        public boolean terminatedCorrectly() {
            return instructionPointer == code.size();
        }

        public long getAccumulator() {
            return accumulator;
        }
    }
}
