/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.four_friends.demetraserver.dao;

import com.four_friends.demetraserver.entity.CityMall;
import com.j256.ormlite.support.ConnectionSource;
import java.sql.SQLException;

/**
 *
 * @author gekko
 */
public class CityMallDao extends EntityDao<CityMall>{
    
    public CityMallDao(ConnectionSource connectionSource) throws SQLException {
        super(CityMall.class, connectionSource);
    }
    
}
