package com.packtpublishing.tddjava.ch03tictactoe.mongo;

import org.jongo.marshall.jackson.oid.Id;

public class TicTacToeBean<C> {

    @Id
    private int turn;
    private C value;

    public int getTurn() {
        return turn;
    }

    private int x;

    public int getX() {
        return x;
    }

    private int y;

    public int getY() {
        return y;
    }

    private String player;

    public String getPlayer() {
        return player;
    }



    public TicTacToeBean(int turn, int x, int y, String player, C value) {
        this.turn = turn;
        this.x = x;
        this.y = y;
        this.player = player;
        this.value = value;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || this.getClass() != o.getClass()) {
            return false;
        }

        TicTacToeBean<?> that = (TicTacToeBean<?>) o;

        if (this.getTurn() != that.getTurn()) {
            return false;
        }
        if (this.getX() != that.getX()) {
            return false;
        }
        if (this.getY() != that.getY()) {
            return false;
        }
        if (this.getValue() != null ? !this.getValue().equals(that.getValue()) : that.getValue() != null) {
            return false;
        }
        return this.getPlayer() != null ? this.getPlayer().equals(that.getPlayer()) : that.getPlayer() == null;
    }

    @Override
    public int hashCode() {
        int result = this.getTurn();
        result = 31 * result + this.getValue().hashCode();
        result = 31 * result + this.getX();
        result = 31 * result + this.getY();
        result = 31 * result + this.getPlayer().hashCode();
        return result;
    }

    @Override
    public String toString() {
        return "TicTacToeBean{" +
               "turn=" + turn +
               ", value=" + value +
               ", x=" + x +
               ", y=" + y +
               ", player='" + player + '\'' +
               '}';
    }

    public C getValue() {
        return this.value;
    }

}