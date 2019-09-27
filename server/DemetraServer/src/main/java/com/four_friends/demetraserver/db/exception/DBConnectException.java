package com.four_friends.demetraserver.db.exception;

import java.sql.SQLException;

/**
 *
 * @author gekko
 */
public class DBConnectException extends SQLException {

    public DBConnectException(String dbType, String dbPath, SQLException e) {
        super("cannot connect to " + dbPath + ":" + e.getMessage());
    }
}
