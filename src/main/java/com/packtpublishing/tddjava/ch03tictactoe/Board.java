package com.packtpublishing.tddjava.ch03tictactoe;

import java.util.HashMap;
import java.util.Map;

/**
 * @author benjaminmartinez
 * Date: 2019-04-26
 */
public class Board<C>{

    private int dimension;
    private Map<Position, C> board = new HashMap<>();

    Board(int dimension) {
        this.dimension = dimension;
        for (int line = 1; line <= dimension; line++) {
            for (int col = 1; col <= dimension; col++) {
                board.put(new Position(line, col), null);
            }
        }
    }

    public Board(final C[][] vals) {
        this.dimension = vals.length;
        for (int line = 1; line <= dimension; line++) {
            for (int col = 1; col <= dimension; col++) {
                board.put(new Position(line, col), vals[line - 1][col - 1]);
            }
        }

    }

    public boolean isFull() {
        for (int index = 1; index <= dimension; index++) {
            for (int col = 1; col <= dimension; col++) {
                if (board.get(new Position(index, col)) == null) {
                    return false;
                }
            }
        }
        return true;
    }

    public void put(final Position position, final C value) {
        board.put(position, value);

    }

    public boolean positionTaken(int x, int y) {
        return !(board.get(new Position(x, y)) == null);
    }

    public int size() {
        return board.size();
    }

    public int getDimension() {
        return this.dimension;
    }

    public C get(final Position position) {
        return board.get(position);
    }
}
