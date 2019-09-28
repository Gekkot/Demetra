/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.four_friends.demetraserver.http.jetty.servlets;

import com.four_friends.demetraserver.cache.RestarauntCache;
import javax.servlet.http.HttpServlet;

/**
 *
 * @author gekko
 */
public class CachingServlets extends HttpServlet{
    RestarauntCache restarauntCache;

    public CachingServlets(RestarauntCache restarauntCache) {
        this.restarauntCache = restarauntCache;
    }
    
    
}
