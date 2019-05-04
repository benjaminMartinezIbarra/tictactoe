package com.packtpublishing.tddjava.ch03tictactoe;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import static org.junit.Assert.assertEquals;

/**
 * @author benjaminmartinez
 * Date: 2019-04-22
 */
public class TicTacToeBasicSpec {

    @Rule
    public ExpectedException exception = ExpectedException.none();

    private TicTacToeBasic game;

    @Before
    public void setup() {
        game = new TicTacToeBasic();
    }


    @Test
    public void whenGameIsStartedPlayerOneIsFirst(){
        assertEquals("player1", game.getCurrentPlayer());
    }


    //If the last turn was played by X, then the next turn should be played by O

    @Test
    public void whenLastTurnPlayerXThenNextTurnPlayer0(){
        game.play(1,1);
        assertEquals("player2", game.getCurrentPlayer());

    }

    @Test
    public void whenLastTurnPlayer2ThenNextTurnPlayer1(){
        game.play(1,1);
        game.play(2,1);
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
}
