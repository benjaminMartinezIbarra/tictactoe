package com.packtpublishing.tddjava.ch03tictactoe;

/**
 * @author benjaminmartinez
 * Date: 2019-04-28
 */
public interface BoardObserver<C extends BoardEvent> {

    void update(C event);

}
