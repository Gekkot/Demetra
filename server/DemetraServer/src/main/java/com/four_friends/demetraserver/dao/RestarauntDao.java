/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.four_friends.demetraserver.dao;

import com.four_friends.demetraserver.db.data_provider.exception.RestarauntNotFoundException;
import com.four_friends.demetraserver.entity.CityMall;
import com.four_friends.demetraserver.entity.Restaraunt;
import com.j256.ormlite.stmt.QueryBuilder;
import com.j256.ormlite.support.ConnectionSource;
import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author gekko
 */
public class RestarauntDao extends EntityDao<Restaraunt> {

    public RestarauntDao(ConnectionSource connectionSource) throws SQLException {
        super(Restaraunt.class, connectionSource);
    }
      public Restaraunt createRestaraunt(Restaraunt restaraunt) throws SQLException, RestarauntNotFoundException {
        CreateOrUpdateStatus createOrUpdate = this.createOrUpdate(restaraunt);
        QueryBuilder<Restaraunt, Long> qb = this.queryBuilder();
        List<Restaraunt> foundClusters = qb.where().eq("name", restaraunt.getName()).query();
        Restaraunt foundInserted = foundClusters.stream().sorted((o1, o2) -> {
            return Long.compare(o2.getId(), o1.getId());
        }).findFirst().orElse(null);
        if (foundInserted != null) {
            restaraunt.setId(foundInserted.getId());
            return foundInserted;
        }
        throw new RestarauntNotFoundException();
    }
}
