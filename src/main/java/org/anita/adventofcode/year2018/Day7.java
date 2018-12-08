package org.anita.adventofcode.year2018;

import java.util.LinkedList;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Day7 {

    Node[] steps = new Node[26];

    public Day7() {
        for (int i = 0; i < 26; ++i) {
            steps[i] = new Node((char)('A' + i));
        }
    }

    public String task1() {
        String result = "";
        for (int i = 0; i < 26; ++i) {
            for (int j = 0; j < 26; ++j) {
                if (steps[j].prereq.isEmpty() && steps[j].available) {
                    result += steps[j].name;
                    steps[j].available = false;
                    for (int k = 0; k < 26; ++k) {
                        steps[k].prereq.remove(steps[j].name);
                    }
                    break;
                }
            }
        }
        return result;
    }

    public int task2(int stepDur, int numOfWorkers) {
        int timePassed = 0;
        List<Integer> timeToFinishPerWorker = new LinkedList<>();
        List<Character> processingStepPerWorker = new LinkedList<>();
        for (int i = 0; i < 26; ++i) {
            for (int j = 0; j < 26; ++j) {
                if (steps[j].prereq.isEmpty() && steps[j].available && timeToFinishPerWorker.size() < numOfWorkers) {
                    steps[j].available = false;
                    timeToFinishPerWorker.add(steps[j].name - 'A' + 1 + stepDur);
                    processingStepPerWorker.add(steps[j].name);
                }
            }

            if (timeToFinishPerWorker.isEmpty()) {
                continue;
            }

            int minTime = timeToFinishPerWorker.stream().mapToInt(z -> z).min().getAsInt();
            timePassed += minTime;
            int indexOfFinished = -1;
            for (int m = 0; m < timeToFinishPerWorker.size(); ++m) {
                int newValue = timeToFinishPerWorker.get(m) - minTime;
                timeToFinishPerWorker.set(m, newValue);
                if (newValue == 0) {
                    indexOfFinished = m;
                }
            }
            ((LinkedList<Integer>) timeToFinishPerWorker).remove(indexOfFinished);
            char valueOfFinished = ((LinkedList<Character>) processingStepPerWorker).get(indexOfFinished);
            ((LinkedList<Character>) processingStepPerWorker).remove(indexOfFinished);

            for (int k = 0; k < 26; ++k) {
                steps[k].prereq.remove(valueOfFinished);
            }
        }
        return timePassed;
    }

    public static class Node {
        char name;
        Set<Character> prereq = new TreeSet<>();
        boolean available = false;

        public Node(char name) {
            this.name = name;
        }
    }

    public static String dependencyRegex = "^Step (\\w) must be finished before step (\\w) can begin.$";
    private Pattern dependencyPattern = Pattern.compile(dependencyRegex);

    public void parseDependency(String line) {
        Matcher matcher = dependencyPattern.matcher(line);
        if (matcher.find()) {
            char dependency = matcher.group(1).toCharArray()[0];
            char step = matcher.group(2).toCharArray()[0];
            steps[step - 'A'].prereq.add(dependency);
            steps[step - 'A'].available = true;
            steps[dependency - 'A'].available = true;
        }
    }
}
