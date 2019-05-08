package com.packtpublishing.tddjava.ch03tictactoe;

/**
 * @author benjaminmartinez
 * Date: 2019-04-27
 */
public class BoardValueAddedEvent extends BoardEvent {
    private Play play;

    public BoardValueAddedEvent(Play play) {
        this.play = play;
    }

    public Play getPlay() {
        return this.play;
    }
}
