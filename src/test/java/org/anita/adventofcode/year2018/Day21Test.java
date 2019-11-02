package org.anita.adventofcode.year2018;

import org.anita.adventofcode.util.FileUtils;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

public class Day21Test {

    @Test
    public void programPrint() throws IOException {
        InputStream inputStream = getClass().getResourceAsStream("/day21.txt");
        List<String> input = FileUtils.readStringsLineByLine(inputStream);
        List<Day19.Instruction> program = Day19.readProgram(input.subList(1, input.size()));

        System.out.println(new Day19().print(2, program));
    }

    @Test
    public void task1Print() throws IOException {
        InputStream inputStream = getClass().getResourceAsStream("/day21.txt");
        List<String> input = FileUtils.readStringsLineByLine(inputStream);
        List<Day19.Instruction> program = Day19.readProgram(input.subList(1, input.size()));

//        new Day19().executeAndPrint(new int[] {0, 0, 0, 0, 0, 0}, 2, program, 20);
//        System.out.println("====================================================================");
//        new Day19().executeAndPrint(new int[] {0, 7782717 / 256, 18, 65536, 7782717, 0}, 2, program, 20);
//        System.out.println("====================================================================");
//        new Day19().executeAndPrint(new int[] {0, 6492970 / 256, 18, 30401, 6492970, 0}, 2, program, 20);
//        System.out.println("====================================================================");
//        new Day19().executeAndPrint(new int[] {0, 12142463 / 256, 18, 25363, 12142463, 0}, 2, program, 20);
//        System.out.println("====================================================================");
//        new Day19().executeAndPrint(new int[] {0, 8308162 / 256, 18, 47431, 8308162, 0}, 2, program, 20);
//        System.out.println("====================================================================");
//        new Day19().executeAndPrint(new int[] {0, 4882797 / 256, 18, 32453, 4882797, 0}, 2, program, 20);
//        System.out.println("====================================================================");
//        new Day19().executeAndPrint(new int[] {0, 9714810 / 256, 18, 19073, 9714810, 0}, 2, program, 20);
//        System.out.println("====================================================================");
//        new Day19().executeAndPrint(new int[] {0, 15210002 / 256, 18, 37948, 15210002, 0}, 2, program, 20);
//        System.out.println("====================================================================");
//        new Day19().executeAndPrint(new int[] {0, 4156088 / 256, 18, 59414, 4156088, 0}, 2, program, 20);
//        System.out.println("====================================================================");
          int[] register = new Day19().executeAndPrint(new int[]{0, 0, 0, 0, 0, 0}, 2, program, 20);

            for (int i = 0; i < 2; ++i) {
                System.out.println("============================" + i + "==================================");
                //256*R[1] > R[3]
                //==> [0, R[1], 8, R[1], R[4], 1]
                reassignRegisters1(register);
                register = new Day19().executeAndPrint(register, 2, program, 20);
//                System.out.print(register[3] + " , ");
//                System.out.print(register[4] + " , ");
//                System.out.println(register[5]);
            }
//        new Day19().executeAndPrint(new int[]{0, register[4] / 256, 18, register[3], register[4], 0}, 2, program, 1000);
        // 28: R[1] := R[4] == R[0]		// [0, 1, 28, 1, 13443200, 1]
    }

    @Test
    public void task2Print() throws IOException {
        InputStream inputStream = getClass().getResourceAsStream("/day21.txt");
        List<String> input = FileUtils.readStringsLineByLine(inputStream);
        List<Day19.Instruction> program = Day19.readProgram(input.subList(1, input.size()));

        int[] register = new Day19().execute(new int[]{0, 0, 0, 0, 0, 0}, 2, program, 20);
        //System.out.println(Arrays.toString(register));
        reassignRegisters1(register);
        register = new Day19().execute(register, 2, program, 20);
        //System.out.println(Arrays.toString(register));
        reassignRegisters1(register);
        register = new Day19().execute(register, 2, program, 30);
        //System.out.println(Arrays.toString(register));
        for (int i = 0; i < 300000; ++i) {
            //System.out.println("============================" + i + "==================================");
            reassignRegisters2(register);
            register = new Day19().execute(register, 2, program, 30);
            //System.out.println(Arrays.toString(register));
            System.out.println(register[3]);
            //System.out.println("R[4]:\t\t" + register[4] + ", binary:\t\t" + Integer.toBinaryString(register[4]));
        }
    }

    private void reassignRegisters1(int[] register) {
        register[1] = register[3] / 256;
        register[0] = 0;
        register[2] = 8;
        register[3] = register[1];
        register[5] = 1;
    }

    private void reassignRegisters2(int[] register) {
        register[1] = register[3] / 256;
        register[0] = 0;
        register[2] = 6;
        register[3] = register[1];
        register[5] = 1;
    }

}
