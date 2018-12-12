package org.anita.adventofcode.year2018;

public class Day12 {

    public int task1(String initialState, String[] patterns, long numOfIterations) {
        long startTime = System.currentTimeMillis();
        int indexOf0thElement = 0;
        for (long i = 0; i < numOfIterations; ++i) {
            int firstPlant = initialState.indexOf('#');
            int lastPlant = initialState.lastIndexOf('#');
            indexOf0thElement = indexOf0thElement - firstPlant + 4;
            initialState = "...." + initialState.substring(firstPlant, lastPlant + 1) + "....";
            char[] after = new char[initialState.length()];
            for (int j = 0; j < after.length; ++j) {
                after[j] = '.';
            }
            for (int j = 2; j < initialState.length() - 2; ++j) {
                for (String pattern : patterns) {
                    if (pattern.equals(initialState.substring(j - 2, j + 3))) {
                        after[j] = '#';
                    }
                }
            }
            initialState = new String(after);
        }
        int result = 0;
        for (int i = 0; i < initialState.length(); ++i) {
            if (initialState.charAt(i) == '#') {
                result += (i - indexOf0thElement);
            }
        }
        return result;
    }
}
