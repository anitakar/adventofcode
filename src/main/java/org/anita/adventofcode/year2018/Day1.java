package org.anita.adventofcode.year2018;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Day1 {

    public int task1(List<Integer> frequencies) {
        return frequencies.stream().mapToInt(i -> i).sum();
    }

    public int task2(List<Integer> frequencies) {
        Set<Integer> found = new HashSet<>();

        int sum = 0;
        int nextIndex = 0;
        int lastIndex = frequencies.size() - 1;
        while (!found.contains(sum)) {
            found.add(sum);
            sum += frequencies.get(nextIndex);
            nextIndex += 1;
            if (nextIndex > lastIndex) {
                nextIndex = 0;
            }
        }
        return sum;
    }
}
