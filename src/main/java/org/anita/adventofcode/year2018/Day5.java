package org.anita.adventofcode.year2018;

public class Day5 {

    public int task1(String polymer) {
        int prevLen = polymer.length();
        String shortened = shortenOnce(polymer);
        int curLen = shortened.length();
        while (prevLen != curLen) {
            prevLen = curLen;
            shortened = shortenOnce(shortened);
            curLen = shortened.length();
        }

        return shortened.length();
    }

    private String shortenOnce(String polymer) {
        char[] polymers = polymer.toCharArray();
        for (int i = 0; i < polymers.length - 1; ++i) {
            if (Math.abs(polymers[i] - polymers[i + 1]) == 32) {
                polymers[i] = ' ';
                polymers[i+1] = ' ';
            }
        }
        return new String(polymers).replaceAll(" ", "");
    }

    public int task2(String polymer) {
        int minLen = polymer.length();
        for (int i = 0; i < 26; ++ i) {
            String first = "" + ((char)('A' + i));
            String second = "" + ((char)('a' + i ));
            String newPolymer = polymer.replaceAll(first, "").replaceAll(second, "");
            int newLen = task1(newPolymer);
            if (newLen < minLen) {
                minLen = newLen;
            }
        }
        return minLen;
    }


}
