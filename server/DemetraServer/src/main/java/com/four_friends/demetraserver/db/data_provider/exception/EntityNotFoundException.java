package com.four_friends.demetraserver.db.data_provider.exception;

/**
 *
 * @author gekko
 */
public class EntityNotFoundException extends Exception {

    public EntityNotFoundException(Class _class, Long id) {
        super(_class.getSimpleName() + " with id=" + id + " not found");
    }

    public EntityNotFoundException(Class _class) {
        super(_class.getSimpleName() + "not found");
    }
    
}
