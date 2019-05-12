package com.packtpublishing.tddjava.ch03tictactoe;

import com.packtpublishing.tddjava.ch03tictactoe.mongo.TiTacToeRepository;
import com.packtpublishing.tddjava.ch03tictactoe.mongo.TicTacToeBean;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import java.net.UnknownHostException;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

/**
 * @author benjaminmartinez
 * Date: 2019-04-22
 */
public class TicTacToeBasicSpec {

    @Rule
    public ExpectedException exception = ExpectedException.none();

    private TicTacToeBasic game;
    private TiTacToeRepository tiTacToeRepository;

    @Before
    public void setup() throws UnknownHostException {

        tiTacToeRepository = mock(TiTacToeRepository.class);
        doReturn(true).when(tiTacToeRepository).saveMove(any());
        doReturn(true).when(tiTacToeRepository).drop();
        game = new TicTacToeBasic(tiTacToeRepository);

    }

    @Test
    public void whenGameIsStartedPlayerOneIsFirst() {
        assertEquals("player1", game.getCurrentPlayer());
    }

    //If the last turn was played by X, then the next turn should be played by O

    @Test
    public void whenLastTurnPlayerXThenNextTurnPlayer0() {
        game.play(1, 1);
        assertEquals("player2", game.getCurrentPlayer());

    }

    @Test
    public void whenLastTurnPlayer2ThenNextTurnPlayer1() {
        game.play(1, 1);
        game.play(2, 1);
        assertEquals("player1", game.getCurrentPlayer());

    }

    @Test
    public void whenPieceisPlacedOutsideMaxXAxis() {
        exception.expect(RuntimeException.class);
        exception.expectMessage("X is off limits");
        game.play(4, 1);

    }

    @Test
    public void whenPieceisPlacedOutsideMinXAxis() {
        exception.expect(RuntimeException.class);
        exception.expectMessage("X is off limits");
        game.play(0, 1);

    }

    @Test
    public void whenPieceisPlacedOutsideMaxYAxis() {
        exception.expect(RuntimeException.class);
        exception.expectMessage("Y is off limits");
        game.play(1, 4);
    }

    @Test
    public void whenPieceisPlacedOutsideMinYAxis() {
        exception.expect(RuntimeException.class);
        exception.expectMessage("Y is off limits");
        game.play(1, 0);

    }

    @Test
    public void whenPieceisPlacedInsideXAxis() {
        game.play(3, 1);

    }

    @Test
    public void whenPieceisPlacedInsideYAxis() {
        game.play(1, 3);

    }

    @Test
    public void whenPieceisPlacedInNonEmptyPlace() {
        game.play(1, 1);
        exception.expect(RuntimeException.class);
        exception.expectMessage("Position is already taken (1,1)");
        game.play(1, 1);

    }

    @Test
    public void whenDiscIsPlayedThenMoveIsSaved() {
        TicTacToeBean bean = new TicTacToeBean(1, 1, 1, "player1", 'O');
        game.play(bean.getX(), bean.getY());

        verify(tiTacToeRepository).saveMove(bean);
    }

    @Test
    public void whenPlayAndSaveReturnsFalseThenThrowException() {

        TicTacToeBean bean = new TicTacToeBean(1, 1, 1, "player1", 'O');
        doReturn(false).when(tiTacToeRepository).saveMove(bean);
        exception.expectMessage("Error while saving player move");
        game.play(bean.getX(), bean.getY());
    }

    @Test
    public void whenGameIsStartedThenDBIsDropped(){
        verify(tiTacToeRepository).drop();
    }

    @Test
    public void whenGameIsStartedThenIfDBIsNotDroppedThenReturnException() throws UnknownHostException {

        doReturn(false).when(tiTacToeRepository).drop();
        exception.expect(RuntimeException.class);
        new TicTacToeBasic(tiTacToeRepository);
        verify(tiTacToeRepository).drop();
    }

    @Test
    public void whenPlayInvokedMultipleTimesThenTurnIncreases() {
        game.play(1, 1);
        game.play(2, 2);
        verify(tiTacToeRepository).saveMove(new TicTacToeBean(2, 2, 2, "player2", 'X'));
    }
}
