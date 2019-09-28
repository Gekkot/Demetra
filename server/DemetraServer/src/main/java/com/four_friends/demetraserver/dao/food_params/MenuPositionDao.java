/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.four_friends.demetraserver.dao.food_params;

import com.four_friends.demetraserver.dao.EntityDao;
import com.four_friends.demetraserver.entity.food_params.MenuPosition;
import com.j256.ormlite.support.ConnectionSource;
import java.sql.SQLException;

/**
 *
 * @author gekko
 */
public class MenuPositionDao extends EntityDao<MenuPosition>{
    
    public MenuPositionDao(ConnectionSource connectionSource) throws SQLException {
        super(MenuPosition.class, connectionSource);
    }
    
}
