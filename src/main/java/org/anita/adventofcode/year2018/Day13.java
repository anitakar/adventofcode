package org.anita.adventofcode.year2018;

import org.anita.adventofcode.structures.Position;

import java.util.Iterator;
import java.util.List;
import java.util.TreeMap;

public class Day13 {

    char[][] track;
    TreeMap<Position, Cart> carts = new TreeMap<>();

    public Day13(List<String> lines) {
        parseMap(lines);
        //printMap();
    }

    private void parseMap(List<String> lines) {
        int maxy = lines.size() - 1;
        int maxx = 0;
        for (String line : lines) {
            maxx = Math.max(maxx, line.length() - 1);
        }
        track = new char[maxx + 1][maxy + 1];
        int y = 0;
        for (String line : lines) {
            for (int x = 0; x < line.length(); ++x) {
                if (line.charAt(x) == '>' || line.charAt(x) == '<') {
                    carts.put(new Position(x, y), new Cart(line.charAt(x), new Position(x, y)));
                    track[x][y] = '-';
                } else if (line.charAt(x) == '^' || line.charAt(x) == 'v') {
                    carts.put(new Position(x, y), new Cart(line.charAt(x), new Position(x, y)));
                    track[x][y] = '|';
                } else if (line.charAt(x) != ' ' && line.charAt(x) != '\n') {
                    track[x][y] = line.charAt(x);
                }
            }
            y += 1;
        }
    }

    public Position task1() {
        while (true) {
            TreeMap<Position, Cart> after = new TreeMap<>();
            Iterator<Position> iterator = carts.navigableKeySet().iterator();
            while (iterator.hasNext()) {
                Position position = iterator.next();
                Cart cart = carts.get(position);
                cart.move();
                if (carts.containsKey(cart.position) || after.containsKey(cart.position)) {
                    return cart.position;
                }
                iterator.remove();
                after.put(new Position(cart.position.x, cart.position.y), cart);
            }
            carts = after;
            //printMap();
        }
    }

    public Position task2() {
        while (carts.size() > 1) {
            TreeMap<Position, Cart> after = new TreeMap<>();
            Iterator<Position> iterator = carts.navigableKeySet().iterator();
            while (iterator.hasNext()) {
                Position position = iterator.next();
                Cart cart = carts.get(position);
                if (cart.crashed) {
                    iterator.remove();
                    continue;
                }
                cart.move();
                if (carts.containsKey(cart.position) && !carts.get(cart.position).crashed) {
                    iterator.remove();
                    carts.get(cart.position).crashed = true;
                } else if (after.containsKey(cart.position) && !after.get(cart.position).crashed) {
                    iterator.remove();
                    after.get(cart.position).crashed = true;
                } else {
                    iterator.remove();
                    after.put(new Position(cart.position.x, cart.position.y), cart);
                }
            }
            carts = new TreeMap<>();
            iterator = after.navigableKeySet().iterator();
            while (iterator.hasNext()) {
                Position position = iterator.next();
                if (!after.get(position).crashed) {
                    carts.put(new Position(position.x, position.y), after.get(position));
                }
            }
            //printMap();
        }
        //printMap();
        return carts.keySet().iterator().next();
    }

    public void printMap() {
        for (int y = 0; y < track[0].length; ++y) {
            for (int x = 0; x < track.length; ++x) {
                if (carts.containsKey(new Position(x, y))) {
                    System.out.print(carts.get(new Position(x, y)).direction);
                } else if (track[x][y] == 0) {
                    System.out.print(' ');
                } else {
                    System.out.print(track[x][y]);
                }
            }
            System.out.println();
        }
        System.out.println("-----");
    }

    class Cart {
        char direction;
        int nextTurn = 0; //0-left, 1-straight, 2-right
        Position position;
        boolean crashed = false;

        public Cart(char direction, Position position) {
            this.direction = direction;
            this.position = position;
        }

        public void move() {
            if (direction == '^') {
                char step = track[position.x][position.y - 1];
                position.y -= 1;
                if (step == '/') {
                    direction = '>';
                } else if (step == '\\') {
                    direction = '<';
                } else if (step == '+') {
                    if (nextTurn == 0) {
                        direction = '<';
                    } else if (nextTurn == 2) {
                        direction = '>';
                    }
                    nextTurn += 1;
                    nextTurn = nextTurn % 3;
                }
            } else if (direction == 'v') {
                char step = track[position.x][position.y + 1];
                position.y += 1;
                if (step == '/') {
                    direction = '<';
                } else if (step == '\\') {
                    direction = '>';
                } else if (step == '+') {
                    if (nextTurn == 0) {
                        direction = '>';
                    } else if (nextTurn == 2) {
                        direction = '<';
                    }
                    nextTurn += 1;
                    nextTurn = nextTurn % 3;
                }
            } else if (direction == '>') {
                char step = track[position.x + 1][position.y];
                position.x += 1;
                if (step == '/') {
                    direction = '^';
                } else if (step == '\\') {
                    direction = 'v';
                } else if (step == '+') {
                    if (nextTurn == 0) {
                        direction = '^';
                    } else if (nextTurn == 2) {
                        direction = 'v';
                    }
                    nextTurn += 1;
                    nextTurn = nextTurn % 3;
                }
            } else {
                char step = track[position.x - 1][position.y];
                position.x -= 1;
                if (step == '/') {
                    direction = 'v';
                } else if (step == '\\') {
                    direction = '^';
                } else if (step == '+') {
                    if (nextTurn == 0) {
                        direction = 'v';
                    } else if (nextTurn == 2) {
                        direction = '^';
                    }
                    nextTurn += 1;
                    nextTurn = nextTurn % 3;
                }
            }
        }
    }
}
