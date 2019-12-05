package org.anita.adventofcode.year2019;

public class Day4 {

    public int task1() {
        int total = 0;
        for (int i = 172930; i < 683082; ++i) {
            int num = i;
            int prevDigit = 10;
            boolean monotonic = true;
            boolean containsRepetition = false;
            while (num > 0) {
                int digit = num % 10;
                if (digit == prevDigit) {
                    containsRepetition = true;
                }
                if (digit > prevDigit) {
                    monotonic = false;
                }
                prevDigit = digit;
                num = num / 10;
            }
            if (monotonic && containsRepetition) {
                total += 1;
            }
        }
        return total;
    }

    public int task2(int start, int end) {
        int total = 0;
        for (int i = start; i <= end; ++i) {
            int num = i;
            boolean monotonic = true;
            int prevDigit = 10;
            int prevPrevDigit = 10;
            boolean containsRepetition = false;
            while (num > 0) {
                int digit = num % 10;
                if (digit > prevDigit) {
                    monotonic = false;
                }
                if (digit == prevDigit && ((num / 10) % 10) != digit && prevPrevDigit != digit) {
                    containsRepetition = true;
                }
                prevPrevDigit = prevDigit;
                prevDigit = digit;
                num = num / 10;
            }
            if (monotonic && containsRepetition) {
                total += 1;
            }
        }
        return total;
    }
}
