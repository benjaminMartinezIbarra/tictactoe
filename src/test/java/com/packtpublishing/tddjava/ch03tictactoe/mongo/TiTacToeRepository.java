package com.packtpublishing.tddjava.ch03tictactoe.mongo;

import com.mongodb.DB;
import com.mongodb.MongoClient;
import com.mongodb.MongoException;
import org.jongo.Jongo;
import org.jongo.MongoCollection;

import java.net.UnknownHostException;

/**
 * @author benjaminmartinez
 * Date: 2019-05-10
 */
public class TiTacToeRepository {

    private final MongoCollection mongoCollection;

    public TiTacToeRepository() throws UnknownHostException {
        DB db = new MongoClient().getDB("tic-tac-toe");
        mongoCollection = new Jongo(db).getCollection("game");
    }

    public MongoCollection getMongoCollection() {
        return mongoCollection;
    }

    public boolean saveMove(final TicTacToeBean bean) {
        try {
            getMongoCollection().save(bean);
            return true;
        } catch (MongoException exception) {
            return false;
        }
    }

    public boolean drop() {
        try {
            getMongoCollection().drop();
            return true;
        } catch (MongoException ex) {
            return false;
        }

    }
}
