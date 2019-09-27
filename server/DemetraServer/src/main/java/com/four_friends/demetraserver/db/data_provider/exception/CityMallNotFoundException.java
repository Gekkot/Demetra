package com.four_friends.demetraserver.db.data_provider.exception;

import com.four_friends.demetraserver.entity.CityMall;

/**
 *
 * @author gekko
 */
public class CityMallNotFoundException extends EntityNotFoundException {

    public CityMallNotFoundException(Long id) {
        super(CityMall.class, id);
    }

    public CityMallNotFoundException() {
        super(CityMall.class);
    }
    
    

}
