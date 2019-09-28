package com.four_friends.demetraserver.entity.food_params;

import com.four_friends.demetraserver.entity.Entity;
import com.j256.ormlite.field.DatabaseField;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author gekko
 */
public class MenuPosition extends Entity {
    
    @DatabaseField
    String name;
    
    @DatabaseField
    String description;
    
    List<Topping> toppings  = new ArrayList<>();
    
    @DatabaseField
    Long[] toppingsIds;
    
}
