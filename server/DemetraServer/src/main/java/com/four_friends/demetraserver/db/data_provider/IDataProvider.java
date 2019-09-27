package com.four_friends.demetraserver.db.data_provider;

import com.four_friends.demetraserver.db.data_provider.exception.CityMallNotFoundException;
import com.four_friends.demetraserver.db.data_provider.exception.FoodTagNotFoundException;
import com.four_friends.demetraserver.db.data_provider.exception.OwnerNotFoundException;
import com.four_friends.demetraserver.db.data_provider.exception.RestarauntNotFoundException;
import com.four_friends.demetraserver.entity.CityMall;
import com.four_friends.demetraserver.entity.FoodTag;
import com.four_friends.demetraserver.entity.Owner;
import com.four_friends.demetraserver.entity.Restaraunt;
import java.util.List;

/**
 *
 * @author gekko
 */
public interface IDataProvider {
    
    CityMall getCityMall(Long id) throws CityMallNotFoundException;
    Owner getOwner(Long id) throws OwnerNotFoundException;
    Restaraunt getRestaraunt(Long id) throws RestarauntNotFoundException;
    FoodTag getFoodTag(Long id) throws FoodTagNotFoundException;
    
    List<CityMall> getCityMalls();
    List<Owner> getOwners();
    List<Restaraunt> getRestaraunts();
    List<FoodTag> getFoodTags();
    
    CityMall addCityMall(CityMall cityMall) throws CityMallNotFoundException;
    Owner addOwner(Owner owner) throws OwnerNotFoundException;
    Restaraunt addRestaraunt(Restaraunt restaraunt) throws RestarauntNotFoundException;
    FoodTag addFoodTag(FoodTag foodTag) throws FoodTagNotFoundException;
    
    
    List<CityMall> getCityMallWithClusterIndex(Long id);
}
