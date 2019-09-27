package com.four_friends.demetraserver;

import com.four_friends.demetraserver.http.jetty.OwnHttpServer;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author gekko
 */
public class Main {
    public static void main(String[] args) {
        try {
            OwnHttpServer ownHttpServer = new OwnHttpServer();
            ownHttpServer.start();
        } catch (Exception ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
