package org.anita.adventofcode.year2018;

import java.util.*;
import java.util.function.BiFunction;

public class Day21 {

    Map<String, BiFunction<int[], int[], Integer>> operations = new HashMap<String, BiFunction<int[], int[], Integer>>();

    {
        operations.put("addr", this::addr);
        operations.put("addi", this::addi);
        operations.put("mulr", this::mulr);
        operations.put("muli", this::muli);
        operations.put("banr", this::banr);
        operations.put("bani", this::bani);
        operations.put("borr", this::borr);
        operations.put("bori", this::bori);
        operations.put("setr", this::setr);
        operations.put("seti", this::seti);
        operations.put("gtir", this::gtir);
        operations.put("gtri", this::gtri);
        operations.put("gtrr", this::gtrr);
        operations.put("eqir", this::eqir);
        operations.put("eqri", this::eqri);
        operations.put("eqrr", this::eqrr);
    }

    public int addr(int[] registers, int[] op) {
        return registers[op[0]] + registers[op[1]];
    }

    public int addi(int[] registers, int[] op) {
        return registers[op[0]] + op[1];
    }

    public int mulr(int[] registers, int[] op) {
        return registers[op[0]] * registers[op[1]];
    }

    public int muli(int[] registers, int[] op) {
        return registers[op[0]] * op[1];
    }

    public int banr(int[] registers, int[] op) {
        return registers[op[0]] & registers[op[1]];

    }

    public int bani(int[] registers, int[] op) {
        return registers[op[0]] & op[1];
    }

    public int borr(int[] registers, int[] op) {
        return registers[op[0]] | registers[op[1]];
    }

    public int bori(int[] registers, int[] op) {
        return registers[op[0]] | op[1];

    }

    public int setr(int[] registers, int[] op) {
        return registers[op[0]];
    }

    public int seti(int[] registers, int[] op) {
        return op[0];
    }

    public int gtir(int[] registers, int[] op) {
        return op[0] > registers[op[1]] ? 1 : 0;
    }

    public int gtri(int[] registers, int[] op) {
        return registers[op[0]] > op[1] ? 1 : 0;
    }

    public int gtrr(int[] registers, int[] op) {
        return registers[op[0]] > registers[op[1]] ? 1 : 0;
    }

    public int eqir(int[] registers, int[] op) {
        return op[0] == registers[op[1]] ? 1 : 0;
    }

    public int eqri(int[] registers, int[] op) {
        return registers[op[0]] == op[1] ? 1 : 0;
    }

    public int eqrr(int[] registers, int[] op) {
        return registers[op[0]] == registers[op[1]] ? 1 : 0;
    }

    public int execute(int[] register, int instructionPointerIndex, List<Instruction> instructions) {
        int instructionCounter = 0;
        int instructionPointer = 0;
        while (instructionCounter < 10000000) {
            if (instructionPointer < 0 || instructionPointer >= instructions.size()) {
                return instructionCounter;
            }
            register[instructionPointerIndex] = instructionPointer;
            //System.out.print("ip=" + instructionPointer + " " + Arrays.toString(register));
            Instruction currentInstruction = instructions.get(register[instructionPointerIndex]);
            //System.out.print(" " + currentInstruction);
            Integer result = operations.get(currentInstruction.opCode).apply(register, currentInstruction.args);
            register[currentInstruction.args[2]] = result;
            //System.out.println(" " + Arrays.toString(register));
            instructionPointer = register[instructionPointerIndex];
            instructionPointer += 1;
            ++instructionCounter;
        }
        return Integer.MAX_VALUE;
    }

    public static List<Instruction> readProgram(List<String> input) {
        List<Instruction> result = new ArrayList<>();
        for (String line : input) {
            String[] op = line.split(" ");
            result.add(new Instruction(op[0], new int[]{
                    Integer.parseInt(op[1]), Integer.parseInt(op[2]), Integer.parseInt(op[3])
            }));
        }
        return result;
    }

    public static class Instruction {
        String opCode;
        int[] args;

        public Instruction(String opCode, int[] args) {
            this.opCode = opCode;
            this.args = args;
        }

        @Override
        public String toString() {
            return "Instruction{" +
                    "opCode='" + opCode + '\'' +
                    ", args=" + Arrays.toString(args) +
                    '}';
        }
    }
}
