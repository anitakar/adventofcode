package org.anita.adventofcode.year2015;

public class Day10 {

    public static String lookAndSay(String seq) {
        StringBuilder output = new StringBuilder();
        int currCount = 0;
        char curr = seq.charAt(0);
        for (char next : seq.toCharArray()) {
            if (next == curr) {
                currCount += 1;
            } else {
                output.append(currCount).append(curr);
                curr = next;
                currCount = 1;
            }
        }
        output.append(currCount).append(curr);
        return output.toString();
    }
}
