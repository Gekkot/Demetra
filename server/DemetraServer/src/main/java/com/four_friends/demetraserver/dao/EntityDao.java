package com.four_friends.demetraserver.dao;

import com.four_friends.demetraserver.entity.Entity;
import com.j256.ormlite.dao.BaseDaoImpl;
import com.j256.ormlite.support.ConnectionSource;
import java.sql.SQLException;

/**
 *
 * @author gekko
 */
public class EntityDao extends BaseDaoImpl<Entity, Long>{
    
     public EntityDao(ConnectionSource connectionSource) throws SQLException {
        super(connectionSource, Entity.class);
    }
}
