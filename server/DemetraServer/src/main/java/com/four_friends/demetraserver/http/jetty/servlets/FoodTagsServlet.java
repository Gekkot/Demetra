/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.four_friends.demetraserver.http.jetty.servlets;

import com.four_friends.demetraserver.cache.RestarauntCache;
import com.four_friends.demetraserver.db.test_data_generator.FoodTagGenerator;
import com.four_friends.demetraserver.entity.FoodTag;
import com.four_friends.demetraserver.http.jetty.HttpHelper;
import java.io.IOException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author gekko
 */
public class FoodTagsServlet extends CachingServlets {

    public FoodTagsServlet(RestarauntCache restarauntCache) {
        super(restarauntCache);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<FoodTag> allFoodTags = restarauntCache.getFoodTags();
        HttpHelper.answerEntities(resp, allFoodTags);
    }
    
    
}
