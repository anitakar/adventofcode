package org.anita.adventofcode.year2015;

import java.security.MessageDigest;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Stream;

public class Day1 {

    private MessageDigest md;

    public int floors(String text) {
        int openBracketCount = 0, closedBracketCount = 0;
        for (char c : text.toCharArray()) {
            if (c == '(') {
                openBracketCount++;
            } else {
                closedBracketCount++;
            }
        }
        return openBracketCount - closedBracketCount;
    }

    public int basementPosition(String text) {
        int currentFloor = 0;
        int position = 0;
        for (char c : text.toCharArray()) {
            position++;
            if (c == '(') {
                currentFloor++;
            } else {
                currentFloor--;
            }
            if (currentFloor < 0) {
                return position;
            }
        }
        return -1;
    }
}