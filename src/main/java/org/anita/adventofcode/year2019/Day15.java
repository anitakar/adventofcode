package org.anita.adventofcode.year2019;

import java.util.Iterator;

public class Day15 {

    public int task1() {
        return 0;
    }

    public static class Robot implements Iterator<Long>, IntCodeComputer.OutputListener {

        @Override
        public boolean hasNext() {
            return true;
        }

        @Override
        public Long next() {
            return 1L;
        }

        @Override
        public void onOutput(long value) {
            System.out.println(value);
        }
    }
}
