/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.four_friends.demetraserver.http.jetty.servlets;

import com.four_friends.demetraserver.cache.RestarauntCache;
import com.four_friends.demetraserver.http.jetty.HttpHelper;
import java.io.IOException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author gekko
 */
public class RequestRestarauntNearestServlet extends CachingServlets{

    public RequestRestarauntNearestServlet(RestarauntCache restarauntCache) {
        super(restarauntCache);
    }
    
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
      HttpHelper.answerError(resp, new UnsupportedOperationException("not implement yet"));
    }

}
