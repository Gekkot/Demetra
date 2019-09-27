package com.four_friends.demetraserver.entity;

import com.j256.ormlite.field.DatabaseField;

/**
 *
 * @author gekko
 */
public class Owner {

    @DatabaseField(generatedId = true)
    private long id;

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

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

}
