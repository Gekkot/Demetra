/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.four_friends.demetraserver.entity;

import com.j256.ormlite.field.DatabaseField;
import java.io.Serializable;

/**
 *
 * @author gekko
 */
public class Entity implements Serializable {
    
    

    @DatabaseField(generatedId = true)
    private long id;

    public Entity() {
    }
    

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }
}
