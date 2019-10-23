package org.anita.adventofcode.year2016;

import org.anita.adventofcode.structures.Position2D;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;

public class Day2 {

    public String rectangularLockpadNumber(List<String> steps) {
        return lockpadNumber(steps, new Position2D(1, 1), this::digit, this::withinBounds);
    }

    public String diamondLockpadNumber(List<String> steps) {
        return lockpadNumber(steps, new Position2D(-2, 0), this::digit2, this::withinBounds2);
    }

    public String lockpadNumber(List<String> steps, Position2D start, Function<Position2D, Character> digit, Function<Position2D, Boolean> withinBounds) {
        String code = "";
        Position2D current = start;
        for (String step : steps) {
            for (char move : step.toCharArray()) {
                switch(move) {
                    case 'L': {
                        Position2D next = current.left();
                        if (withinBounds.apply(next)) {
                            current = next;
                        }
                    } break;
                    case 'R': {
                        Position2D next = current.right();
                        if (withinBounds.apply(next)) {
                            current = next;
                        }
                    } break;
                    case 'U': {
                        Position2D next = current.down();
                        if (withinBounds.apply(next)) {
                            current = next;
                        }
                    } break;
                    case 'D': {
                        Position2D next = current.up();
                        if (withinBounds.apply(next)) {
                            current = next;
                        }
                    } break;
                }
            }
            code += digit.apply(current);
        }
        return code;
    }

    private Character digit(Position2D position) {
        return Character.forDigit((2- position.y) * 3 + position.x + 1, 10);
    }

    private boolean withinBounds(Position2D position) {
        return position.y >= 0 && position.y <=2 && position.x >= 0 && position.x <=2;
    }

    private Map<Position2D, Character> chars = new HashMap(){{
        put(new Position2D(0, 2), '1');
        put(new Position2D(-1, 1), '2');
        put(new Position2D(0, 1), '3');
        put(new Position2D(1, 1), '4');
        put(new Position2D(-2, 0), '5');
        put(new Position2D(-1, 0), '6');
        put(new Position2D(0, 0), '7');
        put(new Position2D(1, 0), '8');
        put(new Position2D(2, 0), '9');
        put(new Position2D(-1, -1), 'A');
        put(new Position2D(0, -1), 'B');
        put(new Position2D(1, -1), 'C');
        put(new Position2D(0, -2), 'D');
    }};

    private Character digit2(Position2D position) {
        return chars.get(position);
    }

    private boolean withinBounds2(Position2D position) {
        return position.manhattanDistance(new Position2D(0,0)) <= 2;
    }
}
