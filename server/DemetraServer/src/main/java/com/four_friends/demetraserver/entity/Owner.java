package com.four_friends.demetraserver.entity;

import com.j256.ormlite.field.DataType;
import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 *
 * @author gekko
 */
@DatabaseTable
public class Owner extends Entity{

    @DatabaseField
    private String ownerName;

    @DatabaseField
    private String ownerRestarauntName;

    @DatabaseField
    private String url;
    
    @DatabaseField
    private String logoUrl;
    
    @DatabaseField(dataType = DataType.SERIALIZABLE)
    private Long[] foodTagIds = new Long[]{};

    public String getOwnerName() {
        return ownerName;
    }

    public void setOwnerName(String ownerName) {
        this.ownerName = ownerName;
    }

    public String getOwnerRestarauntName() {
        return ownerRestarauntName;
    }

    public void setOwnerRestarauntName(String ownerRestarauntName) {
        this.ownerRestarauntName = ownerRestarauntName;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
    
     public void addFoodTag(FoodTag foodTag) {
        List<Long> idsList = new ArrayList<>();
        Collections.addAll(idsList, foodTagIds);
        idsList.add(foodTag.getId());
        this.foodTagIds = idsList.toArray(new Long[0]);
    }

}
