package com.four_friends.demetraserver.cache;

import com.four_friends.demetraserver.db.data_provider.IDataProvider;
import com.four_friends.demetraserver.entity.CityMall;
import com.four_friends.demetraserver.entity.FoodTag;
import com.four_friends.demetraserver.entity.Owner;
import com.four_friends.demetraserver.entity.Restaraunt;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * @author gekko
 */
public class RestarauntCache {

    IDataProvider dataProvider;

    private Map<Long, CityMall> cityMallCache = new HashMap<>();
    private Map<Long, Owner> ownerCache = new HashMap<>();
    private Map<Long, Restaraunt> restarauntCache = new HashMap<>();
    private Map<Long, FoodTag> foodTagCache = new HashMap<>();

    public RestarauntCache(IDataProvider dataProvider) {
        this.dataProvider = dataProvider;
    }
    
       private void initData() {
        List<CityMall> cityMalls = dataProvider.getCityMalls();
        cityMalls.forEach((t) -> {
            cityMallCache.put(t.getId(), t);
        });

        List<Owner> ownerList = dataProvider.getOwners();
        ownerList.forEach((t) -> {
            ownerCache.put(t.getId(), t);
        });
        
        List<Restaraunt> restarauntList = dataProvider.getRestaraunts();
        restarauntList.forEach((t) -> {
            restarauntCache.put(t.getId(), t);
        });
        
        List<FoodTag> foodTagsList = dataProvider.getFoodTags();
        foodTagsList.forEach((t) -> {
            foodTagCache.put(t.getId(), t);
        });
    }

}
