package org.anita.adventofcode.year2018;

public class Day21 {

    int r0, r1, ip, r3, r4, r5;

    public void program() {
        r4 = 123;

        do {
            r4 = r4 & 456;
            r4 = r4 == 72 ? 1 : 0;
            //ip = r4 + ip;
        } while (r4 == 0);
        //ip = 0;
        r4 = 0;
        r3 = r4 | 65536;
        r4 = 10283511;

        do {
            r1 = r3 & 255;
            r4 = r4 + r1;
            r4 = r4 & 16777215;
            r4 = r4 * 65899;
            r4 = r4 & 16777215;

            r1 = 256 > r3 ? 1 : 0;
        } while (r1 == 1);

        //ip = r1 + ip;
        //ip = ip + 1;
        //ip = 27;

        r1 = 0;
        r5 = r1 + 1;
        r5 = r5 * 256;

        r5 = r5 > r3 ? 1 : 0;

        if (r5 == 1) {

        } else {

        }



        ip = r5 + ip;
        ip = ip + 1;
        ip = 25;
        r1 = r1 + 1;
        ip = 17;
        r3 = r1;
        ip = 7;
        r1 = r4 == r0 ? 1 : 0;
        ip = r1 + ip;
        ip = 5;
    }

    private void l1() {

    }
}
