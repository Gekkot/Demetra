/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.four_friends.demetraserver.db.test_data_generator;

import com.four_friends.demetraserver.entity.CityMall;
import com.four_friends.demetraserver.util.exception.WrongLocationException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author gekko
 */
public class CityMallGenerator {
    
    private final static long NORD_ID = OwnerGenerator.getRandomId();
    private final static long CANION_ID = OwnerGenerator.getRandomId();
    
    public static CityMall getNordCityMall() {
        CityMall cityMall = new CityMall("ТРК Норд");
        cityMall.setId(NORD_ID);
        cityMall.setCity("Санкт-Петербург");
        cityMall.setAddress("пр. просвещения 19");
        cityMall.setLocation("60.052388, 30.332191");
        try {
            cityMall.calClusterId();
        } catch (WrongLocationException ex) {
            Logger.getLogger(CityMallGenerator.class.getName()).log(Level.SEVERE, null, ex);
        }
        return cityMall;
    }

    public static CityMall getGreadCanionCityMall() {
        CityMall cityMall = new CityMall("Гранд Каньон");
        cityMall.setId(CANION_ID);
        cityMall.setCity("Санкт-Петербург");
        cityMall.setAddress("просп. Энгельса, 154");
        cityMall.setLocation("60.059050, 30.334377");
        try {
            cityMall.calClusterId();
        } catch (WrongLocationException ex) {
            Logger.getLogger(CityMallGenerator.class.getName()).log(Level.SEVERE, null, ex);
        }
        return cityMall;
    }
    
      public static CityMall getGaleryCityMall() {
        CityMall cityMall = new CityMall("Галерея");
        cityMall.setId(CANION_ID);
        cityMall.setCity("Санкт-Петербург");
        cityMall.setAddress("Лиговский просп., 30");
        cityMall.setLocation("59.927235, 30.359542");
        try {
            cityMall.calClusterId();
        } catch (WrongLocationException ex) {
            Logger.getLogger(CityMallGenerator.class.getName()).log(Level.SEVERE, null, ex);
        }
        return cityMall;
    }
    
    public static CityMall[] getCityMalls(){
        return new CityMall[]{
            getGreadCanionCityMall(),
            getNordCityMall()
        };
    }
}
