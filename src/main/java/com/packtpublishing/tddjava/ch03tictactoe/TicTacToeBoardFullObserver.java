package com.packtpublishing.tddjava.ch03tictactoe;

/**
 * @author benjaminmartinez
 * Date: 2019-04-29
 */
public class TicTacToeBoardFullObserver implements BoardObserver<BoardFullEvent> {

    TicTacToe game;

    public TicTacToeBoardFullObserver(final TicTacToe game) {
        this.game = game;
    }

    @Override
    public void update(final BoardFullEvent event) {
        this.game.drawGame();
    }

}
