package org.anita.adventofcode.year2019;

import java.util.*;

public class IntCodeComputer {

    private long[] memory;
    private long currentPosition = 0;
    private List<Long> output = new LinkedList<>();
    private long relativeBase = 0;
    private Map<Long, Long> additionalMemory = new HashMap<>();
    private Iterator<Long> input;
    private OutputListener outputListener;

    public void addOutputListener(OutputListener outputListener) {
        this.outputListener = outputListener;
    }



    public long[] interpret(long[] memory, long[] input) {
        return interpret(memory, new DefaultInput(input));
    }

    public long[] interpret(long[] memory, Iterator<Long> input) {
        set(memory, input);

        long code = accessMemory(currentPosition);

        while ((code % 100) != 99) {
            if ((code % 100) == 1) {
                interpretAdd();
            } else if ((code % 100) == 2) {
                interpretMultiply();
            } else if ((code % 100) == 3) {
                interpretInput();
            } else if ((code % 100) == 4) {
                interpretOutput();
            } else if ((code % 100) == 5) {
                interpretJumpIfTrue();
            } else if ((code % 100) == 6) {
                interpretJumpIfFalse();
            } else if ((code % 100) == 7) {
                interpretLessThan();
            } else if ((code % 100) == 8) {
                interpretEquals();
            } else if ((code % 100) == 9) {
                interpretAdjustBase();
            } else {
                throw new RuntimeException();
            }

            code = accessMemory(currentPosition);
        }

        return output.stream().mapToLong(i -> i).toArray();
    }

    public List<Long> getOutput() {
        return output;
    }

    private void set(long[] memory, Iterator<Long> input) {
        this.memory = memory;
        this.input = input;
    }

    private void interpretAdd() {
        long op1 = getOp1();
        long op2 = getOp2();

        long position = getOp3();
        storeInMemory(position, Math.addExact(op1, op2));

        currentPosition += 4;
    }

    private void interpretMultiply() {
        long op1 = getOp1();
        long op2 = getOp2();

        long position = getOp3();
        storeInMemory(position, Math.multiplyExact(op1, op2));

        currentPosition += 4;
    }

    private void interpretInput() {
        long opMode = (accessMemory(currentPosition) / 100) % 10;
        long position = accessMemory(currentPosition + 1);
        if (opMode == 2) {
            position += relativeBase;
        }

        storeInMemory(position, input.next());
        currentPosition += 2;
    }

    private void interpretOutput() {
        long value = getOp1();
        output.add(value);
        if (outputListener != null) {
            outputListener.onOutput(value);
        }
        currentPosition += 2;
    }

    private void interpretJumpIfTrue() {
        long op1 = getOp1();
        long op2 = getOp2();

        if (op1 != 0) {
            currentPosition = op2;
        } else {
            currentPosition += 3;
        }
    }

    private void interpretJumpIfFalse() {
        long op1 = getOp1();
        long op2 = getOp2();

        if (op1 == 0) {
            currentPosition = op2;
        } else {
            currentPosition += 3;
        }
    }

    private void interpretLessThan() {
        long op1 = getOp1();
        long op2 = getOp2();
        long position = getOp3();

        storeInMemory(position, op1 < op2 ? 1 : 0);

        currentPosition += 4;
    }

    private void interpretEquals() {
        long op1 = getOp1();
        long op2 = getOp2();
        long position = getOp3();

        storeInMemory(position, op1 == op2 ? 1 : 0);

        currentPosition += 4;
    }

    private void interpretAdjustBase() {
        relativeBase += getOp1();

        currentPosition += 2;
    }

    private long getOp(int base, int paramIndex) {
        long opMode = (accessMemory(currentPosition) / base) % 10;
        long op = accessMemory(currentPosition + paramIndex);
        if (opMode == 0) {
            op = accessMemory(op);
        } else if (opMode == 2) {
            op = accessMemory(relativeBase + op);
        }
        return op;
    }

    private long getOp1() {
        return getOp(100, 1);
    }

    private long getOp2() {
        return getOp(1000, 2);
    }

    private long getOp3() {
        long opMode = (accessMemory(currentPosition) / 10000) % 10;
        long position = accessMemory(currentPosition + 3);
        if (opMode == 2) {
            position += relativeBase;
        }

        return position;
    }

    private long accessMemory(long position) {
        if (position < memory.length) {
            return memory[(int)position];
        } else {
            return additionalMemory.getOrDefault(position, 0L);
        }
    }

    private void storeInMemory(long position, long value) {
        if (position < memory.length) {
            memory[(int)position] = value;
        } else {
            additionalMemory.put(position, value);
        }
    }

    private static class DefaultInput implements Iterator<Long> {

        long[] input;
        private int inputPosition = 0;

        DefaultInput(long[] input) {
            this.input = input;
        }

        @Override
        public boolean hasNext() {
            return inputPosition < input.length - 1;
        }

        @Override
        public Long next() {
            return input[inputPosition++];
        }
    }

    public static interface OutputListener {
        void onOutput(long value);
    }
}
