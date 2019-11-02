package org.anita.adventofcode.year2018;

import java.util.HashSet;
import java.util.Set;

public class Day21 {

    private static int r0, r1, r3, r4, r5;
    private static Set<Integer> seen = new HashSet<>();
    private static int lastr4 = 0;

    public static void main(String[] args) {
        r4 = 123;
        l1();
        r4 = 0;

        while (true) {
            l6:
            r3 = r4 | 65536;
            r4 = 10283511;

            l8:
            r1 = r3 & 255;
            r4 = r4 + r1;
            r4 = r4 & 16777215;
            r4 = r4 * 65899;
            r4 = r4 & 16777215;
            r1 = 256 > r3 ? 1 : 0;
            while (r1 == 0) {
                r1 = 0;

                l18:
                do {
                    r5 = r1 + 1;
                    r5 = r5 * 256;
                    r5 = r5 > r3 ? 1 : 0;
                    r1 = r1 + 1;
                } while (r5 == 0);
                r1 = r1 - 1;

                l26:
                r3 = r1;

                l8:
                r1 = r3 & 255;
                r4 = r4 + r1;
                r4 = r4 & 16777215;
                r4 = r4 * 65899;
                r4 = r4 & 16777215;
                r1 = 256 > r3 ? 1 : 0;

            }

            l28:
            if (lastr4 == 0) {
                System.out.println("Part1: " + r4);
            }
            if (!seen.contains(r4)) {
                seen.add(r4);
                lastr4 = r4;
            } else {
                System.out.println("Part2: " + lastr4);
                return;
            }
            r1 = r4 == r0 ? 1 : 0;
        }

        //f();
    }

    private static void f() {
        r4 = 123;
        l1();
        r4 = 0;
        l6();
    }

    private static void l1() {
        do {
            r4 = r4 & 456;
            r4 = r4 == 72 ? 1 : 0;
        } while (r4 == 0);
    }

    private static void l6() {
        r3 = r4 | 65536;
        r4 = 10283511;
        l8();
    }

    private static void l8() {
        r1 = r3 & 255;
        r4 = r4 + r1;
        r4 = r4 & 16777215;
        r4 = r4 * 65899;
        r4 = r4 & 16777215;
        r1 = 256 > r3 ? 1 : 0;
        if (r1 == 1) {
            l28();
        } else {
            r1 = 0;
            l18();
        }
    }

    private static void l28() {
        if (lastr4 == 0) {
            System.out.println("Part1: " + r4);
        }
        if (!seen.contains(r4)) {
            seen.add(r4);
            lastr4 = r4;
        } else {
            System.out.println("Part2: " + lastr4);
            return;
        }
        r1 = r4 == r0 ? 1 : 0;
        if (r1 == 0) l6();
    }

    private static void l18() {
        do {
            r5 = r1 + 1;
            r5 = r5 * 256;
            r5 = r5 > r3 ? 1 : 0;
            r1 = r1 + 1;
        } while (r5 == 0);
        r1 = r1 - 1;
        l26();
    }

    private static void l26() {
        r3 = r1;
        l8();
    }
}
