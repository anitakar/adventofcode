package org.anita.adventofcode.year2019;

public class Day2 {

    public int task1(int[] registers) {
        int currentPosition = 0;
        int code = registers[currentPosition];

        while (code != 99) {
            int op1 = registers[registers[currentPosition + 1]];
            int op2 = registers[registers[currentPosition + 2]];
            int result = 0;
            if (code == 1) {
                result = op1 + op2;
            } else if (code == 2) {
                result = op1 * op2;
            }
            registers[registers[currentPosition + 3]] = result;
            currentPosition += 4;
            code = registers[currentPosition];
        }

        return registers[0];
    }
}
