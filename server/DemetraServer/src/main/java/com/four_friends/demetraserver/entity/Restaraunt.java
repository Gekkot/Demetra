package com.four_friends.demetraserver.entity;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

/**
 *
 * @author gekko
 */
@DatabaseTable
public class Restaraunt extends Entity {

    @DatabaseField(columnName = "name", canBeNull = false)
    private String name;

    @DatabaseField
    private String city;

    @DatabaseField
    private String address;

    @DatabaseField
    private long latitude;
    
    @DatabaseField
    private String  description;

    @DatabaseField
    private long longitude;

    @DatabaseField
    private long cityMollID;

    @DatabaseField
    private long ownerID;
    
    @DatabaseField
    private String imageUrl;

    public Restaraunt() {
    }

    public Restaraunt(String name) {
        this.name = name;
    }

    public Restaraunt(Owner owner) {
        this.name = owner.getOwnerRestarauntName();
        this.ownerID = owner.getId();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public long getLatitude() {
        return latitude;
    }

    public void setLatitude(long latitude) {
        this.latitude = latitude;
    }

    public long getLongitude() {
        return longitude;
    }

    public void setLongitude(long longitude) {
        this.longitude = longitude;
    }

    public long getCityMollID() {
        return cityMollID;
    }

    public void setCityMollID(long cityMollID) {
        this.cityMollID = cityMollID;
    }

    public long getOwnerID() {
        return ownerID;
    }

    public void setOwnerID(long ownerID) {
        this.ownerID = ownerID;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
    
    
    
    

}
