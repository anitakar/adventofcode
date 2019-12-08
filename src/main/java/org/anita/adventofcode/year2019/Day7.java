package org.anita.adventofcode.year2019;

import java.util.Arrays;

public class Day7 {

    public static class Program {
        private int[] originalMemory;
        private int[] phaseSettings;

        public Program(int[] memory, int[] phaseSettings) {
            this.originalMemory = Arrays.copyOf(memory, memory.length);
            this.phaseSettings = Arrays.copyOf(phaseSettings, phaseSettings.length);
        }

        public int interpret1() {
            int[] memory = Arrays.copyOf(originalMemory, originalMemory.length);
            Amplifier amplifier = new Amplifier(memory, new int[]{phaseSettings[0], 0});
            int amplifirerOutput = amplifier.interpret();
            amplifier = new Amplifier(memory, new int[]{phaseSettings[1], amplifirerOutput});
            amplifirerOutput = amplifier.interpret();
            amplifier = new Amplifier(memory, new int[]{phaseSettings[2], amplifirerOutput});
            amplifirerOutput = amplifier.interpret();
            amplifier = new Amplifier(memory, new int[]{phaseSettings[3], amplifirerOutput});
            amplifirerOutput = amplifier.interpret();
            amplifier = new Amplifier(memory, new int[]{phaseSettings[4], amplifirerOutput});
            amplifirerOutput = amplifier.interpret();
            return amplifirerOutput;
        }

        public int interpret2() {
            int[] memory = Arrays.copyOf(originalMemory, originalMemory.length);
            int signal = 0;
            boolean isHalted = false;
            Amplifier amplifier1 = new Amplifier(memory, new int[]{phaseSettings[0], signal});
            Amplifier amplifier2 = new Amplifier(memory, new int[]{phaseSettings[1], signal});
            Amplifier amplifier3 = new Amplifier(memory, new int[]{phaseSettings[2], signal});
            Amplifier amplifier4 = new Amplifier(memory, new int[]{phaseSettings[3], signal});
            Amplifier amplifier5 = new Amplifier(memory, new int[]{phaseSettings[4], signal});
            while (!isHalted) {
                signal = amplifier1.interpret();
                amplifier2.setSignal(signal);
                signal = amplifier2.interpret();
                amplifier3.setSignal(signal);
                signal = amplifier3.interpret();
                amplifier4.setSignal(signal);
                signal = amplifier4.interpret();
                amplifier5.setSignal(signal);
                signal = amplifier5.interpret();
                amplifier1.setSignal(signal);
                isHalted = amplifier5.isHalted();
            }
            return signal;
        }
    }

    public static class Amplifier {

        private int[] memory;
        private int currentPosition = 0;
        private int output = 0;
        private int[] inputs;
        private int inputIndex;
        private int code;

        public Amplifier(int[] memory, int[] inputs) {
            reset(memory, inputs);
        }

        public int interpret() {
            //currentPosition = 0;
            code = memory[currentPosition] % 100;

            while (code != 99) {
                if (code == 1) {
                    interpretAdd();
                } else if (code == 2) {
                    interpretMultiply();
                } else if (code == 3) {
                    interpretInput();
                } else if (code == 4) {
                    interpretOutput();
                    break;
                } else if (code == 5) {
                    interpretJumpIfTrue();
                } else if (code == 6) {
                    interpretJumpIfFalse();
                } else if (code == 7) {
                    interpretLessThan();
                } else if (code == 8) {
                    interpretEquals();
                } else {
                    throw new RuntimeException();
                }

                code = memory[currentPosition] % 100;
            }

            code = memory[currentPosition] % 100;

            return output;
        }

        public boolean isHalted() {
            return code == 99;
        }

        public void setSignal(int signal) {
            inputs[1] = signal;
        }

        private void reset(int[] memory, int[] inputs) {
            this.memory = memory;
            this.inputs = inputs;
            this.currentPosition = 0;
            this.output = 0;
            this.inputIndex = 0;
        }

        private void interpretAdd() {
            int op1 = getOp1();
            int op2 = getOp2();

            memory[memory[currentPosition + 3]] = op1 + op2;

            currentPosition += 4;
        }

        private void interpretMultiply() {
            int op1 = getOp1();
            int op2 = getOp2();

            memory[memory[currentPosition + 3]] = op1 * op2;

            currentPosition += 4;
        }

        private void interpretInput() {
            int pos = memory[currentPosition + 1];
            int value = inputs[inputIndex];
            if (inputIndex == 0) {
                inputIndex = 1;
            }

            memory[pos] = value;
            currentPosition += 2;
        }

        private void interpretOutput() {
            int value = getOp1();
            output = value;
            currentPosition += 2;
            System.out.print("; Output: " + output);
        }

        private void interpretJumpIfTrue() {
            int op1 = getOp1();
            int op2 = getOp2();

            if (op1 != 0) {
                currentPosition = op2;
            } else {
                currentPosition += 3;
            }
        }

        private void interpretJumpIfFalse() {
            int op1 = getOp1();
            int op2 = getOp2();

            if (op1 == 0) {
                currentPosition = op2;
            } else {
                currentPosition += 3;
            }
        }

        private void interpretLessThan() {
            int op1 = getOp1();
            int op2 = getOp2();

            memory[memory[currentPosition + 3]] = op1 < op2 ? 1 : 0;

            currentPosition += 4;
        }

        private void interpretEquals() {
            int op1 = getOp1();
            int op2 = getOp2();

            memory[memory[currentPosition + 3]] = op1 == op2 ? 1 : 0;

            currentPosition += 4;
        }

        private int getOp1() {
            int op1Mode = (memory[currentPosition] / 100) % 10;
            int op1 = memory[currentPosition + 1];
            if (op1Mode == 0) {
                op1 = memory[op1];
            }
            return op1;
        }

        private int getOp2() {
            int op2Mode = (memory[currentPosition] / 1000) % 10;
            int op2 = memory[currentPosition + 2];
            if (op2Mode == 0) {
                op2 = memory[op2];
            }
            return op2;
        }
    }
}
