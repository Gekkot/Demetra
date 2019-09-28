/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.four_friends.demetraserver.http.jetty.servlets;

import com.four_friends.demetraserver.cache.RestarauntCache;
import com.four_friends.demetraserver.db.test_data_generator.CityMallGenerator;
import com.four_friends.demetraserver.db.test_data_generator.FoodTagGenerator;
import com.four_friends.demetraserver.db.test_data_generator.RestarauntGenerator;
import com.four_friends.demetraserver.entity.FoodTag;
import com.four_friends.demetraserver.entity.Owner;
import com.four_friends.demetraserver.entity.Restaraunt;
import com.four_friends.demetraserver.http.jetty.HttpHelper;
import java.io.IOException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author gekko
 */
public class RestarauntServlet extends CachingServlets{

    public RestarauntServlet(RestarauntCache restarauntCache) {
        super(restarauntCache);
    }
    
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            List<Restaraunt> allRestoraunts= restarauntCache.getRestaraunts();
            HttpHelper.answerEntities(resp, allRestoraunts);
        } catch (Exception e) {
            HttpHelper.answerError(resp, e);
        }
    }
    
}
