package com.packtpublishing.tddjava.ch03tictactoe.mongo;

import com.mongodb.MongoException;
import org.jongo.MongoCollection;
import org.junit.Before;
import org.junit.Test;

import java.net.UnknownHostException;

import static junit.framework.TestCase.assertEquals;
import static junit.framework.TestCase.assertTrue;
import static org.junit.Assert.assertFalse;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

/**
 * @author benjaminmartinez
 * Date: 2019-05-10
 */
public class TicTacToeCollectionSpec {

    TiTacToeRepository tiTacToeRepository;
    TicTacToeBean<String> bean;
    MongoCollection mongoCollection;

    @Before
    public void setup() throws UnknownHostException {
        tiTacToeRepository = spy(new TiTacToeRepository());
        mongoCollection = mock(MongoCollection.class);
        bean = new TicTacToeBean(3, 2, 1, "Player1","Test");
    }

    @Test
    public void whenInstantiatedThenMongoHasDbNameTicTacToe() {

        assertEquals(
                "tic-tac-toe",
                tiTacToeRepository.getMongoCollection().getDBCollection().getDB().getName());
    }

    @Test
    public void whenInstantiatedThenMongoCollectionHasNameGame() {
        assertEquals(
                "game",
                tiTacToeRepository.getMongoCollection().getDBCollection().getName());
    }

    @Test
    public void whenSaveMoveThenInvokeMongoCollectionSave() {
        doReturn(mongoCollection).when(tiTacToeRepository).getMongoCollection();

        tiTacToeRepository.saveMove(bean);

        verify(mongoCollection, times(1)).save(bean);
    }

    @Test
    public void whenSaveMoveThenReturnTrue() {
        doReturn(mongoCollection).when(tiTacToeRepository).getMongoCollection();
        assertTrue(tiTacToeRepository.saveMove(bean));
    }

    @Test
    public void whenGameIsFinishedCollectionIsDropped() {
        doReturn(mongoCollection).when(tiTacToeRepository).getMongoCollection();
        assertTrue(tiTacToeRepository.drop());
    }

    @Test
    public void givenExceptionWhenCollectionIsDroppedThenReturnFalse() {
        doThrow(new MongoException("yo")).when(mongoCollection).drop();
        doReturn(mongoCollection).when(tiTacToeRepository).getMongoCollection();
        assertFalse(tiTacToeRepository.drop());
        verify(mongoCollection).drop();
    }

    @Test
    public void givenExceptionWhenSaveMoveThenReturnFalse() {
        doThrow(new MongoException("Bla"))
                .when(mongoCollection).save(any(TicTacToeBean.class));
        doReturn(mongoCollection).when(tiTacToeRepository).getMongoCollection();
        assertFalse(tiTacToeRepository.saveMove(bean));

    }

}
