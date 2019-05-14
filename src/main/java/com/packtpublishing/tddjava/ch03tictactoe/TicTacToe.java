package com.packtpublishing.tddjava.ch03tictactoe;

import com.packtpublishing.tddjava.ch03tictactoe.mongo.TiTacToeRepository;
import com.packtpublishing.tddjava.ch03tictactoe.mongo.TicTacToeBean;

import java.net.UnknownHostException;

/**
 * @author benjaminmartinez
 * Date: 2019-04-25
 */
public abstract class TicTacToe<C> {

    private GameBoard board;
    private String currentPlayer;
    private String status;

    public static final String PLAYER_1 = "player1";
    public static final String PLAYER_2 = "player2";
    private TiTacToeRepository repository;
    private int turn;

    public TicTacToe(int dimension, TiTacToeRepository repository) {
        board = new GameBoard(dimension);
        board.addObserver(getPlayersStatusWatcher());
        board.addObserver(getBoardFullWatcher());
        this.repository = repository;
        turn = 1;
        currentPlayer = PLAYER_1;
        status = "started";
        onGameStart();

    }

    private void onGameStart() {
        if (!repository.drop()) {
            throw new RuntimeException("Error dropping Last Game data");
        }
    }

    private TicTacToeBoardFullObserver getBoardFullWatcher() {
        return new TicTacToeBoardFullObserver(this);
    }

    private BoardValueAddedObserver getPlayersStatusWatcher() {
        return new BoardValueAddedObserver(this);
    }

    protected abstract boolean isWinning(final PlaySet<C> completed);

    public void winGame(final String player) {
        status = player + " won the game";
    }

    public void drawGame() {
        status = "draw";
    }

    public void play(int x, int y, C value) {
        play(new TicTacToeBean(turn, x, y, currentPlayer, value));
    }

    public void play(TicTacToeBean<C> bean) {
        beforeVerifyPlacement(bean);

        verifyPlacement(bean.getX(), bean.getY());
        board.put(new Position(bean.getX(), bean.getY()), bean.getValue(), currentPlayer);
        saveMove(bean);

        afterVerifyPlacement(bean);

        if (!gamefinished()) {
            swapPlayer();
            turn++;
        }
    }

    private void saveMove(final TicTacToeBean<C> bean) {
        if (!repository.saveMove(bean)) {
            throw new RuntimeException("Error while saving player move");
        }
    }

    protected abstract void beforeVerifyPlacement(final TicTacToeBean<C> bean);

    protected abstract void afterVerifyPlacement(TicTacToeBean<C> bean);

    private void verifyPlacement(final int x, final int y) {

        if (x < 1 || x > board.getDimension()) {
            throw new RuntimeException("X is off limits");
        }
        if (y < 1 || y > board.getDimension()) {
            throw new RuntimeException("Y is off limits");
        }
        if (board.positionTaken(x, y)) {
            throw new RuntimeException(String.format("Position is already taken (%s,%s)", x, y));
        }

    }

    public String getCurrentPlayer() {
        return currentPlayer;
    }

    private boolean gamefinished() {
        return !(getGameStatus().equals("started"));

    }

    private void swapPlayer() {
        if (currentPlayer == PLAYER_2) {
            currentPlayer = PLAYER_1;
        } else {
            currentPlayer = PLAYER_2;
        }
    }

    public String getGameStatus() {
        return this.status;
    }

}
