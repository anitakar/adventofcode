package org.anita.adventofcode.year2020;

import java.util.Comparator;
import java.util.List;

public class Day10 {

    public static class JoltAdapterSequence {
         public int indicator(List<Integer> jolts) {
             jolts.add(0);
             jolts.add(jolts.stream().max(Comparator.naturalOrder()).get() + 3);
             jolts.sort(Comparator.comparingInt(i -> i));
             int num1JoltDiffs = 0;
             int num3JoltDiffs = 0;
             for (int i = 1; i < jolts.size(); ++i) {
                 int diff = jolts.get(i) - jolts.get(i - 1);
                 if (diff == 1) {
                     num1JoltDiffs += 1;
                 } else if (diff == 3) {
                     num3JoltDiffs += 1;
                 } else if (diff > 3) {
                     throw new RuntimeException();
                 }
             }
             return num1JoltDiffs * num3JoltDiffs;
         }
    }
}
