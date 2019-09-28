/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.four_friends.demetraserver.dao;

import com.four_friends.demetraserver.db.data_provider.exception.OwnerNotFoundException;
import com.four_friends.demetraserver.entity.Owner;
import com.j256.ormlite.stmt.QueryBuilder;
import com.j256.ormlite.support.ConnectionSource;
import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author gekko
 */
public class OwnerDao extends EntityDao<Owner>{
    
    public OwnerDao(ConnectionSource connectionSource) throws SQLException {
        super(Owner.class, connectionSource);
    }
    
    public Owner createOwner(Owner owner) throws SQLException, OwnerNotFoundException {
        CreateOrUpdateStatus createOrUpdate = this.createOrUpdate(owner);
        QueryBuilder<Owner, Long> qb = this.queryBuilder();
        List<Owner> foundClusters = qb.where().eq("ownerName", owner.getOwnerName()).query();
        Owner foundInserted = foundClusters.stream().sorted((o1, o2) -> {
            return Long.compare(o2.getId(), o1.getId());
        }).findFirst().orElse(null);
        if (foundInserted != null) {
            owner.setId(foundInserted.getId());
            return foundInserted;
        }
        throw new OwnerNotFoundException();
    }
    
}
