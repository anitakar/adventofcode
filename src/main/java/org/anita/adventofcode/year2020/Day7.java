package org.anita.adventofcode.year2020;


import java.util.*;
import org.anita.adventofcode.util.Pair;

public class Day7 {

    public Set<String> task1(Map<String, Set<Pair<String, Integer>>> rules) {
        Queue<String> toCheck = new ArrayDeque<>();
        toCheck.add("shiny gold");

        Set<String> checked = new HashSet<>();

        Set<String> answers = new HashSet<>();

        while (!toCheck.isEmpty()) {
            String toCheckBag = toCheck.poll();
            for (Map.Entry<String, Set<Pair<String, Integer>>> entry : rules.entrySet()) {

                if (entry.getValue().stream().map(Pair::getKey).anyMatch(toCheckBag::equals)) {
                    answers.add(entry.getKey());
                    if (!checked.contains(entry.getKey())) {
                        toCheck.add(entry.getKey());
                    }
                }
            }
            checked.add(toCheckBag);
        }

        return answers;
    }

    public int task2(Map<String, Set<Pair<String, Integer>>> rules) {
        final Queue<Pair<String, Integer>> contents = new ArrayDeque<>(rules.get("shiny gold"));
        int bagsInside = 0;

        while (!contents.isEmpty()) {
            final Pair<String, Integer> inside = contents.poll();
            bagsInside += inside.getValue();
            final Set<Pair<String, Integer>> pairs = rules.get(inside.getKey());
            if (pairs != null) {
                for (Pair<String, Integer> pair : pairs) {
                    contents.offer(new Pair<>(pair.getKey(), pair.getValue() * inside.getValue()));
                }
            }
        }

        return bagsInside;
    }

    public static class BagParser {
        private Map<String, Set<Pair<String, Integer>>> result = new HashMap<>();

        public Pair<String, Set<Pair<String, Integer>>> parse(String line) {
            final String[] splitted = line.split(" bags contain ");
            String mainBag = splitted[0];
            String[] inside = splitted[1].split(",");
            Set<Pair<String, Integer>> insideBags = new HashSet<>();
            for (String bag : inside) {
                String insideBag = bag.replaceAll("bags", "")
                        .replaceAll("bag", "")
                        .replaceAll(",", "")
                        .replaceAll("\\.", "")
                        .replaceAll("\\d+", "")
                        .trim();
                String numberString = bag.replaceAll("[^0-9]", "");
                int numberOfBags = 0;
                if (!numberString.isEmpty()) {
                    numberOfBags = Integer.parseInt(numberString);
                }
                insideBags.add(new Pair<>(insideBag, numberOfBags));
            }
            result.put(mainBag, insideBags);
            return new Pair<>(mainBag, insideBags);
        }

        public Map<String, Set<Pair<String, Integer>>> getResult() {
            return result;
        }
    }
}
