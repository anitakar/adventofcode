package org.anita.adventofcode.year2020;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Day9 {

    public static class XMASEncoder {

        public long findWeakness(List<Long> numbers) {
            boolean weaknessFound;
            Set<Long> lastNumbers = new HashSet<>(numbers.subList(0, 25));

            int i = 25;
            for (; i < numbers.size(); ++i) {
                long desiredSum = numbers.get(i);
                weaknessFound = true;
                for (Long number : lastNumbers) {
                    if (lastNumbers.contains(desiredSum - number)) {
                        weaknessFound = false;
                    }
                }
                lastNumbers.remove(numbers.get(i - 25));
                lastNumbers.add(numbers.get(i));

                if (weaknessFound) {
                    return numbers.get(i);
                }
            }
            return numbers.get(i);
        }

        public long findEncryptionWeakness(List<Long> numbers) {
            long weakness = findWeakness(numbers);
            for (int i = 0; i < numbers.size(); ++i) {
                long currentSum = numbers.get(i);
                for (int j = i + 1; j < numbers.size(); ++j) {
                    currentSum += numbers.get(j);
                    if (currentSum > weakness) {
                        break;
                    }
                    if (currentSum == weakness) {
                        long minElem = weakness + 1;
                        long maxElem = 0;
                        for (int k = i; k <= j; ++k) {
                            if (numbers.get(k) < minElem) {
                                minElem = numbers.get(k);
                            }
                            if (numbers.get(k) > maxElem) {
                                maxElem = numbers.get(k);
                            }
                        }
                        return minElem + maxElem;
                    }
                }
            }
            return 0;
        }
    }
}
