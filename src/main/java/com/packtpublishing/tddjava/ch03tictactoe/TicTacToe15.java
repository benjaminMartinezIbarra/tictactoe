package com.packtpublishing.tddjava.ch03tictactoe;

import com.packtpublishing.tddjava.ch03tictactoe.mongo.TiTacToeRepository;
import com.packtpublishing.tddjava.ch03tictactoe.mongo.TicTacToeBean;

import java.net.UnknownHostException;

/**
 * @author benjaminmartinez
 * Date: 2019-04-25
 */
public class TicTacToe15 extends TicTacToe<Integer> {

    boolean[] possibleValues = {false, false, false, false, false, false, false, false, false};

    public TicTacToe15(final int dimension, TiTacToeRepository repository) throws UnknownHostException {
        super(dimension, repository);
    }

    @Override
    protected boolean isWinning(final PlaySet<Integer> completed) {
        final int totalOfTheSet = completed.getPlays().stream()
                                                 .mapToInt(play -> play.getValue())
                                                 .sum();

        return totalOfTheSet == 15;
    }

    @Override
    protected void beforeVerifyPlacement(TicTacToeBean<Integer> bean) {
        verifyRangeValue(bean.getValue());
        verifyValueAlreadyTaken(bean.getValue());

    }

    @Override
    protected void afterVerifyPlacement(TicTacToeBean<Integer> bean) {
        possibleValues[bean.getValue().intValue() - 1] = true;
    }

    private void verifyRangeValue(final Integer value) {
        if (value < 1 || value > possibleValues.length) {
            throw new RuntimeException(String.format("Value %s is not in the range of supported values (1..9)", value));
        }
    }

    private void verifyValueAlreadyTaken(final Integer value) {
        if (possibleValues[value.intValue() - 1]) {
            throw new RuntimeException(String.format("\"Value %s is already taken\"", value));
        }
    }

}
