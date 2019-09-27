package com.four_friends.demetraserver.entity;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

/**
 *
 * @author gekko
 */
@DatabaseTable
public class FoodTag extends Entity {

    @DatabaseField
    private String name;

    public FoodTag() {
    }

    
    public FoodTag(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
