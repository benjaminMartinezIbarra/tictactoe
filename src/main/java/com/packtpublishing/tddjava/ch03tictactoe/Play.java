package com.packtpublishing.tddjava.ch03tictactoe;

/**
 * @author benjaminmartinez
 * Date: 2019-04-27
 */
public class Play<C> {

    private Position position;

    private C value;

    private String player;

    public Play(Position position, C value, String player) {
        this.position = position;
        this.value = value;
        this.player = player;
    }

    public Position getPosition() {
        return this.position;
    }

    public C getValue() {
        return this.value;
    }

    public String getPlayer() {
        return this.player;
    }


}
