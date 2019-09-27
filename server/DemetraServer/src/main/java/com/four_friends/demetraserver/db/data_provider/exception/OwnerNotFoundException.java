package com.four_friends.demetraserver.db.data_provider.exception;

import com.four_friends.demetraserver.entity.Owner;

/**
 *
 * @author gekko
 */
public class OwnerNotFoundException extends EntityNotFoundException{
    
    public OwnerNotFoundException(Long id) {
        super(Owner.class, id);
    }

    public OwnerNotFoundException() {
        super(Owner.class);
    }
    
}
