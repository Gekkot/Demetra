/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.four_friends.demetraserver.dao;

import com.four_friends.demetraserver.entity.Actions;
import com.j256.ormlite.support.ConnectionSource;
import java.sql.SQLException;

/**
 *
 * @author gekko
 */
public class ActionsDao extends EntityDao<Actions>{
    
    public ActionsDao(ConnectionSource connectionSource) throws SQLException {
        super(Actions.class, connectionSource);
    }
    
}
