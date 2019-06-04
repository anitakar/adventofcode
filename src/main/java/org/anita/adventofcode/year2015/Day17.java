package org.anita.adventofcode.year2015;

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
}
