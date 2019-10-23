package org.anita.adventofcode.year2016;

import org.anita.adventofcode.structures.Position2D;

import java.util.List;

public class Day2 {
    public String lockpadNumber(List<String> steps) {
        String code = "";
        Position2D current = new Position2D(1, 1);
        for (String step : steps) {
            for (char move : step.toCharArray()) {
                switch(move) {
                    case 'L': {
                        Position2D next = current.left();
                        if (withinBonds(next)) {
                            current = next;
                        }
                    } break;
                    case 'R': {
                        Position2D next = current.right();
                        if (withinBonds(next)) {
                            current = next;
                        }
                    } break;
                    case 'U': {
                        Position2D next = current.up();
                        if (withinBonds(next)) {
                            current = next;
                        }
                    } break;
                    case 'D': {
                        Position2D next = current.down();
                        if (withinBonds(next)) {
                            current = next;
                        }
                    } break;
                }
            }
            code += digit(current);
        }
        return code;
    }

    private int digit(Position2D position) {
        return position.y * 3 + position.x + 1;
    }

    private boolean withinBonds(Position2D position) {
        return position.y >= 0 && position.y <=2 && position.x >= 0 && position.x <=2;
    }
}
