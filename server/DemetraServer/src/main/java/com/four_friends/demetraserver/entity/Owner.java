package com.four_friends.demetraserver.entity;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

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

}
