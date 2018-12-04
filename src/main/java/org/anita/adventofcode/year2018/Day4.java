package org.anita.adventofcode.year2018;

import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Day4 {

    public static String startsShiftRegex = "\\[\\d\\d\\d\\d-\\d\\d-\\d\\d \\d\\d:\\d\\d\\] Guard #(\\d+) begins shift";
    public static String fallsAsleepRegex = "\\[\\d\\d\\d\\d-\\d\\d-\\d\\d \\d\\d:(\\d\\d)\\] falls asleep";
    public static String wakesUpRegex = "\\[\\d\\d\\d\\d-\\d\\d-\\d\\d \\d\\d:(\\d\\d)\\] wakes up";

    private Pattern startsShiftPattern = Pattern.compile(startsShiftRegex);
    private Pattern fallsAsleepPattern = Pattern.compile(fallsAsleepRegex);
    private Pattern wakesUpPattern = Pattern.compile(wakesUpRegex);

    public long task1(List<String> lines) {
        lines.sort(Comparator.naturalOrder());

        Map<Integer, Long> totalSleepTimePerGuard = new HashMap<>();
        Map<Integer, ArrayList<Long>> minutesAsleepPerGuard = new HashMap<>();

        Integer currentGuard = 0;
        int napStart = -1;
        int napEnd = -1;
        for (String line : lines) {
            Matcher startsShift = startsShiftPattern.matcher(line);
            if (startsShift.find()) {
                if (napStart != -1 && napEnd == -1) {
                    napEnd = 60;
                    updateWithNap(totalSleepTimePerGuard, minutesAsleepPerGuard, currentGuard, napStart, napEnd);
                }

                currentGuard = Integer.parseInt(startsShift.group(1));
                napStart = -1;
                napEnd = -1;
                continue;
            }

            Matcher fallsAsleep = fallsAsleepPattern.matcher(line);
            if (fallsAsleep.find()) {
                napStart = Integer.parseInt(fallsAsleep.group(1));
                continue;
            }

            Matcher wakesUp = wakesUpPattern.matcher(line);
            if (wakesUp.find()) {
                napEnd = Integer.parseInt(wakesUp.group(1));
                updateWithNap(totalSleepTimePerGuard, minutesAsleepPerGuard, currentGuard, napStart, napEnd);
                continue;
            }
        }

        int maxGuardId = 0;
        long maxGuardTime = 0;
        for(Map.Entry<Integer, Long> entry : totalSleepTimePerGuard.entrySet()) {
            if (entry.getValue() > maxGuardTime) {
                maxGuardId = entry.getKey();
                maxGuardTime = entry.getValue();
            }
        }

        ArrayList<Long> minutes = minutesAsleepPerGuard.get(maxGuardId);
        long maxMinutesAsleep = 0;
        long maxMinute = 0;
        for (int i = 0; i < 60; ++i) {
            if (minutes.get(i) > maxMinutesAsleep) {
                maxMinute = i;
                maxMinutesAsleep = minutes.get(i);
            }
        }

        return maxGuardId * maxMinute;
    }

    public long task2(List<String> lines) {
        lines.sort(Comparator.naturalOrder());

        Map<Integer, Long> totalSleepTimePerGuard = new HashMap<>();
        Map<Integer, ArrayList<Long>> minutesAsleepPerGuard = new HashMap<>();

        Integer currentGuard = 0;
        int napStart = -1;
        int napEnd = -1;
        for (String line : lines) {
            Matcher startsShift = startsShiftPattern.matcher(line);
            if (startsShift.find()) {
                if (napStart != -1 && napEnd == -1) {
                    napEnd = 60;
                    updateWithNap(totalSleepTimePerGuard, minutesAsleepPerGuard, currentGuard, napStart, napEnd);
                }

                currentGuard = Integer.parseInt(startsShift.group(1));
                napStart = -1;
                napEnd = -1;
                continue;
            }

            Matcher fallsAsleep = fallsAsleepPattern.matcher(line);
            if (fallsAsleep.find()) {
                napStart = Integer.parseInt(fallsAsleep.group(1));
                continue;
            }

            Matcher wakesUp = wakesUpPattern.matcher(line);
            if (wakesUp.find()) {
                napEnd = Integer.parseInt(wakesUp.group(1));
                updateWithNap(totalSleepTimePerGuard, minutesAsleepPerGuard, currentGuard, napStart, napEnd);
                continue;
            }
        }

        int maxGuardId = 0;
        long maxMinute = 0;
        long maxMinutesAsleep = 0;
        for(Map.Entry<Integer, ArrayList<Long>> entry : minutesAsleepPerGuard.entrySet()) {
            for (int i = 0; i < 60; ++i) {
                if (entry.getValue().get(i) > maxMinutesAsleep) {
                    maxMinute = i;
                    maxMinutesAsleep = entry.getValue().get(i);
                    maxGuardId = entry.getKey();
                }
            }
        }

        return maxGuardId * maxMinute;
    }

    private void updateWithNap(Map<Integer, Long> totalSleepTimePerGuard, Map<Integer, ArrayList<Long>> minutesAsleepPerGuard, Integer currentGuard, int napStart, int napEnd) {
        int napTime = napEnd - napStart;
        totalSleepTimePerGuard.compute(currentGuard, (gid, acc) -> Optional.ofNullable(acc).orElse(0L) + napTime);
        minutesAsleepPerGuard.putIfAbsent(currentGuard, new ArrayList(Collections.nCopies(60, 0L)));
        for (int i = napStart; i < napEnd; ++i) {
            minutesAsleepPerGuard.get(currentGuard).set(i, minutesAsleepPerGuard.get(currentGuard).get(i) + 1);
        }
    }
}
