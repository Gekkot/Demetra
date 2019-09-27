package com.four_friends.demetraserver.db.sqlite;

import com.four_friends.demetraserver.db.exception.DBConnectException;
import com.four_friends.demetraserver.db.exception.TableCreateException;
import com.four_friends.demetraserver.entity.CityMall;
import com.four_friends.demetraserver.entity.FoodTag;
import com.four_friends.demetraserver.entity.Owner;
import com.four_friends.demetraserver.entity.Restaraunt;
import com.j256.ormlite.jdbc.JdbcPooledConnectionSource;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.TableUtils;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.sqlite.SQLiteConfig;

/**
 *
 * @author gekko
 */
public class SqliteDBConnect {
     private final static String DB_NAME = "persephone.db";
    private final static String CONNECT_DB_URL = "jdbc:sqlite:" + DB_NAME;

    public boolean createDB() {
        try {
            //Connection connection = connectToDB();
            ConnectionSource connectionSource = createConnectionSource();
            if(connectionSource == null){
            return false;
        }
            createTables(connectionSource);
            return true;
        } catch (DBConnectException ex) {
            Logger.getLogger(SqliteDBConnect.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        } catch (SQLException ex) {
            Logger.getLogger(SqliteDBConnect.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }
    
    public Connection connectToDB() throws DBConnectException{
        Connection conn = null;
        try {
            SQLiteConfig config = new SQLiteConfig();
            config.setEncoding(SQLiteConfig.Encoding.UTF8);
            conn = DriverManager.getConnection(CONNECT_DB_URL, config.toProperties());
            return conn;
        } catch (SQLException ex) {
            throw new DBConnectException("sqlite", DB_NAME, ex);
        }
    }
    
    public ConnectionSource createConnectionSource() throws SQLException{
        return new JdbcPooledConnectionSource(CONNECT_DB_URL);
    }

    void createTables(ConnectionSource connectionSource) throws TableCreateException {
        createTable(connectionSource, Owner.class);
        createTable(connectionSource, CityMall.class);
        createTable(connectionSource, Restaraunt.class);
        createTable(connectionSource, FoodTag.class);
    }
    
    void createTable(ConnectionSource connectionSource,Class tableClass) throws TableCreateException{
        try { 
            TableUtils.dropTable(connectionSource, tableClass, true);
            TableUtils.createTableIfNotExists(connectionSource, tableClass);
        } catch (SQLException ex) {
            throw new TableCreateException(tableClass.getSimpleName(), ex);
        }
    }
}
