package org.anita.adventofcode.year2020;

import java.util.HashSet;
import java.util.List;

public class Day1 {

    public int task1(List<Integer> numbers) {
        // it would not work if I used 1010 twice
        HashSet<Integer> numbersSet = new HashSet<>(numbers);
        for(Integer num : numbers) {
            if (numbersSet.contains(2020 - num)) {
                return num * (2020 - num);
            }
        }
        return 0;
    }

    public int task2(List<Integer> numbers) {
        // it would not work if the third number was one of the first two
        HashSet<Integer> numbersSet = new HashSet<>(numbers);
        for (int i = 0; i < numbers.size(); ++i) {
            for (int j = i + 1; j < numbers.size(); ++j) {
                if (numbersSet.contains(2020 - numbers.get(i) - numbers.get(j))) {
                    return numbers.get(i) * numbers.get(j) * (2020 - numbers.get(i) - numbers.get(j));
                }
            }
        }
        return 0;
    }
}
