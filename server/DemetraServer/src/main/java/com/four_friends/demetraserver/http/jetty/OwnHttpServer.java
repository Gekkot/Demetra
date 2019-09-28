/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.four_friends.demetraserver.http.jetty;

import com.four_friends.demetraserver.cache.RestarauntCache;
import com.four_friends.demetraserver.http.jetty.servlets.FoodTagsServlet;
import com.four_friends.demetraserver.http.jetty.servlets.RequestRestarauntNearestServlet;
import org.eclipse.jetty.server.Connector;
import org.eclipse.jetty.server.HttpConfiguration;
import org.eclipse.jetty.server.HttpConnectionFactory;
import org.eclipse.jetty.server.SecureRequestCustomizer;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.server.ServerConnector;
import org.eclipse.jetty.server.SslConnectionFactory;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;
import org.eclipse.jetty.util.ssl.SslContextFactory;

/**
 *
 * @author gekko
 */
public class OwnHttpServer {
    
    private final static int HTTP_PORT = 4004;
    private final static int HTTPS_PORT = 4005;
    
    RestarauntCache restarauntCache;

    public OwnHttpServer(RestarauntCache restarauntCache) {
        this.restarauntCache = restarauntCache;
    }

    private Server server;

    public void start() throws Exception {
        server = new Server();
        ServerConnector connector = new ServerConnector(server);
        connector.setPort(HTTP_PORT);
        
        HttpConfiguration https = new HttpConfiguration();
        https.addCustomizer(new SecureRequestCustomizer());

        SslContextFactory sslContextFactory = new SslContextFactory();
        ServerConnector sslConnector = new ServerConnector(server, new SslConnectionFactory(sslContextFactory, "http/1.1"), new HttpConnectionFactory(https));
            sslConnector.setPort(HTTPS_PORT);

        server.setConnectors(new Connector[]{connector});   
        
        ServletContextHandler context = new ServletContextHandler(ServletContextHandler.SESSIONS);
        
        context.addServlet(new ServletHolder(new RequestRestarauntNearestServlet(restarauntCache)), "/nearest");
        context.addServlet(new ServletHolder(new FoodTagsServlet(restarauntCache)), "/food_tags");
        server.setHandler(context);
        
        server.start();
    }
}
