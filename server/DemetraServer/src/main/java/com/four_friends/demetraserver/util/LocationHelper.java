/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.four_friends.demetraserver.util;

import com.four_friends.demetraserver.util.exception.WrongLocationException;


/**
 *
 * @author gekko
 */
public class LocationHelper {

    public static long LocationToClusterIndex(double latitude, double longitude) throws WrongLocationException {
        if (latitude < -90 || latitude > 90) {
            throw new WrongLocationException("wrong latitude: " + latitude);
        }
        if (longitude < 0 || longitude > 320) {
            throw new WrongLocationException("wrong longitude: " + longitude);
        }
        latitude = 180.0 - latitude;
        int latitudeIndex = (int) ((latitude / 180.0) * 10000);
        int longitudeIndex = (int) ((longitude / 360.0) * 10000);
        return latitudeIndex * 10000 + longitudeIndex;
    }
    
    public static double distanceBetweenLocations(double lat1, double lon1, double lat2, double lon2){
        double dLat = Math.toRadians(lat2 - lat1); 
        double dLon = Math.toRadians(lon2 - lon1); 
  
        // convert to radians 
        lat1 = Math.toRadians(lat1); 
        lat2 = Math.toRadians(lat2); 
  
        // apply formulae 
        double a = Math.pow(Math.sin(dLat / 2), 2) +  
                   Math.pow(Math.sin(dLon / 2), 2) *  
                   Math.cos(lat1) *  
                   Math.cos(lat2); 
        double rad = 6371; 
        double c = 2 * Math.asin(Math.sqrt(a)); 
        return rad * c; 

    }

}
