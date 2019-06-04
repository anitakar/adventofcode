package org.anita.adventofcode.year2015;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class Day17 {

    public static int bruteForce(List<Integer> containers, int total) {
        if (total == 0) {
            return 1;
        } else if (total < 0) {
            return 0;
        } else if (containers.size() == 0) {
            return 0;
        } else {
            return bruteForce(containers.subList(1, containers.size()), total) + bruteForce(containers.subList(1, containers.size()), total - containers.get(0));
        }
    }

    public static int bruteForceCombinations(List<Integer> containers, int total) {
        containersCount = new ArrayList<>();
        bruteForceCombinationsInner(containers, total, 0);

        int minContainers = Day17.containersCount.stream().min(Comparator.comparingInt(e -> e)).get();
        long numberOfCombinations = Day17.containersCount.stream().filter(e -> e == minContainers).count();

        return (int) numberOfCombinations;
    }

    private static List<Integer> containersCount = new ArrayList<>();

    public static void bruteForceCombinationsInner(List<Integer> containers, int total, int containersUsed) {
        if (total == 0) {
            containersCount.add(containersUsed);
        } else if (total < 0) {
            return;
        } else if (containers.size() == 0) {
            return;
        } else {
            bruteForceCombinationsInner(containers.subList(1, containers.size()), total, containersUsed);
            bruteForceCombinationsInner(containers.subList(1, containers.size()), total - containers.get(0), containersUsed + 1);
        }
    }
}
