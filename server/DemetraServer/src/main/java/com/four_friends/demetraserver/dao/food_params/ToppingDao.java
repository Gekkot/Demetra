/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.four_friends.demetraserver.dao.food_params;

import com.four_friends.demetraserver.dao.EntityDao;
import com.four_friends.demetraserver.entity.food_params.Topping;
import com.j256.ormlite.support.ConnectionSource;
import java.sql.SQLException;

/**
 *
 * @author gekko
 */
public class ToppingDao extends EntityDao<Topping>{
    
    public ToppingDao(ConnectionSource connectionSource) throws SQLException {
        super(Topping.class, connectionSource);
    }
    
}
