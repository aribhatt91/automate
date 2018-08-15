package com.aribhatt.automate.data.model;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.PrimaryKey;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.Serializable;
import java.util.Date;
import java.util.UUID;

public class EventItem implements Serializable {
    private static final String EVENTID = "EVENTID";
    private static final String EVENTNAME = "EVENTNAME";
    private static final String EVENTTYPE = "EVENTTYPE";
    private static final String EVENTSPAN = "EVENTSPAN";
    private static final String EVENTBEGDATE = "EVENTBEGDATE";
    private static final String EVENTENDDATE = "EVENTENDDATE";
    private static final String REPEAT = "REPEAT";

    private UUID eventId;

    private String eventName;

    private String eventType;

    private int eventSpan;

    private Date beginDate;

    private Date endDate;

    private boolean repeat;

    public EventItem(String eventName, String eventType, int eventSpan, Date beginDate, Date endDate, boolean repeat){
        this.eventId = UUID.randomUUID();
        this.eventName = eventName;
        this.eventType = eventType;
        this.eventSpan = eventSpan;
        this.beginDate = beginDate;
        this.endDate = endDate;
        this.repeat = repeat;
    }

    public EventItem(JSONObject object) throws JSONException{
        this.eventName = object.getString(EVENTNAME);
        this.eventType = object.getString(EVENTTYPE);
        this.eventSpan = object.getInt(EVENTSPAN);
        this.beginDate = new Date(object.getLong(EVENTBEGDATE));
        this.endDate = new Date(object.getLong(EVENTENDDATE));
        this.repeat = object.getBoolean(REPEAT);
        this.eventId = UUID.fromString(object.getString(EVENTID));
    }

    public JSONObject toJSON() throws JSONException{
        JSONObject object = new JSONObject();

        object.put(EVENTNAME, eventName);
        object.put(EVENTTYPE, eventType);
        object.put(EVENTSPAN, eventSpan);
        if(beginDate != null){
            object.put(EVENTBEGDATE, beginDate.getTime());
        }
        if(endDate != null){
            object.put(EVENTENDDATE, endDate.getTime());
        }
        if(eventId != null){
            object.put(EVENTID, eventId.toString());
        }
        object.put(REPEAT, repeat);

        return object;

    }

    public String getEventName() {
        return eventName;
    }

    public UUID getEventId() {
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

    public void setEventId(UUID eventId) {
        this.eventId = eventId;
    }

    public void setEventSpan(int eventSpan) {
        this.eventSpan = eventSpan;
    }
}

