/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.four_friends.demetraserver.http.jetty.servlets;

import com.four_friends.demetraserver.cache.RestarauntCache;
import com.four_friends.demetraserver.db.data_provider.exception.CityMallNotFoundException;
import com.four_friends.demetraserver.entity.CityMall;
import com.four_friends.demetraserver.entity.Owner;
import com.four_friends.demetraserver.http.jetty.HttpHelper;
import com.four_friends.demetraserver.util.LocationHelper;
import com.four_friends.demetraserver.util.exception.WrongLocationException;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author gekko
 */
public class CityMallServlet extends CachingServlets{
    
    public CityMallServlet(RestarauntCache restarauntCache) {
        super(restarauntCache);
    }
    
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Map<String, String[]> parameterMap = req.getParameterMap();
        boolean hasLatKey = (parameterMap.containsKey("lat"));
        boolean hasLongKey = (parameterMap.containsKey("long"));
        if (hasLatKey && hasLongKey) {
            try {
                String latString = parameterMap.get("lat")[0];
                String longString = parameterMap.get("long")[0];
                double latValue = Double.parseDouble(latString);
                double longValue = Double.parseDouble(longString);
                long locationToClusterIndex = LocationHelper.LocationToClusterIndex(latValue, longValue);
                //TODO: replace with sql query.
                List<CityMall> collect = restarauntCache.getCityMalls().stream().filter((cityMall) -> {
                    return cityMall.getClusterId() == locationToClusterIndex;
                }).collect(Collectors.toList());
                HttpHelper.answerEntities(resp, collect);
                return;

            } catch (NumberFormatException | ArrayIndexOutOfBoundsException | NullPointerException | WrongLocationException ex) {
                HttpHelper.answerError(resp, ex);
                return;
            }
        }
        if (parameterMap.containsKey("id")) {
            try {
                String idString = parameterMap.get("id")[0];
                long id = Long.parseLong(idString);
                CityMall cityMall = restarauntCache.getCityMall(id);
                HttpHelper.answerEntity(resp, cityMall);
                return;
            } catch (ArrayIndexOutOfBoundsException | NumberFormatException | CityMallNotFoundException ex) {
                HttpHelper.answerError(resp, ex);
                return;
            } catch (Exception ex) {
                HttpHelper.answerError(resp, ex);
                return;
            }
        }
        try {
            List<CityMall> allCityMalls = restarauntCache.getCityMalls();
            HttpHelper.answerEntities(resp, allCityMalls);
        } catch (Exception e) {
            HttpHelper.answerError(resp, e);
        }
    }
    
    
}
