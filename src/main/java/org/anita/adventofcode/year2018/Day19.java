package org.anita.adventofcode.year2018;

import java.util.*;
import java.util.function.BiFunction;

public class Day19 {
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

    Map<String, BiFunction<int[], int[], String>> operationsRepr = new HashMap<>();
    {
        operationsRepr.put("addr", this::addrp);
        operationsRepr.put("addi", this::addip);
        operationsRepr.put("mulr", this::mulrp);
        operationsRepr.put("muli", this::mulip);
        operationsRepr.put("banr", this::banrp);
        operationsRepr.put("bani", this::banip);
        operationsRepr.put("borr", this::borrp);
        operationsRepr.put("bori", this::borip);
        operationsRepr.put("setr", this::setrp);
        operationsRepr.put("seti", this::setip);
        operationsRepr.put("gtir", this::gtirp);
        operationsRepr.put("gtri", this::gtrip);
        operationsRepr.put("gtrr", this::gtrrp);
        operationsRepr.put("eqir", this::eqirp);
        operationsRepr.put("eqri", this::eqrip);
        operationsRepr.put("eqrr", this::eqrrp);
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

    public String addrp(int[] registers, int[] op) {
        return "R[" + op[0] + "] + " + "R[" + op[1] + "]";
    }

    public String addip(int[] registers, int[] op) {
        return "R[" + op[0] + "] + " + op[1];
    }

    public String mulrp(int[] registers, int[] op) {
        return "R[" + op[0] + "] * R[" + op[1] + "]";
    }

    public String mulip(int[] registers, int[] op) {
        return "R[" + op[0] + "] * " + op[1];
    }

    public String banrp(int[] registers, int[] op) {
        return "R[" + op[0] + "] & R[" + op[1] + "]";

    }

    public String banip(int[] registers, int[] op) {
        return "R[" + op[0] + "] & " + op[1];
    }

    public String borrp(int[] registers, int[] op) {
        return "R[" + op[0] + "] | R[" + op[1] + "]";
    }

    public String borip(int[] registers, int[] op) {
        return "R[" + op[0] + "] | " + op[1];
    }

    public String setrp(int[] registers, int[] op) {
        return "R[" + op[0] + "]";
    }

    public String setip(int[] registers, int[] op) {
        return "" + op[0];
    }

    public String gtirp(int[] registers, int[] op) {
        return "" + op[0] + " > R[" + op[1] + "]";
    }

    public String gtrip(int[] registers, int[] op) {
        return "R[" + op[0] + "] > " + op[1];
    }

    public String gtrrp(int[] registers, int[] op) {
        return "R[" + op[0] + "] > R[" + op[1] + "]";
    }

    public String eqirp(int[] registers, int[] op) {
        return "" + op[0] + " == R[" + op[1] + "]";
    }

    public String eqrip(int[] registers, int[] op) {
        return "R[" + op[0] + "] == " + op[1];
    }

    public String eqrrp(int[] registers, int[] op) {
        return "R[" + op[0] + "] == R[" + op[1] + "]";
    }

    public int[] execute(int[] register, int instructionPointerIndex, List<Instruction> instructions) {
        int instructionPointer = register[instructionPointerIndex];
        while (true) {
            if (instructionPointer < 0 || instructionPointer >= instructions.size()) {
                return register;
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
        }
    }

    public int[] executeAndPrint(int[] register, int instructionPointerIndex, List<Instruction> instructions, int maxLoop) {
        int instructionPointer = register[instructionPointerIndex];
        int loop = 0;
        while (true) {
            if (instructionPointer < 0 || instructionPointer >= instructions.size()) {
                System.out.println("THE END");
                return register;
            }
            register[instructionPointerIndex] = instructionPointer;
            //System.out.println("Before R: " + Arrays.toString(register));
            //System.out.print("ip=" + instructionPointer + " " + Arrays.toString(register));
            Instruction currentInstruction = instructions.get(register[instructionPointerIndex]);
            String printResult = "" + register[instructionPointerIndex] + ": R[" + currentInstruction.args[2] + "] := " + operationsRepr.get(currentInstruction.opCode).apply(new int[]{}, currentInstruction.args);
            //printResult = printResult.replaceAll("R\\[" + instructionPointerIndex + "\\]", "IP");
            //System.out.print(" " + currentInstruction);
            Integer result = operations.get(currentInstruction.opCode).apply(register, currentInstruction.args);
            printResult += "\t\t// " + Arrays.toString(register);
            System.out.println(printResult);
            register[currentInstruction.args[2]] = result;
            //System.out.println("After R: " + Arrays.toString(register));
            //System.out.println(" " + Arrays.toString(register));
            instructionPointer = register[instructionPointerIndex];
            instructionPointer += 1;
            loop += 1;
            if (loop > maxLoop) {
                return register;
            }
        }
    }

    public String print(int instructionPointerIndex, List<Instruction> instructions) {
        String result = "";
        Map<Integer, String> registerNames = new HashMap<>();
        for (int i = 0; i < 6; ++i) {
            registerNames.put(i, "" + (char) ('A' + i));
        }
        registerNames.put(instructionPointerIndex, "I");
        //result += "IP " + instructionPointerIndex + "\n";
        int instructionIndex = 0;
        for (Instruction instruction : instructions) {
            result += "" + instructionIndex + ": R[" + instruction.args[2] + "] := " + operationsRepr.get(instruction.opCode).apply(new int[]{}, instruction.args) + "\n";
            instructionIndex += 1;
            //(instruction.opCode + " " + registerNames.get(instruction.args[0]) + " " + registerNames.get(instruction.args[1]) + " " + registerNames.get(instruction.args[2]) + "\n");
        }
        result = result.replaceAll("R\\[" + instructionPointerIndex + "\\]", "IP");
        return result;
    }

    public int task2(int num) {
        double sqrt = Math.sqrt(num);
        int sum = 1 + num;
        for (int i = 2; i <= sqrt; ++i) {
            if (num % i == 0) {
                sum += (i + num / i);
            }
        }
        return sum;
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
