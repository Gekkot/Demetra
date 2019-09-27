package com.four_friends.demetraserver.entity;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

/**
 *
 * @author gekko
 */
@DatabaseTable
public class FoodTag {
    @DatabaseField(generatedId = true)
    private long id;

    @DatabaseField
    private String name;

    public FoodTag() {
    }

    
    public FoodTag(String name) {
        this.name = name;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
