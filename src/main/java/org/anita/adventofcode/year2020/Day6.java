package org.anita.adventofcode.year2020;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

public class Day6 {
    public static class AnswersScanner {
        private boolean all = false;
        private boolean firstLine = true;

        private Set<Integer> chosenAnswers = new HashSet<>();
        private int totalAnswers = 0;

        public AnswersScanner() {
            this(false);
        }

        public AnswersScanner(boolean all) {
            this.all = all;
        }

        public int readLine(String line) {
            if (line.trim().isEmpty()) {
                totalAnswers += chosenAnswers.size();
                chosenAnswers.clear();
                firstLine = true;
                return totalAnswers;
            }

            if (all) {
                if (firstLine) {
                    chosenAnswers.addAll(line.chars().boxed().collect(Collectors.toSet()));
                    firstLine = false;
                } else {
                    chosenAnswers.retainAll(line.chars().boxed().collect(Collectors.toSet()));
                }
            } else {
                chosenAnswers.addAll(line.chars().boxed().collect(Collectors.toSet()));
            }

            return totalAnswers;
        }

        public int getTotalAnswers() {
            return totalAnswers;
        }
    }
}
