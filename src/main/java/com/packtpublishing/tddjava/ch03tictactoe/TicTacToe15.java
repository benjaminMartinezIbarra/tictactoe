package com.packtpublishing.tddjava.ch03tictactoe;

/**
 * @author benjaminmartinez
 * Date: 2019-04-25
 */
public class TicTacToe15 extends TicTacToe<Integer> {

    boolean[] possibleValues = {false, false, false, false, false, false, false, false, false};

    public TicTacToe15(final int dimension) {
        super(dimension);
    }

    @Override
    protected boolean isWinning(final PlaySet<Integer> completed) {
        final int totalOfTheSet = completed.getPlays().stream()
                                                 .mapToInt(play -> play.getValue())
                                                 .sum();

        return totalOfTheSet == 15;
    }

    @Override
    protected void beforeVerifyPlacement(int x, int y, Integer value) {
        verifyRangeValue(value);
        verifyValueAlreadyTaken(value);

    }

    @Override
    protected void afterVerifyPlacement(int x, int y, Integer value) {
        possibleValues[value.intValue() - 1] = true;
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
