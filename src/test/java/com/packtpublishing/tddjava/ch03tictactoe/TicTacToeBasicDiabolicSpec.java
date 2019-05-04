package com.packtpublishing.tddjava.ch03tictactoe;

import org.junit.Assert;
import org.junit.Test;

/**
 * @author benjaminmartinez
 * Date: 2019-04-24
 */
public class TicTacToeBasicDiabolicSpec {

    @Test
    public void testSetupwithCustomDimensions3() {
        Board board = new Board(3);
        Assert.assertEquals(board.size(), 9);
    }

    @Test
    public void testSetupwithCustomDimensions4() {
        Board board = new Board(4);
        Assert.assertEquals(board.size(), 16);
    }

}
