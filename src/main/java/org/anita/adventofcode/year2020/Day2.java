package org.anita.adventofcode.year2020;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Day2 {
    private static String rulePasswordPatternString = "^(\\d+)\\-(\\d+)\\s+(\\w+)\\:\\s+(\\w+)$";
    private Pattern rulePasswordPattern = Pattern.compile(rulePasswordPatternString);

    public boolean task1(String line) {
        Matcher matcher = rulePasswordPattern.matcher(line);
        if (matcher.find()) {
            int min = Integer.parseInt(matcher.group(1));
            int max = Integer.parseInt(matcher.group(2));
            char letter = matcher.group(3).charAt(0);
            String password = matcher.group(4);

            int charCount = 0;
            for (char c : password.toCharArray()) {
                if (c == letter) {
                    ++charCount;
                }
            }
            if (charCount >= min && charCount <= max) {
                return true;
            }
        }
        return false;
    }

    public boolean task2(String line) {
        Matcher matcher = rulePasswordPattern.matcher(line);
        if (matcher.find()) {
            int firstPosition = Integer.parseInt(matcher.group(1));
            int secondPosition = Integer.parseInt(matcher.group(2));
            char letter = matcher.group(3).charAt(0);
            String password = matcher.group(4);

            return password.charAt(firstPosition - 1) == letter ^ password.charAt(secondPosition - 1) == letter;
        }
        return false;
    }
}
