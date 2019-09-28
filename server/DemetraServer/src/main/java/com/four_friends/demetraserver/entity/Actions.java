package com.four_friends.demetraserver.entity;

import com.j256.ormlite.field.DataType;
import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

/**
 *
 * @author gekko
 */
@DatabaseTable
public class Actions extends Entity{
    
    @DatabaseField(dataType = DataType.SERIALIZABLE)
    Long[] restarauntIds;
    
    @DatabaseField
    String shortDescription;
    
    @DatabaseField
    String fullDescription;
    
    @DatabaseField
    String startDate;
    
    @DatabaseField
    String endDate;

    public Actions() {
    }

    public Long[] getRestarauntIds() {
        return restarauntIds;
    }

    public void setRestarauntId(Long[] restarauntIds) {
        this.restarauntIds = restarauntIds;
    }

    public String getShortDescription() {
        return shortDescription;
    }

    public void setShortDescription(String shortDescription) {
        this.shortDescription = shortDescription;
    }

    public String getFullDescription() {
        return fullDescription;
    }

    public void setFullDescription(String fullDescription) {
        this.fullDescription = fullDescription;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }
    
    
    
    
}
