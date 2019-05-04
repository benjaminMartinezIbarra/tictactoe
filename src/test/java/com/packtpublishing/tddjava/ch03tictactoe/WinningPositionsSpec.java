package com.packtpublishing.tddjava.ch03tictactoe;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

/**
 * @author benjaminmartinez
 * Date: 2019-04-24
 */
public class WinningPositionsSpec {

    private TicTacToeBasic game;


    @Before
    public void setup(){
        game = new TicTacToeBasic();
    }

    @Test
    public void testWhenHorizontalFirstLineWining(){
        game.play(1,1);
        game.play(2,2);
        game.play(2,1);
        game.play(2,3);
        game.play(3,1);

        Assert.assertEquals("player1 won the game", game.getGameStatus());
    }

    @Test
    public void testWhenGameStartedNoWinner(){
        Assert.assertEquals("started", game.getGameStatus());
    }

    @Test
    public void testWhenVerticalFirstLineWining(){
        game.play(1,1);
        game.play(2,2);
        game.play(1,2);
        game.play(2,3);
        game.play(1,3);

        Assert.assertEquals("player1 won the game", game.getGameStatus());
    }

    @Test
    public void testWhenDiagonal1Wining(){
        game.play(1,1);
        game.play(2,1);
        game.play(2,2);
        game.play(2,3);
        game.play(3,3);

        Assert.assertEquals("player1 won the game", game.getGameStatus());
    }

    @Test
    public void testWhenDiagonal2Wining(){
        game.play(3,1);
        game.play(1,1);
        game.play(2,2);
        game.play(2,3);
        game.play(1,3);

        Assert.assertEquals("player1 won the game", game.getGameStatus());
    }

    @Test
    public void testWhenAllPositionAreTakenThenTie(){
        game.play(1,1);
        game.play(1,2);
        game.play(1,3);
        game.play(2,1);
        game.play(2,3);
        game.play(2,2);
        game.play(3,1);
        game.play(3,3);
        game.play(3,2);

        Assert.assertEquals("draw", game.getGameStatus());
    }

}
