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
        cityMall.setUrl("https://www.trk-nord.ru/");
        cityMall.setImageUrl("https://aliton.ru/img/site-pix/nord-logo-240-120.jpg");
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
        cityMall.setUrl("http://trk-canyon.ru/");
        cityMall.setImageUrl("http://trk-canyon.ru/styles/images/image_logo.png");
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
        cityMall.setUrl("http://trk-canyon.ru/");
        cityMall.setImageUrl("https://scontent.fhel5-1.fna.fbcdn.net/v/t1.0-9/33191566_1929017473809614_4388650247941783552_n.jpg?_nc_cat=107&_nc_oc=AQmLXNlmJcp40i2byBGVKqFGFU1FdajLTWoBfxe7g5ceLMDZnW1VQxNVYqXm1O7M2TU&_nc_ht=scontent.fhel5-1.fna&oh=3e326e1f1574aebecca5caa6e31efa6b&oe=5DEF0D2B");
        try {
            cityMall.calClusterId();
        } catch (WrongLocationException ex) {
            Logger.getLogger(CityMallGenerator.class.getName()).log(Level.SEVERE, null, ex);
        }
        return cityMall;
    }
      
     public static CityMall getPickCityMall() {
        CityMall pickCityMall = new CityMall();
        pickCityMall.setName("ТК ПИК");
        pickCityMall.setCity("Санкт-Петербург");
        pickCityMall.setUrl("http://tk-pik.ru/");
        pickCityMall.setAddress("ул. Ефимова, д.2");
        pickCityMall.setLocation("59.926437, 30.320587");
        pickCityMall.setImageUrl("https://kalashnikovav.ru/wp-content/uploads/2014/10/2-1.jpg");
        return pickCityMall;
    }

    public static CityMall[] getCityMalls(){
        return new CityMall[]{
            getGreadCanionCityMall(),
            getNordCityMall(),
            getPickCityMall()
        };
    }
}
