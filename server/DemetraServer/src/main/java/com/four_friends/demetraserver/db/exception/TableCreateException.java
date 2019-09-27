package com.four_friends.demetraserver.db.exception;

import java.sql.SQLException;

/**
 *
 * @author gekko
 */
public class TableCreateException extends SQLException {

    public TableCreateException(String tableName, SQLException ex) {
        super("cannot create table:" + tableName + ": " + ex.getMessage());
    }
}
