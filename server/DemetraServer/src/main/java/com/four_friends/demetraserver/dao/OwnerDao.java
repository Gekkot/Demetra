/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.four_friends.demetraserver.dao;

import com.four_friends.demetraserver.entity.Owner;
import com.j256.ormlite.support.ConnectionSource;
import java.sql.SQLException;

/**
 *
 * @author gekko
 */
public class OwnerDao extends EntityDao<Owner>{
    
    public OwnerDao(ConnectionSource connectionSource) throws SQLException {
        super(Owner.class, connectionSource);
    }
    
}
