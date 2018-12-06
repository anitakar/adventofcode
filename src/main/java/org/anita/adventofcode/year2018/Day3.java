package org.anita.adventofcode.year2018;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Day3 {

    public int task1(List<Claim> claims) {
        int maxHeight = 0, maxWidth = 0;
        for (Claim claim : claims) {
            maxHeight = Math.max(maxHeight, claim.top + claim.height);
            maxWidth = Math.max(maxWidth, claim.left + claim.width);
        }

        int[][] fabric = new int[maxHeight][maxWidth];
        int overlapping = 0;
        for (Claim claim : claims) {
            for (int i = claim.top; i < claim.top + claim.height; ++ i) {
                for (int j = claim.left; j < claim.left + claim.width; ++ j) {
                    fabric[i][j] += 1;
                    if (fabric[i][j] == 2) {
                        overlapping += 1;
                    }
                }
            }
        }
        return overlapping;
    }

    public int task2(List<Claim> claims) {
        int maxHeight = 0, maxWidth = 0;
        for (Claim claim : claims) {
            maxHeight = Math.max(maxHeight, claim.top + claim.height);
            maxWidth = Math.max(maxWidth, claim.left + claim.width);
        }

        int[][] fabric = new int[maxHeight][maxWidth];
        for (Claim claim : claims) {
            for (int i = claim.top; i < claim.top + claim.height; ++ i) {
                for (int j = claim.left; j < claim.left + claim.width; ++ j) {
                    fabric[i][j] += 1;
                }
            }
        }

        for (Claim claim : claims) {
            boolean overlapping = false;
            for (int i = claim.top; i < claim.top + claim.height; ++ i) {
                for (int j = claim.left; j < claim.left + claim.width; ++ j) {
                    if (fabric[i][j] > 1) {
                        overlapping = true;
                    }
                }
            }
            if (!overlapping) {
                return claim.id;
            }
        }

        return 0;
    }

    public static class Claim {
        public int id;
        public int left;
        public int top;
        public int width;
        public int height;

        public Claim(int id, int left, int top, int width, int height) {
            this.id = id;
            this.left = left;
            this.top = top;
            this.width = width;
            this.height = height;
        }
    }

    public static String claimRegex = "^#(\\d+) @ (\\d+),(\\d+): (\\d+)x(\\d+)$";
    private Pattern claimPattern = Pattern.compile(claimRegex);

    public Claim parseClaim(String line) {
        Matcher matcher = claimPattern.matcher(line);
        if (matcher.find()) {
            return new Claim(
                    Integer.parseInt(matcher.group(1)),
                    Integer.parseInt(matcher.group(2)),
                    Integer.parseInt(matcher.group(3)),
                    Integer.parseInt(matcher.group(4)),
                    Integer.parseInt(matcher.group(5))
            );
        }
        return null;
    }


}
