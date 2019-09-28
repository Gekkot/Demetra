package com.four_friends.demetraserver.db.test_data_generator;

import com.four_friends.demetraserver.entity.CityMall;
import com.four_friends.demetraserver.entity.Owner;
import com.four_friends.demetraserver.entity.Restaraunt;


/**
 *
 * @author gekko
 */
public class RestarauntGenerator {
    public static Restaraunt createRestaunt(CityMall cityMall, Owner owner){
        Restaraunt restaraunt = new Restaraunt(owner);
        restaraunt.setCity(cityMall.getCity());
        restaraunt.setAddress(cityMall.getAddress());
        restaraunt.setCityMollID(cityMall.getId());
        restaraunt.setImageUrl(owner.getLogoUrl());
        restaraunt.setDescription(owner.getOwnerRestarauntName() + " в "+ cityMall.getName());
        return restaraunt;
    }
}
