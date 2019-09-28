package com.four_friends.demetraserver.dao;

import com.four_friends.demetraserver.entity.Entity;
import com.j256.ormlite.dao.BaseDaoImpl;
import com.j256.ormlite.support.ConnectionSource;
import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author gekko
 */
public class EntityDao<T> extends BaseDaoImpl<T, Long> {

    Class entityClass;
    
    public EntityDao(Class entityClass, ConnectionSource connectionSource) throws SQLException {
        super(connectionSource, entityClass);
        this.entityClass = entityClass;
    }
    
      public List<T> getAll() throws SQLException {
        return this.queryForAll();
    }
    
    public T getWithId(long id) throws SQLException {
        return this.queryForId(id);
    }
}
