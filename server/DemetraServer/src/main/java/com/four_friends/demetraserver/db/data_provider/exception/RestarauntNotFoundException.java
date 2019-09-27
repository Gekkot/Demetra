package com.four_friends.demetraserver.db.data_provider.exception;

import com.four_friends.demetraserver.entity.Restaraunt;

/**
 *
 * @author gekko
 */
public class RestarauntNotFoundException extends EntityNotFoundException {

    public RestarauntNotFoundException(Long id) {
        super(Restaraunt.class, id);
    }
    
       public RestarauntNotFoundException() {
        super(Restaraunt.class);
    }

}
