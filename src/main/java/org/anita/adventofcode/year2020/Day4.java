package org.anita.adventofcode.year2020;

import java.util.Arrays;

public class Day4 {

    public static class PassportScanner {
        private int validCount = 0;

        private boolean hasByr;
        private boolean hasIyr;
        private boolean hasEyr;
        private boolean hasHgt;
        private boolean hasHcl;
        private boolean hasEcl;
        private boolean hasPid;

        public int readLine(String line) {
            if (line.trim().isEmpty()) {
                if (hasByr && hasIyr && hasEyr && hasHgt && hasHcl && hasEcl && hasPid) {
                    validCount += 1;
                }
                clear();
                return validCount;
            }
            String[] pairs = line.split("\\s");
            for (String pair : pairs) {
                String field = pair.split("\\:")[0];
                String value = pair.split("\\:")[1];
                switch(field) {
                    case "byr":
                        if (value.matches("^\\d{4}$")) {
                            int age = Integer.parseInt(value);
                            if (age >= 1920 && age <= 2002) {
                                hasByr = true;
                            }
                        }
                        break;
                    case "iyr":
                        if (value.matches("^\\d{4}$")) {
                            int issue = Integer.parseInt(value);
                            if (issue >= 2010 && issue <= 2020) {
                                hasIyr = true;
                            }
                        }
                        break;
                    case "eyr":
                        if (value.matches("^\\d{4}$")) {
                            int expiration = Integer.parseInt(value);
                            if (expiration >= 2020 && expiration <= 2030) {
                                hasEyr = true;
                            }
                        }
                        break;
                    case "hgt":
                        if (value.matches("^\\d{2}in$")) {
                            int height = Integer.parseInt(value.substring(0, 2));
                            if (height >= 59 && height <= 76) {
                                hasHgt = true;
                            }
                        } else if (value.matches("^\\d{3}cm$")) {
                            int height = Integer.parseInt(value.substring(0, 3));
                            if (height >= 150 && height <= 193) {
                                hasHgt = true;
                            }
                        }
                        break;
                    case "hcl":
                        if (value.matches("^\\#[a-f0-9]{6}$")) {
                            hasHcl = true;
                        }
                        break;
                    case "ecl":
                        if (value.equals("amb") || value.equals("blu") || value.equals("brn") || value.equals("gry")
                                || value.equals("grn") || value.equals("hzl") || value.equals("oth")) {
                            hasEcl = true;
                        }
                        break;
                    case "pid":
                        if (value.matches("^\\d{9}$")) {
                            hasPid = true;
                        }
                        break;
                    default:
                        break;
                }
            }
            return validCount;
        }

        public int getValidCount() {
            return validCount;
        }

        private void clear() {
            hasByr = false;
            hasIyr = false;
            hasEyr = false;
            hasHgt = false;
            hasHcl = false;
            hasEcl = false;
            hasPid = false;
        }
    }
}
