package com.packtpublishing.tddjava.ch03tictactoe;

import java.util.Vector;

/**
 * @author benjaminmartinez
 * Date: 2019-04-28
 */
public class PlaySet<C> {

    private String player;

    private Vector<Play<C>> plays;

    public PlaySet() {
        plays = new Vector<>();
    }

    public PlaySet(Play first) {
        this();
        player = first.getPlayer();
        plays.add(first);
    }

    public void addPlay(Play play) {
        if (!isComplete()) {
            plays.add(play);
        }
    }

    public boolean isComplete() {
        return plays.size() == 3;
    }

    public boolean isPossibleAdd(final Play play) {
        if (player.equals(play.getPlayer())) {
            final Position lastPlayPosition = plays.lastElement().getPosition();
            final Position firstPlayPosition = plays.firstElement().getPosition();

            final Position newPlayPosition = play.getPosition();

            //lines
            if (newPlayPosition.getX() == lastPlayPosition.getX() && newPlayPosition.getX() == firstPlayPosition.getX()) {
                return true;
            }
            //columns
            if (newPlayPosition.getY() == lastPlayPosition.getY() && newPlayPosition.getY() == firstPlayPosition.getY()) {
                return true;
            }

            //diagonals

            if (newPlayPosition.getY() == lastPlayPosition.getY()+1 && newPlayPosition.getX() == lastPlayPosition.getX()+1
            ||
                newPlayPosition.getY() == lastPlayPosition.getY()-1 && newPlayPosition.getX() == lastPlayPosition.getX()-1
            ||
                newPlayPosition.getY() == lastPlayPosition.getY()+1 && newPlayPosition.getX() == lastPlayPosition.getX()-1
            ||
                newPlayPosition.getY() == lastPlayPosition.getY()-1 && newPlayPosition.getX() == lastPlayPosition.getX()+1
                ) {
                return true;
            }

        }
        return false;
    }

    public String getPlayer() {
        return this.player;
    }

    public Vector<Play<C>> getPlays() {
        return this.plays;
    }
}
