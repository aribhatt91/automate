package com.aribhatt.automate.data.db.entity;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

/**
 * Created by aribhatt on 06/01/18.
 */
@Entity(tableName="pcontacts")
public class PriorityContact {

    @PrimaryKey
    @ColumnInfo(name = "contactId")
    private long contactId;

    @ColumnInfo(name = "contact_name")
    private String contactName;

    @ColumnInfo(name = "priority_level")
    private int priorityLevel;

    public PriorityContact(long contactId, String contactName, int priorityLevel){
        this.contactId = contactId;
        this.contactName = contactName;
        this.priorityLevel = priorityLevel;
    }

    public String getContactName() {
        return contactName;
    }

    public long getContactId() {
        return contactId;
    }

    public int getPriorityLevel() {
        return priorityLevel;
    }

    public void setContactName(String contactName) {
        this.contactName = contactName;
    }

    public void setContactId(int contactId) {
        this.contactId = contactId;
    }

    public void setPriorityLevel(int priorityLevel) {
        this.priorityLevel = priorityLevel;
    }
}
