/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.four_friends.demetraserver.http.jetty.servlets;

import com.four_friends.demetraserver.http.jetty.HttpHelper;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author gekko
 */
public class RequestRestarauntNearestServlet extends HttpServlet{
    
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) {
        try {
            
        } catch (Exception e) {
            try {
                HttpHelper.answerError(resp, e);
            } catch (Exception ex) {
                //
            }

        }
    }

}
