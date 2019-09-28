package com.four_friends.demetraserver;

import com.four_friends.demetraserver.cache.RestarauntCache;
import com.four_friends.demetraserver.db.data_provider.IDataProvider;
import com.four_friends.demetraserver.db.data_provider.exception.CityMallNotFoundException;
import com.four_friends.demetraserver.db.data_provider.exception.FoodTagNotFoundException;
import com.four_friends.demetraserver.db.data_provider.exception.OwnerNotFoundException;
import com.four_friends.demetraserver.db.data_provider.exception.RestarauntNotFoundException;
import com.four_friends.demetraserver.db.sqlite.SqliteDBConnect;
import com.four_friends.demetraserver.db.sqlite.SqliteDataProvider;
import com.four_friends.demetraserver.db.test_data_generator.CityMallGenerator;
import com.four_friends.demetraserver.db.test_data_generator.FoodTagGenerator;
import com.four_friends.demetraserver.db.test_data_generator.OwnerGenerator;
import com.four_friends.demetraserver.db.test_data_generator.RestarauntGenerator;
import com.four_friends.demetraserver.entity.CityMall;
import com.four_friends.demetraserver.entity.FoodTag;
import com.four_friends.demetraserver.entity.Owner;
import com.four_friends.demetraserver.entity.Restaraunt;
import com.four_friends.demetraserver.http.jetty.OwnHttpServer;
import com.j256.ormlite.support.ConnectionSource;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author gekko
 */
public class Main {
    
      private static void initData( IDataProvider dataProvider){
        
        try {
            
            FoodTag asianTag = FoodTagGenerator.createAsianTag();
            FoodTag bisnessLanch = FoodTagGenerator.createBisnessLanchTag();
            FoodTag burgerTag = FoodTagGenerator.createBurgerTag();
            FoodTag fastFoodTag = FoodTagGenerator.createFastFoodTag();
            FoodTag italianTag = FoodTagGenerator.createItalianTag();
            FoodTag vegeterianTag = FoodTagGenerator.createVegeterianTag();
            FoodTag russianTag = FoodTagGenerator.createRussianTag();
            FoodTag shawarmaTag = FoodTagGenerator.createShawarmaTag();
            
            FoodTag[] foodTags = new FoodTag[]{
                asianTag, bisnessLanch, burgerTag, fastFoodTag, italianTag, vegeterianTag, russianTag, shawarmaTag
            };
            
            for (FoodTag foodTag : foodTags) {
                foodTag = dataProvider.addFoodTag(foodTag);
            }
            
            
            Owner bkOwner = OwnerGenerator.getBKOwner();
            bkOwner.addFoodTag(fastFoodTag);
            bkOwner.addFoodTag(burgerTag);
            
            Owner kfcOwner = OwnerGenerator.getKFCOwner();
            kfcOwner.addFoodTag(fastFoodTag);
            kfcOwner.addFoodTag(burgerTag);

            Owner okolitcaOwner = OwnerGenerator.getOkolitcaOwner();
            okolitcaOwner.addFoodTag(russianTag);
            
            Owner blackStarBurgerOwner = OwnerGenerator.getBlackStarBurgerOwner();
            blackStarBurgerOwner.addFoodTag(burgerTag);
            
            CityMall greadCanionCityMall = CityMallGenerator.getGreadCanionCityMall();
            CityMall nordCityMall = CityMallGenerator.getNordCityMall();
            CityMall galeryCityMall = CityMallGenerator.getGaleryCityMall();
            
            
            bkOwner = dataProvider.addOwner(bkOwner);
            kfcOwner = dataProvider.addOwner(kfcOwner);
            okolitcaOwner = dataProvider.addOwner(okolitcaOwner);
            blackStarBurgerOwner = dataProvider.addOwner(blackStarBurgerOwner);
            
            
            Restaraunt okolitacaNord = RestarauntGenerator.createRestaunt(nordCityMall, okolitcaOwner);
            okolitacaNord = dataProvider.addRestaraunt(okolitacaNord);
            
            Restaraunt kfcNord = RestarauntGenerator.createRestaunt(nordCityMall, kfcOwner);
            kfcNord = dataProvider.addRestaraunt(kfcNord);
            
            Restaraunt kfcCanion = RestarauntGenerator.createRestaunt(greadCanionCityMall, kfcOwner);
            kfcCanion = dataProvider.addRestaraunt(kfcCanion);
            
            Restaraunt blackstarGalery = RestarauntGenerator.createRestaunt(galeryCityMall, blackStarBurgerOwner);
            blackstarGalery = dataProvider.addRestaraunt(blackstarGalery);
            
            
            greadCanionCityMall.addRestaraunt(kfcCanion);
            
            nordCityMall.addRestaraunt(kfcNord);
            nordCityMall.addRestaraunt(okolitacaNord);
            
            galeryCityMall.addRestaraunt(blackstarGalery);
            
            nordCityMall = dataProvider.addCityMall(nordCityMall);
            greadCanionCityMall = dataProvider.addCityMall(greadCanionCityMall);
            galeryCityMall = dataProvider.addCityMall(galeryCityMall);
        } catch (OwnerNotFoundException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        } catch (RestarauntNotFoundException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        } catch (CityMallNotFoundException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        } catch (FoodTagNotFoundException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    public static void main(String[] args) {
        try {
            SqliteDBConnect sqliteDBConnect = new SqliteDBConnect();
            sqliteDBConnect.createDB();
            ConnectionSource connectionSource = sqliteDBConnect.createConnectionSource();
            IDataProvider dataProvider = new SqliteDataProvider(connectionSource);
            RestarauntCache restarauntCache = new RestarauntCache(null);
            OwnHttpServer ownHttpServer = new OwnHttpServer(restarauntCache);
            ownHttpServer.start();
        } catch (Exception ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
