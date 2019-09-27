package com.four_friends.demetraserver.db.data_provider.exception;

import com.four_friends.demetraserver.entity.FoodTag;

/**
 *
 * @author gekko
 */
public class FoodTagNotFoundException extends EntityNotFoundException {

    public FoodTagNotFoundException(Long id) {
        super(FoodTag.class, id);
    }
    
     public FoodTagNotFoundException() {
        super(FoodTag.class);
    }

}
