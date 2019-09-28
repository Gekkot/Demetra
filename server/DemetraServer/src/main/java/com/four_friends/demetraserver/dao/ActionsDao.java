/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.four_friends.demetraserver.dao;

import com.four_friends.demetraserver.db.data_provider.exception.ActionsNotFoundException;
import com.four_friends.demetraserver.db.data_provider.exception.CityMallNotFoundException;
import com.four_friends.demetraserver.entity.Actions;
import com.four_friends.demetraserver.entity.CityMall;
import com.j256.ormlite.stmt.QueryBuilder;
import com.j256.ormlite.support.ConnectionSource;
import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author gekko
 */
public class ActionsDao extends EntityDao<Actions>{
    
    public ActionsDao(ConnectionSource connectionSource) throws SQLException {
        super(Actions.class, connectionSource);
    }
     public Actions createAction(Actions actions) throws SQLException, ActionsNotFoundException{
        this.createOrUpdate(actions);
        QueryBuilder<Actions, Long> qb = this.queryBuilder();
        List<Actions> actionsCityMalls = qb.where()
                .eq("startDate", actions.getStartDate())
                .and()
                .eq("endDate", actions.getEndDate())
                .and()
                .eq("shortDescription", actions.getShortDescription())
                .query();
        Actions foundInserted = actionsCityMalls.stream().sorted((o1, o2) -> {
            return Long.compare(o2.getId(), o1.getId());
        }).findFirst().orElse(null);
        if (foundInserted != null) {
            actions.setId(foundInserted.getId());
            return foundInserted;
        }
        throw new ActionsNotFoundException();
    }
    
}
