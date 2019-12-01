package org.anita.adventofcode.year2019;

import java.util.List;

public class Day1 {

    public int task1(List<Integer> masses) {
        return masses.stream().mapToInt(m -> mass(m)).sum();
    }

    public int task2(List<Integer> masses) {
        return masses.stream().mapToInt(m -> massPlusFuel(m)).sum();
    }

    private int mass(int mass) {
        return mass / 3 - 2;
    }

    private int massPlusFuel(int mass) {
        int total = mass(mass);

        int currentMass = total;
        while (currentMass > 0) {
            currentMass = mass(currentMass);
            if (currentMass > 0) {
                total += currentMass;
            }
        }

        return total;
    }
}
