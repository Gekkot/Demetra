package com.four_friends.demetraserver.db.sqlite;

import com.four_friends.demetraserver.dao.CityMallDao;
import com.four_friends.demetraserver.dao.FoodTagDao;
import com.four_friends.demetraserver.dao.OwnerDao;
import com.four_friends.demetraserver.dao.RestarauntDao;
import com.four_friends.demetraserver.db.data_provider.IDataProvider;
import com.four_friends.demetraserver.db.data_provider.exception.CityMallNotFoundException;
import com.four_friends.demetraserver.db.data_provider.exception.FoodTagNotFoundException;
import com.four_friends.demetraserver.db.data_provider.exception.OwnerNotFoundException;
import com.four_friends.demetraserver.db.data_provider.exception.RestarauntNotFoundException;
import com.four_friends.demetraserver.entity.CityMall;
import com.four_friends.demetraserver.entity.FoodTag;
import com.four_friends.demetraserver.entity.Owner;
import com.four_friends.demetraserver.entity.Restaraunt;
import com.j256.ormlite.support.ConnectionSource;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author gekko
 */
public class SqliteDataProvider implements IDataProvider{
     ConnectionSource connectionSource;
    private CityMallDao cityMallDAO = null;
    private OwnerDao ownerDAO = null;
    private RestarauntDao restarauntDAO = null;
    private FoodTagDao foodTagDAO = null;

    public SqliteDataProvider(ConnectionSource connectionSource) {
        this.connectionSource = connectionSource;
        try {
            initDAO();
        } catch (SQLException ex) {
            Logger.getLogger(SqliteDataProvider.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    private void initDAO() throws SQLException {
        cityMallDAO = new CityMallDao(connectionSource);
        ownerDAO = new OwnerDao(connectionSource);
        restarauntDAO = new RestarauntDao(connectionSource);
        foodTagDAO = new FoodTagDao(connectionSource);
    }

    @Override
    public List<CityMall> getCityMalls() {
        List<CityMall> emptyList = new ArrayList<>();
        if (cityMallDAO != null) {
            try {
                return cityMallDAO.getAll();
            } catch (SQLException ex) {
                return emptyList;
            }
        }
        return emptyList;
    }

    @Override
    public CityMall getCityMall(Long id) throws CityMallNotFoundException {
        CityMallNotFoundException cityMallNotFoundException = new CityMallNotFoundException(id);
        if (cityMallDAO != null) {
            try {
                CityMall withId = cityMallDAO.getWithId(id);
                if (withId == null) {
                    throw cityMallNotFoundException;
                }
                return withId;
            } catch (SQLException ex) {
                throw cityMallNotFoundException;
            }
        }
        throw cityMallNotFoundException;
    }

    @Override
    public Owner getOwner(Long id) throws OwnerNotFoundException {
        OwnerNotFoundException ownerNotFoundException = new OwnerNotFoundException(id);
        if (ownerDAO != null) {
            try {
                Owner withId = ownerDAO.getWithId(id);
                if (withId == null) {
                    throw ownerNotFoundException;
                }
                return withId;
            } catch (SQLException ex) {
                throw ownerNotFoundException;
            }
        }
        throw ownerNotFoundException;
    }

    @Override
    public Restaraunt getRestaraunt(Long id) throws RestarauntNotFoundException {
        RestarauntNotFoundException restarauntNotFoundException = new RestarauntNotFoundException(id);

        if (restarauntDAO != null) {
            try {
                Restaraunt withId = restarauntDAO.getWithId(id);
                if (withId == null) {
                    throw restarauntNotFoundException;

                }
                return withId;
            } catch (SQLException ex) {
                throw restarauntNotFoundException;
            }
        }
        throw restarauntNotFoundException;
    }

    @Override
    public List<Owner> getOwners() {
        List<Owner> empty = new ArrayList<>();
        if (ownerDAO != null) {
            try {
                return ownerDAO.getAll();
            } catch (SQLException ex) {
                return empty;
            }
        }
        return empty;
    }

    @Override
    public List<Restaraunt> getRestaraunts() {
        List<Restaraunt> empty = new ArrayList<>();
        if (restarauntDAO != null) {
            try {
                return restarauntDAO.getAll();
            } catch (SQLException ex) {
                return empty;
            }
        }
        return empty;
    }

    @Override
    public CityMall addCityMall(CityMall cityMall) throws CityMallNotFoundException {
        if (cityMallDAO != null) {
            try {
                return cityMallDAO.createCityMall(cityMall);
            } catch (SQLException ex) {
                Logger.getLogger(SqliteDataProvider.class.getName()).log(Level.SEVERE, null, ex);
                throw new CityMallNotFoundException();
            }
        }
        throw new CityMallNotFoundException();
    }

    @Override
    public Owner addOwner(Owner owner) throws OwnerNotFoundException {
        if (ownerDAO != null) {
            try {
                return ownerDAO.createOwner(owner);
            } catch (SQLException ex) {
                Logger.getLogger(SqliteDataProvider.class.getName()).log(Level.SEVERE, null, ex);
                throw new OwnerNotFoundException();
            }
        }
        throw new OwnerNotFoundException();
    }

    @Override
    public FoodTag getFoodTag(Long id) throws FoodTagNotFoundException {
        FoodTagNotFoundException foodTagNotFoundException = new FoodTagNotFoundException(id);
        if (foodTagDAO != null) {
            try {
                return foodTagDAO.getWithId(id);
            } catch (SQLException ex) {
                Logger.getLogger(SqliteDataProvider.class.getName()).log(Level.SEVERE, null, ex);
                throw foodTagNotFoundException;
            }
        }
        throw foodTagNotFoundException;
    }

    @Override
    public List<FoodTag> getFoodTags() {
        List<FoodTag> empty = new ArrayList<>();
        if (foodTagDAO != null) {
            try {
                return foodTagDAO.getAll();
            } catch (SQLException ex) {
                return empty;
            }
        }
        return empty;
    }

    @Override
    public FoodTag addFoodTag(FoodTag foodTag) throws FoodTagNotFoundException {
        if (foodTagDAO != null) {
            try {
                return foodTagDAO.createFoodTag(foodTag);
            } catch (SQLException ex) {
                Logger.getLogger(SqliteDataProvider.class.getName()).log(Level.SEVERE, null, ex);
                throw new FoodTagNotFoundException();
            }
        }
        throw new FoodTagNotFoundException();
    }

    @Override
    public List<CityMall> getCityMallWithClusterIndex(Long id) {
        List<CityMall> empty = new ArrayList<>();
        if (cityMallDAO != null) {
            try {
                return cityMallDAO.cityMallWithClusterIndex(id);
            } catch (SQLException ex) {
                Logger.getLogger(SqliteDataProvider.class.getName()).log(Level.SEVERE, null, ex);
                return empty;
            }
        }
        return empty;
    }

    @Override
    public Restaraunt addRestaraunt(Restaraunt restaraunt) throws RestarauntNotFoundException {
        if (restarauntDAO != null) {
            try {
                return restarauntDAO.createRestaraunt(restaraunt);
            } catch (SQLException ex) {
                Logger.getLogger(SqliteDataProvider.class.getName()).log(Level.SEVERE, null, ex);
                throw new RestarauntNotFoundException();
            }
        }
        throw new RestarauntNotFoundException();
    }

}
