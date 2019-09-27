package com.four_friends.demetraserver.entity;

import com.j256.ormlite.field.DataType;
import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

/**
 *
 * @author gekko
 */
@DatabaseTable
public class Profile extends Entity{
 
    
    @DatabaseField
    String name;
    
    Set<FoodTag> favoriteFoodTags = new HashSet<>();
    
    @DatabaseField(dataType = DataType.SERIALIZABLE)
    private Long[] foodIds = new Long[]{};

    public Profile() {
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
