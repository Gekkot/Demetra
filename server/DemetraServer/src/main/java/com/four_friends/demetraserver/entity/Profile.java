/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.four_friends.demetraserver.entity;

import com.j256.ormlite.field.DataType;
import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;

/**
 *
 * @author gekko
 */
@DatabaseTable
public class Profile {
    
    @DatabaseField(generatedId = true)
    private long id;
    
    @DatabaseField
    String name;
    
    Set<FoodTag> favoriteFoodTags = new HashSet<>();
    
    @DatabaseField(dataType = DataType.SERIALIZABLE)
    private Long[] foodIds = new Long[]{};

    public Profile() {
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

    public Long[] getFoodIds() {
        return foodIds;
    }

    public void setFoodIds(Long[] foodIds) {
        this.foodIds = foodIds;
    }
    
    
    
    public void addFoodTag(FoodTag foodTag){
        favoriteFoodTags.add(foodTag);
    }
    
    private void updateFoodTagIds() {
        Long[] updatedfavoriteFoodTags = this.favoriteFoodTags
                .stream()
                .filter(Objects::nonNull)
                .map((currentFoodTag) -> {
                    return currentFoodTag.getId();
                }).toArray(Long[]::new);
        setFoodIds(updatedfavoriteFoodTags);
    }
    
}
