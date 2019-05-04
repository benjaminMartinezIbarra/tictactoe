package com.packtpublishing.tddjava.ch03tictactoe;

import org.junit.BeforeClass;
import org.junit.Test;

/**
 * @author benjaminmartinez
 * Date: 2019-04-29
 */
public class BoardValueAddedEventSpec {


    @BeforeClass
    public static void setup(){
        TicTacToe game = new TicTacToeBasic();
        BoardObserver bo = new BoardValueAddedObserver(game);
        BoardObserver bofull = new TicTacToeBoardFullObserver(game);
    }

    @Test
    public void testBoardFull(){

    }
}
