package com.four_friends.demetraserver.cache;

import com.four_friends.demetraserver.db.data_provider.IDataProvider;
import com.four_friends.demetraserver.db.data_provider.exception.CityMallNotFoundException;
import com.four_friends.demetraserver.db.data_provider.exception.FoodTagNotFoundException;
import com.four_friends.demetraserver.db.data_provider.exception.OwnerNotFoundException;
import com.four_friends.demetraserver.db.data_provider.exception.RestarauntNotFoundException;
import com.four_friends.demetraserver.entity.CityMall;
import com.four_friends.demetraserver.entity.FoodTag;
import com.four_friends.demetraserver.entity.Owner;
import com.four_friends.demetraserver.entity.Restaraunt;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

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
        initData();
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
       
        public CityMall getCityMall(Long id) throws CityMallNotFoundException {
        if (cityMallCache.containsKey(id)) {
            return cityMallCache.get(id);
        } else {
            CityMall cityMall = dataProvider.getCityMall(id);
            cityMallCache.put(cityMall.getId(), cityMall);
            return cityMall;
        }
    }

    public Owner getOwner(Long id) throws OwnerNotFoundException {
        if (ownerCache.containsKey(id)) {
            return ownerCache.get(id);
        } else {
            Owner owner = dataProvider.getOwner(id);
            ownerCache.put(owner.getId(), owner);
            return owner;
        }
    }

    public Restaraunt getRestaraunt(Long id) throws RestarauntNotFoundException {
        if (restarauntCache.containsKey(id)) {
            return restarauntCache.get(id);
        } else {
            Restaraunt restaraunt = dataProvider.getRestaraunt(id);
            restarauntCache.put(restaraunt.getId(), restaraunt);
            return restaraunt;
        }
    }
  
    public FoodTag getFoodTag(Long id) throws FoodTagNotFoundException {
        if (foodTagCache.containsKey(id)) {
            return foodTagCache.get(id);
        } else {
            FoodTag foodTag = dataProvider.getFoodTag(id);
            foodTagCache.put(foodTag.getId(), foodTag);
            return foodTag;
        }
    }

    public List<Owner> getOwners() {
        return ownerCache.values().stream().collect(Collectors.toList());
    }

    public List<Restaraunt> getRestaraunts() {
        return restarauntCache.values().stream().collect(Collectors.toList());
    }
    
    public List<CityMall> getCityMalls() {
        return cityMallCache.values().stream().collect(Collectors.toList());
    }
    
    public List<FoodTag> getFoodTags() {
        return foodTagCache.values().stream().collect(Collectors.toList());
    }

}
