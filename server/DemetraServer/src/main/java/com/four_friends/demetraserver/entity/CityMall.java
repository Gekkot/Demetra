package com.four_friends.demetraserver.entity;

import com.j256.ormlite.field.DataType;
import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

/**
 *
 * @author gekko
 */
@DatabaseTable
public class CityMall extends Entity{
    @DatabaseField(generatedId = true)
    private long id;
    
    @DatabaseField
    private String name;
    
    private List<Restaraunt> restarauntList = new ArrayList<>();
    
    @DatabaseField(dataType = DataType.SERIALIZABLE)
    private Long[] restarauntIds = new Long[]{};
    
    @DatabaseField
    private String city;
    
    @DatabaseField
    private String address;
    
    @DatabaseField
    private long clusterId;
    
    @DatabaseField
    private double longitude;
    
    @DatabaseField
    private double latitude;
    
    @DatabaseField
    private String url;
    

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long[] getRestarauntIds() {
        return restarauntIds;
    }

    public void setRestarauntIds(Long[] restarauntIds) {
        this.restarauntIds = restarauntIds;
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

    public long getClusterId() {
        return clusterId;
    }

    public void setClusterId(long clusterId) {
        this.clusterId = clusterId;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public CityMall() {
    }
    

    public CityMall(String name) {
        this.name = name;
    }
    
    public boolean setLocation(String location){
        String[] latlongArray = location.split("[,]");
        if(latlongArray.length < 2){
            return false;
        }
        try{
            Double latitude = Double.parseDouble(latlongArray[0]);
            Double longitude = Double.parseDouble(latlongArray[1]);
            this.longitude = longitude;
            this.latitude = latitude;
            return true;
        }catch(NumberFormatException ex){
            System.out.println(ex.toString());
            return false;
        }
    };
    
    public void addRestaraunt(Restaraunt restaraunt){
        List<Long> idsList = new ArrayList<>();
        Collections.addAll(idsList, restarauntIds);
        idsList.add(restaraunt.getId());
        this.restarauntIds = idsList.toArray(new Long[0]);
        restaraunt.setCity(this.city);
        restaraunt.setAddress(this.address);
        
    }
    
    public void updateRestaraultIds(){
        Long[] updatedRestarauntIds = this.restarauntList
                .stream()
                .filter(Objects::nonNull)
                .map((current_restaraunt) -> {
                    return current_restaraunt.getId();
                }).toArray(Long[]::new);
        setRestarauntIds(updatedRestarauntIds);
    }
    
    private int calClusterId(){
        return 0;
    }
}
