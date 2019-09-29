/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.four_friends.demetraserver.http.jetty.servlets;

import com.four_friends.demetraserver.cache.RestarauntCache;
import com.four_friends.demetraserver.db.test_data_generator.MenuGenerator;
import com.four_friends.demetraserver.entity.food_params.Menu;
import com.four_friends.demetraserver.entity.food_params.MenuAnswer;
import com.four_friends.demetraserver.http.jetty.HttpHelper;
import com.google.gson.JsonElement;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author gekko
 */
public class MenuServlet extends CachingServlets{
    
    public MenuServlet(RestarauntCache restarauntCache) {
        super(restarauntCache);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Menu createMenu = MenuGenerator.createMenu();
        JsonElement createJsonObject = MenuAnswer.createJsonObject(createMenu);
        HttpHelper.answerJson(resp, createJsonObject);
        return;
        
    }
    
    
}
