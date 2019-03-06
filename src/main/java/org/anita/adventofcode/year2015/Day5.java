package org.anita.adventofcode.year2015;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Day5 {

    public Day5() {
    }

    public boolean isNice(String text) {
        for (String forbidden : new String[]{"ab", "cd", "pq", "xy"}) {
            if (text.contains(forbidden)) {
                return false;
            }
        }

        int numberOfVowels = 0;
        char[] chars = text.toCharArray();
        for (char c : chars) {
            if (c == 'a' || c == 'e' || c == 'i' || c == 'o' || c == 'u') {
                numberOfVowels++;
            }
        }

        if (numberOfVowels < 3) {
            return false;
        }

        boolean twoInARow = false;
        for (int i = 0; i < chars.length - 1; ++i) {
            if (chars[i] == chars[i + 1]) {
                twoInARow = true;
                break;
            }
        }

        return twoInARow;
    }

    public boolean isNice2(String text) {
        char[] chars = text.toCharArray();
        boolean twoWithOneInBetween = false;
        for (int i = 0; i < chars.length - 2; ++i) {
            if (chars[i] == chars[i + 2]) {
                twoWithOneInBetween = true;
                break;
            }
        }

        if (!twoWithOneInBetween) {
            return false;
        }

        HashMap<String, List<Integer>> substrings = new HashMap<>();
        for (int i = 0; i < chars.length - 1; ++i) {
            int finalI = i;
            substrings.compute("" + chars[i] + chars[i + 1], (key, occurences) -> {
                if (occurences == null) {
                    occurences = new ArrayList<>();
                }
                occurences.add(finalI);
                return occurences;
            });
        }

        return substrings.values().stream().anyMatch(occur -> occur.size() > 2 || (occur.size() == 2 && occur.get(0) + 1 != occur.get(1)));
    }
}