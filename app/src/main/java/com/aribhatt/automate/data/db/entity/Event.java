package com.aribhatt.automate.data.db.entity;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

import java.io.Serializable;
import java.util.Date;

@Entity
public class Event implements Serializable {
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "eventId")
    private long eventId;

    @ColumnInfo(name = "event_name")
    private String eventName;

    @ColumnInfo(name = "event_type")
    private String eventType;

    @ColumnInfo(name = "event_span")
    private int eventSpan;

    @ColumnInfo(name = "begin_date")
    private Date beginDate;

    @ColumnInfo(name = "end_date")
    private Date endDate;

    public Event(String eventName, String eventType, int eventSpan, Date beginDate, Date endDate){
        this.eventName = eventName;
        this.eventType = eventType;
        this.eventSpan = eventSpan;
        this.beginDate = beginDate;
        this.endDate = endDate;
    }

    public String getEventName() {
        return eventName;
    }

    public long getEventId() {
        return eventId;
    }

    public String getEventType() {
        return eventType;
    }

    public int getEventSpan() {
        return eventSpan;
    }

    public void setEventName(String eventName) {
        this.eventName = eventName;
    }

    public void setEventId(int eventId) {
        this.eventId = eventId;
    }

    public void setEventSpan(int eventSpan) {
        this.eventSpan = eventSpan;
    }

    public Date getBeginDate() {
        return beginDate;
    }

    public Date getEndDate() {
        return endDate;
    }
}
