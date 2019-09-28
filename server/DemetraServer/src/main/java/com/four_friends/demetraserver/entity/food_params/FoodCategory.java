package com.four_friends.demetraserver.entity.food_params;

import com.four_friends.demetraserver.entity.Entity;
import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

/**
 *
 * @author gekko
 */
@DatabaseTable
public class FoodCategory extends Entity {
    @DatabaseField
    String name;
    
    @DatabaseField
    String description;

    public FoodCategory() {
        this("","");
    }

    public FoodCategory(String name) {
        this(name, "");
    }

    public FoodCategory(String name, String description) {
        this.name = name;
        this.description = description;
    }
    
    
    
    
    
}
