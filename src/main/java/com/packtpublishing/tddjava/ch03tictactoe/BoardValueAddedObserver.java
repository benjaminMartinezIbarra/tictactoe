package com.packtpublishing.tddjava.ch03tictactoe;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * @author benjaminmartinez
 * Date: 2019-04-28
 */
public class BoardValueAddedObserver implements BoardObserver<BoardValueAddedEvent> {

    TicTacToe game;
    List<PlaySet> sets = new ArrayList();

    public BoardValueAddedObserver(final TicTacToe game) {
        this.game = game;
    }

    @Override
    public void update(final BoardValueAddedEvent event) {
        updatePlayset(event);
        final Optional<PlaySet> playsetWinning = sets.stream()
                                                     .filter(playSet -> playSet.isComplete())
                                                     .filter(playSet -> game.isWinning(playSet))
                                                     .findFirst();

        if (playsetWinning.isPresent()) {
            this.game.winGame(playsetWinning.get().getPlayer());
        }
    }

    private void updatePlayset(final BoardValueAddedEvent event) {
        final List<PlaySet> collect = sets.stream()
                                          .filter(playSet -> playSet.isPossibleAdd(event.getPlay()))
                                          .collect(Collectors.toList());

        if (!collect.isEmpty()) {
            collect.stream()
                   .forEach(playSet -> playSet.addPlay(event.getPlay()));
        } else {
            sets.add(new PlaySet(event.getPlay()));
        }
    }
}

