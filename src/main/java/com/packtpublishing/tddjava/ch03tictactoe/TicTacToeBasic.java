package com.packtpublishing.tddjava.ch03tictactoe;

/**
 * @author benjaminmartinez
 * Date: 2019-04-23
 */
public class TicTacToeBasic extends TicTacToe {

    public TicTacToeBasic(final int i) {
        super(i);
    }

    @Override
    protected boolean isWinning(final PlaySet completed) {
        return true;
    }

    public TicTacToeBasic() {
        this(3);
    }

    public void play(int x, int y) {
        play(x, y, getPieceForCurrentPlayer());
    }

    private Character getPieceForCurrentPlayer() {
        switch (getCurrentPlayer()) {
            case PLAYER_1:
                return 'O';
            case PLAYER_2:
                return 'X';
            default:
                return 'X';
        }

    }

}
