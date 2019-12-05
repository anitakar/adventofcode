package org.anita.adventofcode.year2019;

public class Day5 {

    private int[] memory;
    private int currentPosition = 0;
    private StringBuilder output = new StringBuilder();
    private int input;

    public String interpret(int[] memory, int input) {
        reset(memory, input);

        int code = memory[currentPosition] % 100;

        while (code != 99) {
            if (code == 1) {
                interpretAdd();
            } else if (code == 2) {
                interpretMultiply();
            } else if (code == 3) {
                interpretInput();
            } else if (code == 4) {
                interpretOutput();
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

        return output.toString();
    }

    private void reset(int[] memory, int input) {
        this.memory = memory;
        this.input = input;
        this.currentPosition = 0;
        this.output = new StringBuilder();
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
        memory[pos] = input;
        currentPosition += 2;
    }

    private void interpretOutput() {
        int value = getOp1();
        output.append(value).append(";");
        currentPosition += 2;
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
