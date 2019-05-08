package com.packtpublishing.tddjava.ch03tictactoe;

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

    public TicTacToe(int dimension) {
        board = new GameBoard(dimension);
        board.addObserver(getPlayersStatusWatcher());
        board.addObserver(getBoardFullWatcher());
        currentPlayer = PLAYER_1;
        status = "started";

    }

    private TicTacToeBoardFullObserver getBoardFullWatcher() {
        return new TicTacToeBoardFullObserver(this);
    }

    private BoardValueAddedObserver getPlayersStatusWatcher() {
        return new BoardValueAddedObserver(this) ;
    }

    protected abstract boolean isWinning(final PlaySet<C> completed);

    public void winGame(final String player) {
        status = player + " won the game";
    }

    public void drawGame() {
        status = "draw";
    }

    public void play(int x, int y, final C value) {
        beforeVerifyPlacement(x, y, value);
        verifyPlacement(x, y);

        board.put(new Position(x, y), value, currentPlayer);

        afterVerifyPlacement(x, y, value);

        if (!gamefinished()) {
            swapPlayer();
        }
    }

    protected void afterVerifyPlacement(final int x, final int y, final C value) {

    }

    protected void beforeVerifyPlacement(final int x, final int y, final C value) {

    }

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
