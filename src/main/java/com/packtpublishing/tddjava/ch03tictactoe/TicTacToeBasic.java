package com.packtpublishing.tddjava.ch03tictactoe;

import com.packtpublishing.tddjava.ch03tictactoe.mongo.TiTacToeRepository;
import com.packtpublishing.tddjava.ch03tictactoe.mongo.TicTacToeBean;

import java.net.UnknownHostException;

/**
 * @author benjaminmartinez
 * Date: 2019-04-23
 */
public class TicTacToeBasic extends TicTacToe {

    public TicTacToeBasic(final int i, final TiTacToeRepository repository) throws UnknownHostException {
        super(i, repository);
    }


    @Override
    protected boolean isWinning(final PlaySet completed) {
        return true;
    }

    @Override
    protected void beforeVerifyPlacement(final TicTacToeBean bean) {
        
    }

    public TicTacToeBasic() throws UnknownHostException {
        this(3, new TiTacToeRepository());
    }


    public TicTacToeBasic(TiTacToeRepository repository) throws UnknownHostException {
        this(3, repository);
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
