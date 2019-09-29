/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.four_friends.demetraserver.db.data_provider.exception;

import com.four_friends.demetraserver.entity.Actions;

/**
 *
 * @author gekko
 */
public class ActionsNotFoundException  extends EntityNotFoundException {
    
    public ActionsNotFoundException(Long id) {
        super(Actions.class, id);
    }

    public ActionsNotFoundException() {
        super(Actions.class);
    }
    
}
