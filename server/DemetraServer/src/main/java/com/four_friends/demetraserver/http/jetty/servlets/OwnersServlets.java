/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.four_friends.demetraserver.http.jetty.servlets;

import com.four_friends.demetraserver.cache.RestarauntCache;
import com.four_friends.demetraserver.db.data_provider.exception.OwnerNotFoundException;
import com.four_friends.demetraserver.db.data_provider.exception.RestarauntNotFoundException;
import com.four_friends.demetraserver.entity.FoodTag;
import com.four_friends.demetraserver.entity.Owner;
import com.four_friends.demetraserver.entity.Restaraunt;
import com.four_friends.demetraserver.http.jetty.HttpHelper;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author gekko
 */
public class OwnersServlets extends CachingServlets {

    public OwnersServlets(RestarauntCache restarauntCache) {
        super(restarauntCache);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Map<String, String[]> parameterMap = req.getParameterMap();
        if (parameterMap.containsKey("id")) {
            try {
                String idString = parameterMap.get("id")[0];
                long id = Long.parseLong(idString);
                Owner owner = restarauntCache.getOwner(id);
                HttpHelper.answerEntity(resp, owner);
                return;
            } catch (ArrayIndexOutOfBoundsException | NumberFormatException | OwnerNotFoundException ex) {
                HttpHelper.answerError(resp, ex);
                return;
            } catch (Exception ex) {
                HttpHelper.answerError(resp, ex);
                return;
            }
        }
        try {
            List<Owner> allOwners = restarauntCache.getOwners();
            HttpHelper.answerEntities(resp, allOwners);
        } catch (Exception e) {
            HttpHelper.answerError(resp, e);
        }
    }

}
