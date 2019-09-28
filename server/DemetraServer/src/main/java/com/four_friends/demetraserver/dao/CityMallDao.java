/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.four_friends.demetraserver.dao;

import com.four_friends.demetraserver.db.data_provider.exception.CityMallNotFoundException;
import com.four_friends.demetraserver.entity.CityMall;
import com.j256.ormlite.stmt.QueryBuilder;
import com.j256.ormlite.support.ConnectionSource;
import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author gekko
 */
public class CityMallDao extends EntityDao<CityMall>{
    
    public CityMallDao(ConnectionSource connectionSource) throws SQLException {
        super(CityMall.class, connectionSource);
    }
    public CityMall createCityMall(CityMall cityMall) throws SQLException, CityMallNotFoundException{
        this.createOrUpdate(cityMall);
        QueryBuilder<CityMall, Long> qb = this.queryBuilder();
        List<CityMall> foundCityMalls = qb.where().eq("name", cityMall.getName()).and().eq("address", cityMall.getAddress()).query();
        CityMall foundInserted = foundCityMalls.stream().sorted((o1, o2) -> {
            return Long.compare(o2.getId(), o1.getId());
        }).findFirst().orElse(null);
        if (foundInserted != null) {
            cityMall.setId(foundInserted.getId());
            return foundInserted;
        }
        throw new CityMallNotFoundException();
    }
    
    public List<CityMall> cityMallWithClusterIndex(long clusterIndex) throws SQLException {
        QueryBuilder<CityMall, Long> qb = this.queryBuilder();
        return qb.where().eq("clusterId", clusterIndex).query();
    }
    
}
