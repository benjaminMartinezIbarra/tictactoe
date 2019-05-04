package com.packtpublishing.tddjava.ch03tictactoe;

import org.junit.Assert;
import org.junit.Test;

/**
 * @author benjaminmartinez
 * Date: 2019-04-27
 */
public class BoardSpec {


    @Test
    public void testBuildingBoardForTicTacToe15() {

        Integer [][] vals = {
                {8, 4, 5},
                {1, 2, 3},
                {6, 7, 9}
        };

        Board board = new Board(vals);
        Assert.assertTrue(board.size() == 9);
        Assert.assertTrue(board.get(new Position(1, 1)).equals(8));
        Assert.assertTrue(board.get(new Position(1, 2)).equals(4));
        Assert.assertTrue(board.get(new Position(1, 3)).equals(5));
        Assert.assertTrue(board.get(new Position(2, 1)).equals(1));
        Assert.assertTrue(board.get(new Position(2, 2)).equals(2));
        Assert.assertTrue(board.get(new Position(2, 3)).equals(3));
        Assert.assertTrue(board.get(new Position(3, 1)).equals(6));
        Assert.assertTrue(board.get(new Position(3, 2)).equals(7));
        Assert.assertTrue(board.get(new Position(3, 3)).equals(9));

    }

}
