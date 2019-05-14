package com.packtpublishing.tddjava.ch03tictactoe.integration;

import com.packtpublishing.tddjava.ch03tictactoe.TicTacToe;
import com.packtpublishing.tddjava.ch03tictactoe.TicTacToeBasic;
import org.junit.Test;

import java.net.UnknownHostException;

import static org.junit.Assert.assertEquals;

/**
 * @author benjaminmartinez
 * Date: 2019-05-14
 */
public class TicTacToeIntegration {

    @Test
    public void givenMongoDbIsRunningWhenPlayThenNoException()
            throws UnknownHostException {
        TicTacToe ticTacToe = new TicTacToeBasic();
        assertEquals(ticTacToe.getGameStatus(), "started");
    }
}
