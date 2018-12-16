package org.anita.adventofcode.year2018;

import java.util.*;
import java.util.function.BiFunction;

public class Day16 {

    List<BiFunction<int[], int[], Integer>> operations = new ArrayList<>(Arrays.asList(
            this::addr, this::addi,
            this::mulr, this::muli,
            this::banr, this::bani,
            this::borr, this::bori,
            this::setr, this::seti,
            this::gtir, this::gtri, this::gtrr,
            this::eqir, this::eqri, this::eqrr
    ));

    public int addr(int[] registers, int[] op) {
        return registers[op[1]] + registers[op[2]];
    }

    public int addi(int[] registers, int[] op) {
        return registers[op[1]] + op[2];
    }

    public int mulr(int[] registers, int[] op) {
        return registers[op[1]] * registers[op[2]];
    }

    public int muli(int[] registers, int[] op) {
        return registers[op[1]] * op[2];
    }

    public int banr(int[] registers, int[] op) {
        return registers[op[1]] & registers[op[2]];

    }

    public int bani(int[] registers, int[] op) {
        return registers[op[1]] & op[2];
    }

    public int borr(int[] registers, int[] op) {
        return registers[op[1]] | registers[op[2]];
    }

    public int bori(int[] registers, int[] op) {
        return registers[op[1]] | op[2];

    }

    public int setr(int[] registers, int[] op) {
        return registers[op[1]];
    }

    public int seti(int[] registers, int[] op) {
        return op[1];
    }

    public int gtir(int[] registers, int[] op) {
        return op[1] > registers[op[2]] ? 1 : 0;
    }

    public int gtri(int[] registers, int[] op) {
        return registers[op[1]] > op[2] ? 1 : 0;

    }

    public int gtrr(int[] registers, int[] op) {
        return registers[op[1]] > registers[op[2]] ? 1 : 0;
    }

    public int eqir(int[] registers, int[] op) {
        return op[1] == registers[op[2]] ? 1 : 0;
    }

    public int eqri(int[] registers, int[] op) {
        return registers[op[1]] == op[2] ? 1 : 0;
    }

    public int eqrr(int[] registers, int[] op) {
        return registers[op[1]] == registers[op[2]] ? 1 : 0;
    }

    public Set<Integer> matchingOps(int[] registers, int[] op, int[] after) {
        Set<Integer> opsMatch = new TreeSet<>();
        for (int i = 0; i < 16; ++i) {
            BiFunction<int[], int[], Integer> operation = operations.get(i);
            if (operation.apply(registers, op) == after[op[3]]) {
                opsMatch.add(i);
            }
        }
        return opsMatch;
    }

    public Map<Integer, Integer> opsDecoding(List<Operation> ops) {
        TreeMap<Integer, Set<Integer>> opsMappings = new TreeMap<>();
        for (int i = 0; i < 16; ++i) {
            opsMappings.put(i, new HashSet<>(Arrays.asList(0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15)));
        }

        for (Operation op : ops) {
            Set<Integer> curMatching = matchingOps(op.registers, op.op, op.after);
            opsMappings.get(op.op[0]).retainAll(curMatching);
        }

        for (int k = 0; k < 16; ++k) {
            for (int i = 0; i < 16; ++i) {
                if (opsMappings.get(i).size() == 1) {
                    int toRemove = opsMappings.get(i).iterator().next();
                    for (int j = 0; j < 16; ++j) {
                        if (i != j) {
                            opsMappings.get(j).remove(toRemove);
                        }
                    }
                }
            }
        }

        TreeMap<Integer, Integer> result = new TreeMap<>();
        for (int i = 0; i < 16; ++i) {
            result.put(i, opsMappings.get(i).iterator().next());
        }
        return result;
    }

    public int[] execute(int[] register, List<int[]> ops, Map<Integer, Integer> opsMapping) {
        for (int[] op : ops) {
            Integer result = operations.get(opsMapping.get(op[0])).apply(register, op);
            register[op[3]] = result;
        }
        return register;
    }

    public int task1(List<Operation> ops) {
        int sum = 0;
        for (Operation op : ops) {
            if (matchingOps(op.registers, op.op, op.after).size() >= 3) {
                sum += 1;
            }
        }
        return sum;
    }

    public static List<Operation> readOperations(List<String> input) {
        List<Operation> result = new ArrayList<>();

        for (int lineNum = 0; lineNum < input.size(); lineNum += 4) {
            String[] before = input.get(lineNum)
                    .substring("Before: [".length(), input.get(lineNum).length() - 1)
                    .split(",\\s*");
            String[] op = input.get(lineNum + 1).split(" ");
            String[] after = input.get(lineNum + 2)
                    .substring("After:  [".length(), input.get(lineNum + 2).length() - 1)
                    .split(",\\s*");

            result.add(new Operation(
                    new int[]{Integer.parseInt(before[0]), Integer.parseInt(before[1]), Integer.parseInt(before[2]), Integer.parseInt(before[3])},
                    new int[]{Integer.parseInt(op[0]), Integer.parseInt(op[1]), Integer.parseInt(op[2]), Integer.parseInt(op[3])},
                    new int[]{Integer.parseInt(after[0]), Integer.parseInt(after[1]), Integer.parseInt(after[2]), Integer.parseInt(after[3])}
            ));
        }

        return result;
    }

    public static List<int[]> readProgram(List<String> input) {
        List<int[]> result = new ArrayList<>();
        for (String line : input) {
            String[] op = line.split(" ");
            result.add(new int[]{
                    Integer.parseInt(op[0]), Integer.parseInt(op[1]), Integer.parseInt(op[2]), Integer.parseInt(op[3])
            });
        }
        return result;
    }

    public static class Operation {
        int[] registers;
        int[] op;
        int[] after;

        public Operation(int[] registers, int[] op, int[] after) {
            this.registers = registers;
            this.op = op;
            this.after = after;
        }
    }
}
