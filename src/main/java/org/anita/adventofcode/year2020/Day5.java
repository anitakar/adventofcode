package org.anita.adventofcode.year2020;

import javafx.util.Pair;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class Day5 {

    public int task1(List<Pair<Integer, Integer>> seats) {
        return seats.stream().map(seat -> seatId(seat.getKey(), seat.getValue())).max(Comparator.naturalOrder()).get();
    }

    public int task2(List<Pair<Integer, Integer>> seats) {
        int minSeat = seats.stream().map(seat -> seatId(seat.getKey(), seat.getValue())).min(Comparator.naturalOrder()).get();
        int maxSeat = seats.stream().map(seat -> seatId(seat.getKey(), seat.getValue())).max(Comparator.naturalOrder()).get();

        List<Integer> sortedSeats = seats.stream().map(seat -> seatId(seat.getKey(), seat.getValue())).sorted().collect(Collectors.toList());
        for (int i = minSeat; i < maxSeat; ++i) {
            if (sortedSeats.get(i - minSeat) != i) {
                return i;
            }
        }

        return 0;
    }

    public static Pair<Integer, Integer> readSeat(String seat) {
        String row = seat.substring(0, 7);
        String column = seat.substring(7);
        int rowNumber = Integer.parseInt(row.replaceAll("F", "0").replaceAll("B", "1"), 2);
        int columnNumber = Integer.parseInt(column.replaceAll("L", "0").replaceAll("R", "1"), 2);
        return new Pair<>(rowNumber, columnNumber);
    }

    public static int seatId(int row, int column) {
        return row * 8 + column;
    }
}
