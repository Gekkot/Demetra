/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.four_friends.demetraserver.entity.food_params;

import com.four_friends.demetraserver.entity.Entity;
import com.j256.ormlite.field.DatabaseField;

/**
 *
 * @author gekko
 */
public class Topping extends Entity {
    
    @DatabaseField
    String name;
    
    @DatabaseField
    String description;
}
