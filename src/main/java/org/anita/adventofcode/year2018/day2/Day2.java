package org.anita.adventofcode.year2018.day2;

import java.util.List;

public class Day2 {

    public int task1(List<String> ids) {
        int exactlyTwoCounter = 0;
        int exactlyThreeCounter = 0;
        int[] frequencies = new int[26];

        for (String id : ids) {
            for (char c : id.toCharArray()) {
                frequencies[c - 'a'] += 1;
            }

            boolean exactlyTwoFound = false;
            boolean exactlyThreeFound = false;
            for (int i = 0; i < 26; ++i) {
                if (frequencies[i] == 2 && !exactlyTwoFound) {
                    exactlyTwoCounter += 1;
                    exactlyTwoFound = true;
                } else if (frequencies[i] == 3 && !exactlyThreeFound) {
                    exactlyThreeCounter += 1;
                    exactlyThreeFound = true;
                }
                frequencies[i] = 0;
            }
        }

        return exactlyTwoCounter * exactlyThreeCounter;
    }

    public String task2(List<String> ids) {
        // a slightly more efficient algorithm could be (if there were much more boxes)
        // notice that: each box id has the same umber of letters
        // 1. create a list of ids with 1st, 2d, 3rd etc letter removed
        // 2. sort those lists
        // 3. go through each list checking if two neighbouring elements are not the same

        int numOfBoxes = ids.size();
        for (int i = 0; i < numOfBoxes - 1; ++i) {
            for (int j = i + 1; j < numOfBoxes; ++j) {
                String first = ids.get(i);
                String second = ids.get(j);
                int differentCharsCount = 0;
                int differentCharPosition = 0;
                for (int k = 0; k < first.length(); ++k) {
                    if (first.charAt(k) != second.charAt(k)) {
                        differentCharsCount += 1;
                        differentCharPosition = k;
                        if (differentCharsCount > 1) {
                            break;
                        }
                    }
                }
                if (differentCharsCount == 1) {
                    return first.substring(0, differentCharPosition) + first.substring(differentCharPosition + 1);
                }
            }
        }
        return "";
    }

}
