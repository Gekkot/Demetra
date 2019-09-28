package com.four_friends.demetraserver;

import com.four_friends.demetraserver.cache.RestarauntCache;
import com.four_friends.demetraserver.db.data_provider.IDataProvider;
import com.four_friends.demetraserver.db.sqlite.SqliteDBConnect;
import com.four_friends.demetraserver.db.sqlite.SqliteDataProvider;
import com.four_friends.demetraserver.http.jetty.OwnHttpServer;
import com.j256.ormlite.support.ConnectionSource;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author gekko
 */
public class Main {
    public static void main(String[] args) {
        try {
            SqliteDBConnect sqliteDBConnect = new SqliteDBConnect();
            sqliteDBConnect.createDB();
            ConnectionSource connectionSource = sqliteDBConnect.createConnectionSource();
            IDataProvider dataProvider = new SqliteDataProvider(connectionSource);
            RestarauntCache restarauntCache = new RestarauntCache(null);
            OwnHttpServer ownHttpServer = new OwnHttpServer(restarauntCache);
            ownHttpServer.start();
        } catch (Exception ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
