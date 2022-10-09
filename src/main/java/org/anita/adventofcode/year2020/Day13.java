package org.anita.adventofcode.year2020;

public class Day13 {

    public int task1(int time, int[] ids) {
        int timeToWait = Integer.MAX_VALUE;
        int busId = 0;
        for (int i = 0; i < ids.length; ++i) {
            int last = (time / ids[i]) * ids[i];
            int curTimeToWait = last + ids[i] - time;
            if (curTimeToWait < timeToWait) {
                timeToWait = curTimeToWait;
                busId = ids[i];
            }
        }
        return timeToWait * busId;
    }

    public int task2(int[] ids) {

    }
}
