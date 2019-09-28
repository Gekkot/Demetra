/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.four_friends.demetraserver.dao;

import com.four_friends.demetraserver.db.data_provider.exception.FoodTagNotFoundException;
import com.four_friends.demetraserver.entity.FoodTag;
import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.stmt.QueryBuilder;
import com.j256.ormlite.support.ConnectionSource;
import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author gekko
 */
public class FoodTagDao extends EntityDao<FoodTag> {

    public FoodTagDao(ConnectionSource connectionSource) throws SQLException {
        super(FoodTag.class, connectionSource);
    }

    public FoodTag createFoodTag(FoodTag foodTag) throws SQLException, FoodTagNotFoundException {
        Dao.CreateOrUpdateStatus createOrUpdate = this.createOrUpdate(foodTag);
        QueryBuilder<FoodTag, Long> qb = this.queryBuilder();
        List<FoodTag> foundClusters = qb.where().eq("name", foodTag.getName()).query();
        FoodTag foundInserted = foundClusters.stream().sorted((o1, o2) -> {
            return Long.compare(o2.getId(), o1.getId());
        }).findFirst().orElse(null);
        if (foundInserted != null) {
            foodTag.setId(foundInserted.getId());
            return foundInserted;
        }
        throw new FoodTagNotFoundException();
    }

}
