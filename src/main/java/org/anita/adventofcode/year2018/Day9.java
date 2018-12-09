package org.anita.adventofcode.year2018;

import java.util.LinkedList;
import java.util.ListIterator;

public class Day9 {

    public long task1(int numberOfPlayers, int numberOfRounds) {
        LinkedList<Integer> marbles = new LinkedList<>();
        ListIterator<Integer> currentPosition = marbles.listIterator();
        currentPosition.add(0);
        currentPosition.add(1);

        long[] scores = new long[numberOfPlayers];
        for(int round = 2; round <= numberOfRounds; ++round) {
            if (round % 23 == 0) {
                for (int i = 0; i < 7; ++i) {
                    if (!currentPosition.hasPrevious()) {
                        currentPosition = marbles.listIterator(marbles.size() - 1);
                    } else {
                        currentPosition.previous();
                    }
                }
                int removed = currentPosition.previous();
                currentPosition.remove();
                currentPosition.next();
                scores[round % numberOfPlayers] += (round + removed);
            } else {
                if (!currentPosition.hasNext()) {
                    currentPosition = marbles.listIterator();
                }
                currentPosition.next();
                currentPosition.add(round);
            }
            //System.out.println(marbles);
        }

        long maxScore = 0;
        for (int scoreIndex = 0; scoreIndex < numberOfPlayers; ++scoreIndex) {
            maxScore = Math.max(maxScore, scores[scoreIndex]);
        }
        return maxScore;
    }
}
