package com.packtpublishing.tddjava.ch03tictactoe;

import java.util.ArrayList;
import java.util.List;

/**
 * @author benjaminmartinez
 * Date: 2019-04-28
 */
public class GameBoard extends Board {

    private List<BoardValueAddedObserver> valueAddedObservers;
    private List<TicTacToeBoardFullObserver> boardFullObservers;

    public GameBoard(final int dimension) {
        super(dimension);
        valueAddedObservers = new ArrayList<>();
        boardFullObservers = new ArrayList<>();
    }

    public void put(final Position position, final Object value, final String currentPlayer) {
        put(position, value);
        notifyObservers(new BoardValueAddedEvent(new Play(position,
                                                          value, currentPlayer)));
        if(isFull()){
            notifyObservers(new BoardFullEvent());
        }
    }

    private void notifyObservers(final BoardFullEvent event) {
        boardFullObservers.forEach(obs -> obs.update(event));
    }

    private void notifyObservers(final BoardValueAddedEvent event) {
        valueAddedObservers.forEach(obs -> obs.update(event));
    }

    public void addObserver(TicTacToeBoardFullObserver obs) {
        this.boardFullObservers.add(obs);
    }

    public void addObserver(BoardValueAddedObserver obs) {
        this.valueAddedObservers.add(obs);
    }

}
