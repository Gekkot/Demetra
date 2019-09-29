/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.four_friends.demetraserver.http.jetty.servlets;

import com.four_friends.demetraserver.cache.RestarauntCache;
import com.four_friends.demetraserver.db.data_provider.exception.CityMallNotFoundException;
import com.four_friends.demetraserver.db.data_provider.exception.RestarauntNotFoundException;
import com.four_friends.demetraserver.entity.CityMall;
import com.four_friends.demetraserver.entity.Owner;
import com.four_friends.demetraserver.entity.Restaraunt;
import com.four_friends.demetraserver.http.jetty.HttpHelper;
import com.four_friends.demetraserver.util.LocationHelper;
import com.four_friends.demetraserver.util.exception.WrongLocationException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
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
    
    private final static String LAT_KEY = "lat";
    private final static String LONG_KEY = "long";
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Map<String, String[]> parameterMap = req.getParameterMap();
        boolean hasLatKey = (parameterMap.containsKey(LAT_KEY));
        boolean hasLongKey = (parameterMap.containsKey(LONG_KEY));
        if (hasLatKey && hasLongKey) {
            try {
                String latString = parameterMap.get(LAT_KEY)[0];
                String longString = parameterMap.get(LONG_KEY)[0];
                double latValue = Double.parseDouble(latString);
                double longValue = Double.parseDouble(longString);
                long locationToClusterIndex = LocationHelper.LocationToClusterIndex(latValue, longValue);
                List<Long> neighbours = LocationHelper.getNeighbours(locationToClusterIndex);
                neighbours.add(locationToClusterIndex);
                //TODO: replace with sql query.
                List<CityMall> collect = restarauntCache.getCityMalls().stream().filter((cityMall) -> {
                    return neighbours.contains(cityMall.getClusterId());
                }).collect(Collectors.toList());
                List<CityMall> copy = new ArrayList<>();
                for(CityMall cityMall : collect){
                    Long[] restarauntIds = cityMall.getRestarauntIds();
                    for(long restarauntId : restarauntIds){
                        try {
                            System.out.println("try found restaraunt with id "+restarauntId);
                            Restaraunt restaraunt = restarauntCache.getRestaraunt(restarauntId);
                            cityMall.addRestaraunt(restaraunt);
                            System.out.println("restoraunt add "+restarauntId);
                        } catch (RestarauntNotFoundException ex) {
                            Logger.getLogger(CityMallServlet.class.getName()).log(Level.SEVERE, null, ex);
                            System.out.println("error on finding restaraunt "+restarauntId);
                        }
                    }
                    copy.add(cityMall);
                }
                HttpHelper.answerEntities(resp, copy);
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
