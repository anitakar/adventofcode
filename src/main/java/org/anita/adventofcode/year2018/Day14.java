package org.anita.adventofcode.year2018;

import java.util.ArrayList;
import java.util.ListIterator;

public class Day14 {

    public String task1(int numberOfRecipes) {
        ArrayList<Integer> scores = new ArrayList<>();
        scores.add(3);
        scores.add(7);

        int firstElfPosition = 0, secondElfPosition = 1;
        while (scores.size() < numberOfRecipes + 10) {
            int firstValue = scores.get(firstElfPosition);
            int secondValue = scores.get(secondElfPosition);
            int newRecipes = firstValue + secondValue;
            if (newRecipes >= 10) {
                scores.add(newRecipes / 10);
                scores.add(newRecipes % 10);
            } else {
                scores.add(newRecipes);
            }
            firstElfPosition = (firstElfPosition + firstValue + 1) % scores.size();
            secondElfPosition = (secondElfPosition + secondValue + 1) % scores.size();
            //System.out.println(scores);
        }


        ListIterator<Integer> reverseScoresIterator = scores.listIterator(numberOfRecipes + 10);
        StringBuilder result = new StringBuilder();
        for (int i = 0; i < 10; ++i) {
            result.append(reverseScoresIterator.previous());
        }

        return result.reverse().toString();
    }

    public int task2(String sequence) {
        int[] toFind = new int[sequence.length()];
        for (int i = 0; i < toFind.length; ++i) {
            toFind[i] = sequence.charAt(i) - '0';
        }

        ArrayList<Integer> scores = new ArrayList<>();
        scores.add(3);
        scores.add(7);

        int firstElfPosition = 0, secondElfPosition = 1;
        while (true) {
            int firstValue = scores.get(firstElfPosition);
            int secondValue = scores.get(secondElfPosition);
            int newRecipes = firstValue + secondValue;
            if (newRecipes >= 10) {
                scores.add(newRecipes / 10);
                scores.add(newRecipes % 10);
            } else {
                scores.add(newRecipes);
            }
            firstElfPosition = (firstElfPosition + firstValue + 1) % scores.size();
            secondElfPosition = (secondElfPosition + secondValue + 1) % scores.size();
            if (scores.size() >= toFind.length) {
                boolean found = true;
                for (int i = 1; i <= toFind.length; ++i) {
                    if (scores.get(scores.size() - i) != toFind[toFind.length - i]) {
                        found = false;
                        break;
                    }
                }
                if (found) {
                    return scores.size() - toFind.length;
                }
                found = true;
                for (int i = 1; i <= toFind.length; ++i) {
                    if (scores.get(scores.size() - i - 1) != toFind[toFind.length - i]) {
                        found = false;
                        break;
                    }
                }
                if (found) {
                    return scores.size() - toFind.length - 1;
                }
            }

            //System.out.println(scores);
        }
    }
}
