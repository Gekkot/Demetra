/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.four_friends.demetraserver.dao;

import com.four_friends.demetraserver.entity.FoodTag;
import com.j256.ormlite.support.ConnectionSource;
import java.sql.SQLException;

/**
 *
 * @author gekko
 */
public class FoodTagDao extends EntityDao<FoodTag> {
    
    public FoodTagDao(ConnectionSource connectionSource) throws SQLException {
        super(FoodTag.class, connectionSource);
    }
    
}
