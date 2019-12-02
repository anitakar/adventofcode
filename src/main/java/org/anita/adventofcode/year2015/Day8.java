package org.anita.adventofcode.year2015;

import java.util.List;

public class Day8 {

    public int task1(List<String> strings) {
        return strings.stream().mapToInt(s -> decodeDiff(s)).sum();
    }

    public int task2(List<String> strings) {
        return strings.stream().mapToInt(s -> encodeDiff(s)).sum();
    }

    private int decodeDiff(String input) {
        int start = input.length();
        int after = input.substring(1, input.length() - 1).replaceAll("\\\\\"", "Z").replaceAll("\\\\\\\\", "Z").replaceAll("\\\\x([a-f0-9]){2}", "Z").length();
        return start - after;
    }

    private int encodeDiff(String input) {
        int start = input.length();
        int after = input.replaceAll("\\\\", "\\\\\\\\").replaceAll("\"", "\\\\\"").length();
        return after - start;
    }
}
