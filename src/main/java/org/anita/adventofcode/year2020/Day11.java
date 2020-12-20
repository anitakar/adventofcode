package org.anita.adventofcode.year2020;

public class Day11 {
    public static class GameOfLifeModified {
        private char[][] board;

        public GameOfLifeModified(char[][] board) {
            this.board = board;
        }

        public void play() {
            int numSeatsChanged = 0;
            do {
                numSeatsChanged = 0;
                char[][] newBoard = new char[board.length][board[0].length];
                for (int i = 0; i < board.length; ++i) {
                    for (int j = 0; j < board[0].length; ++j) {
                        if (board[i][j] == '.') {
                            newBoard[i][j] = '.';
                            continue;
                        }
                        int numOccupiedAround = 0;
                        if (i > 0) {
                            if (j > 0) {
                                if (board[i - 1][j - 1] == '#') {
                                    numOccupiedAround += 1;
                                }
                            }
                            if (board[i - 1][j] == '#') {
                                numOccupiedAround += 1;
                            }
                            if (j < board[0].length - 1) {
                                if (board[i - 1][j + 1] == '#') {
                                    numOccupiedAround += 1;
                                }
                            }
                        }
                        if (i < board.length - 1) {
                            if (j > 0) {
                                if (board[i + 1][j - 1] == '#') {
                                    numOccupiedAround += 1;
                                }
                            }
                            if (board[i + 1][j] == '#') {
                                numOccupiedAround += 1;
                            }
                            if (j < board[0].length - 1) {
                                if (board[i + 1][j + 1] == '#') {
                                    numOccupiedAround += 1;
                                }
                            }
                        }
                        if (j > 0) {
                            if (board[i][j - 1] == '#') {
                                numOccupiedAround += 1;
                            }
                        }
                        if (j < board[0].length - 1) {
                            if (board[i][j + 1] == '#') {
                                numOccupiedAround += 1;
                            }
                        }
                        if (board[i][j] == 'L' && numOccupiedAround == 0) {
                            newBoard[i][j] = '#';
                            numSeatsChanged += 1;
                            continue;
                        }
                        if (board[i][j] == '#' && numOccupiedAround >= 4) {
                            newBoard[i][j] = 'L';
                            numSeatsChanged += 1;
                            continue;
                        }
                        newBoard[i][j] = board[i][j];
                    }
                }
                board = newBoard;
            } while (numSeatsChanged > 0);
        }

        public void play2() {
            int numSeatsChanged = 0;
            do {
                numSeatsChanged = 0;
                char[][] newBoard = new char[board.length][board[0].length];
                for (int i = 0; i < board.length; ++i) {
                    for (int j = 0; j < board[0].length; ++j) {
                        if (board[i][j] == '.') {
                            newBoard[i][j] = '.';
                            continue;
                        }
                        int numOccupiedAround = 0;
                        int newi = i;
                        int newj = j;
                        // NW
                        while (newi > 0  && newj > 0) {
                            newi -= 1;
                            newj -= 1;
                            if (board[newi][newj] == '.') {
                                continue;
                            } else if (board[newi][newj] == 'L') {
                                break;
                            } else if (board[newi][newj] == '#') {
                                numOccupiedAround += 1;
                                break;
                            }
                        }
                        newi = i;
                        newj = j;
                        // N
                        while (newi > 0) {
                            newi -= 1;
                            if (board[newi][newj] == '.') {
                                continue;
                            } else if (board[newi][newj] == 'L') {
                                break;
                            } else if (board[newi][newj] == '#') {
                                numOccupiedAround += 1;
                                break;
                            }
                        }
                        newi = i;
                        newj = j;
                        // NE
                        while (newi > 0 && newj < board[0].length - 1) {
                            newi -= 1;
                            newj += 1;
                            if (board[newi][newj] == '.') {
                                continue;
                            } else if (board[newi][newj] == 'L') {
                                break;
                            } else if (board[newi][newj] == '#') {
                                numOccupiedAround += 1;
                                break;
                            }
                        }
                        newi = i;
                        newj = j;
                        // SW
                        while (newi < board.length - 1 && newj > 0) {
                            newi += 1;
                            newj -= 1;
                            if (board[newi][newj] == '.') {
                                continue;
                            } else if (board[newi][newj] == 'L') {
                                break;
                            } else if (board[newi][newj] == '#') {
                                numOccupiedAround += 1;
                                break;
                            }
                        }
                        newi = i;
                        newj = j;
                        // SE
                        while (newi < board.length - 1 && newj < board[0].length - 1) {
                            newi += 1;
                            newj += 1;
                            if (board[newi][newj] == '.') {
                                continue;
                            } else if (board[newi][newj] == 'L') {
                                break;
                            } else if (board[newi][newj] == '#') {
                                numOccupiedAround += 1;
                                break;
                            }
                        }
                        newi = i;
                        newj = j;
                        // S
                        while (newi < board.length - 1) {
                            newi += 1;
                            if (board[newi][newj] == '.') {
                                continue;
                            } else if (board[newi][newj] == 'L') {
                                break;
                            } else if (board[newi][newj] == '#') {
                                numOccupiedAround += 1;
                                break;
                            }
                        }
                        newi = i;
                        newj = j;
                        // W
                        while (newj > 0) {
                            newj -= 1;
                            if (board[newi][newj] == '.') {
                                continue;
                            } else if (board[newi][newj] == 'L') {
                                break;
                            } else if (board[newi][newj] == '#') {
                                numOccupiedAround += 1;
                                break;
                            }
                        }
                        newi = i;
                        newj = j;
                        // E
                        while (newj < board[0].length - 1) {
                            newj += 1;
                            if (board[newi][newj] == '.') {
                                continue;
                            } else if (board[newi][newj] == 'L') {
                                break;
                            } else if (board[newi][newj] == '#') {
                                numOccupiedAround += 1;
                                break;
                            }
                        }
                        if (board[i][j] == 'L' && numOccupiedAround == 0) {
                            newBoard[i][j] = '#';
                            numSeatsChanged += 1;
                            continue;
                        }
                        if (board[i][j] == '#' && numOccupiedAround >= 5) {
                            newBoard[i][j] = 'L';
                            numSeatsChanged += 1;
                            continue;
                        }
                        newBoard[i][j] = board[i][j];
                    }
                }
                board = newBoard;
            } while (numSeatsChanged > 0);
        }

        public int getOccupiedSeats() {
            int numOccupiedSeats = 0;
            for (int i = 0; i < board.length; ++i) {
                for (int j = 0; j < board[0].length; ++j) {
                    if (board[i][j] == '#') {
                        numOccupiedSeats += 1;
                    }
                }
            }
            return numOccupiedSeats;
        }

        private void print() {
            System.out.println("----------------------------");
            for (int i = 0; i < board.length; ++i) {
                for (int j = 0; j < board[0].length; ++j) {
                    System.out.print(board[i][j]);
                }
                System.out.println();
            }
        }
    }
}
