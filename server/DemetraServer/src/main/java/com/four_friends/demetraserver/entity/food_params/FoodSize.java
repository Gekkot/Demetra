package com.four_friends.demetraserver.entity.food_params;

import com.four_friends.demetraserver.entity.Entity;
import com.j256.ormlite.field.DatabaseField;

/**
 *
 * @author gekko
 */
public class FoodSize extends Entity {

    @DatabaseField
    String name;

    @DatabaseField
    long id;
}
