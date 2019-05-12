package com.packtpublishing.tddjava.ch03tictactoe;

import com.packtpublishing.tddjava.ch03tictactoe.mongo.TiTacToeRepository;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import java.net.UnknownHostException;

import static org.mockito.Mockito.mock;

/**
 * @author benjaminmartinez
 * Date: 2019-04-25
 */
public class TicTacToe15Spec {


    TicTacToe15 game;
    TiTacToeRepository repository;

    @Rule
    public ExpectedException expectedException = ExpectedException.none();

    @Before
    public void setup() throws UnknownHostException {
        repository = mock(TiTacToeRepository.class);
        game = new TicTacToe15(3, repository);
    }

    @Test
    public void whenGameStartedAndFirstPlayerPlaysThenGameNotFinished(){

        game.play(1, 1, 1);
        Assert.assertEquals("started", game.getGameStatus());
    }

    @Test
    public void whenGameStartedAndFirstPlayerPlaysThenSecondPlayerTurn(){

        game.play(1,1, 1);
        Assert.assertEquals("player2", game.getCurrentPlayer());
    }

    @Test
    public void testPlayerPlaysBadValueKO1(){
        expectedException.expect(RuntimeException.class);
        expectedException.expectMessage("Value 10 is not in the range of supported values (1..9)");
        game.play(1,1, 10);

    }

    @Test
    public void testPlayerPlaysBadValueKO2(){
        expectedException.expect(RuntimeException.class);
        expectedException.expectMessage("Value 0 is not in the range of supported values (1..9)");
        game.play(1,1, 0);

    }

    @Test
    public void testPlayerPlaysGoodValue9(){
        game.play(1,1, 9);
        Assert.assertEquals("started", game.getGameStatus());

    }

    @Test
    public void testPlayerPlaysKnownValueThenError(){
        game.play(1,1, 8);
        expectedException.expect(RuntimeException.class);
        expectedException.expectMessage("Value 8 is already taken");
        game.play(2,1, 8);

    }

    @Test
    public void testWhenFirstPlayerReach15WithAdjacentValuesThenPlayerWins1(){
        game.play(1,1, 8);
        game.play(2,1, 1);
        game.play(1,2, 4);
        game.play(2,2, 2);
        game.play(1,3, 3);
        Assert.assertEquals("player1 won the game", game.getGameStatus());
    }


    @Test
    public void testWhenFirstPlayerReach15WithAdjacentValuesThenPlayerWins2(){
        game.play(2,1, 1);
        game.play(1,1, 8);
        game.play(2,2, 2);
        game.play(1,2, 4);
        game.play(2,3, 6);
        game.play(1,3, 3);
        Assert.assertEquals("player2 won the game", game.getGameStatus());
    }

    @Test
    public void testWhenFirstPlayerReach15WithAdjacentValuesThenPlayerWins2Diagonal1(){
        game.play(2,1, 1);
        game.play(2,3, 8);
        game.play(1,2, 2);
        game.play(2,2, 4);
        game.play(3,2, 6);
        game.play(3,3, 3);
        Assert.assertEquals("player2 won the game", game.getGameStatus());
    }

    @Test
    public void testWhenFirstPlayerReach15WithAdjacentValuesThenPlayerWins2Diagonal2(){
        game.play(3,1, 8);
        game.play(2,3, 5);
        game.play(2,2, 4);
        game.play(1,2, 2);
        game.play(1,3, 3);
        game.play(3,3, 1);
        Assert.assertEquals("player1 won the game", game.getGameStatus());
    }

    @Test
    public void testWhenFirstPlayerReach15WithNoAdjacentValuesThenNoWin(){
        game.play(2,1, 1);
        game.play(1,1, 8);
        game.play(2,2, 2);
        game.play(1,2, 4);
        game.play(2,3, 6);
        game.play(3,2, 3);
        Assert.assertEquals("started", game.getGameStatus());
    }


    @Test
    public void testWhenFirstPlayerReach15WithAdjacentValuesNOT_IN_LINE_ThenNoWin1(){
        game.play(2,1, 1);
        game.play(1,1, 8);
        game.play(3,3, 2);
        game.play(1,2, 4);
        game.play(2,3, 6);
        game.play(2,2, 3);
        Assert.assertEquals("started", game.getGameStatus());
    }

    @Test
    public void testWhenFirstPlayerReach15WithAdjacentValuesNOT_IN_LINE_ThenNoWin2(){
        game.play(2,1, 1);
        game.play(2,3, 8);
        game.play(3,3, 2);
        game.play(1,2, 4);
        game.play(3,2, 6);
        game.play(2,2, 3);
        Assert.assertEquals("started", game.getGameStatus());
    }


    @Test
    public void testFullBoardNoAdjacent15ThenDraw(){
        game.play(1,1, 1);
        game.play(1,2, 2);
        game.play(1,3, 3);
        game.play(2,1, 4);
        game.play(2,2, 5);
        game.play(2,3, 6);
        game.play(3,1, 7);
        game.play(3,2, 8);
        game.play(3,3, 9);
        Assert.assertEquals("draw", game.getGameStatus());
    }

    @Test
    public void testWhenFirstPlayerHasAdjacentValuesWithoutReaching15ThenNoWin(){
        game.play(1,1, 8);
        game.play(2,1, 1);
        game.play(1,2, 4);
        game.play(2,2, 2);
        game.play(1,3, 5);
        Assert.assertEquals("started", game.getGameStatus());
    }




}
