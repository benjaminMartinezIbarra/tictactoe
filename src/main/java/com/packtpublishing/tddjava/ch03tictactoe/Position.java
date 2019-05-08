package com.packtpublishing.tddjava.ch03tictactoe;

import java.io.Serializable;

/**
 * @author benjaminmartinez
 * Date: 2019-04-26
 */
public class Position implements Serializable {

    //todo rajouter un cache
    private int x;

    public int getX() {
        return this.x;
    }

    public int getY() {
        return this.y;
    }

    private int y;

    public Position(int x, int y) {
        this.x = x;
        this.y = y;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || this.getClass() != o.getClass()) {
            return false;
        }

        Position position = (Position) o;

        if (this.getX() != position.getX()) {
            return false;
        }
        return this.getY() == position.getY();
    }

    @Override
    public int hashCode() {
        int result = this.getX();
        result = 31 * result + this.getY();
        return result;
    }
}
