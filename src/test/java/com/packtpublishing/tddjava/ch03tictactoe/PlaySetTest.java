package com.packtpublishing.tddjava.ch03tictactoe;

import org.junit.Assert;
import org.junit.Test;

/**
 * @author benjaminmartinez
 * Date: 2019-04-29
 */
public class PlaySetTest {

    @Test
    public void addValueToPlaySetNotComplete(){
        PlaySet set = new PlaySet(new Play(new Position(1,1),
                                           1,
                                           "player1"));
        Assert.assertTrue(set.isComplete() == false);

    }

    @Test
    public void addValueToPlaySetNotSettable(){
        PlaySet set = new PlaySet(new Play(new Position(1,1),
                                           1,
                                           "player1"));
        set.addPlay(new Play(new Position(1,2),
                             4,
                             "player1"));

        Assert.assertTrue(set.isPossibleAdd(new Play(new Position(3,3),
                                            4,
                                            "player1")) == false);
        Assert.assertTrue(set.isComplete() == false);

    }

    @Test
    public void addValueToPlaySetNotSettableBadPlayer(){
        PlaySet set = new PlaySet(new Play(new Position(1,1),
                                           1,
                                           "player1"));
        set.addPlay(new Play(new Position(1,2),
                             4,
                             "player1"));

        Assert.assertTrue(set.isPossibleAdd(new Play(new Position(1,3),
                                                     4,
                                                     "player2")) == false);
        Assert.assertTrue(set.isComplete() == false);

    }
}
