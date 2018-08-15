package com.aribhatt.automate.data.model;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.Comparator;

/**
 * Created by aribhatt on 28/12/17.
 */

public class MissedCall implements Parcelable, Comparator<MissedCall>{
    private String callNumber;
    private String callerName;
    private long lastCalled;
    private int priority;
    private boolean isNew;

    public MissedCall(String callNumber, String callerName, long lastCalled, int priority){
        this.callNumber = callNumber;
        this.callerName = callerName;
        this.lastCalled = lastCalled;
        this.priority = priority;

    }

    private MissedCall(Parcel parcel){
        callNumber = parcel.readString();
        callerName = parcel.readString();
        lastCalled = parcel.readLong();
        priority = parcel.readInt();
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(callNumber);
        parcel.writeString(callerName);
        parcel.writeLong(lastCalled);
        parcel.writeInt(priority);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Parcelable.Creator CREATOR = new Parcelable.Creator() {
        public MissedCall createFromParcel(Parcel in) {
            return new MissedCall(in);
        }

        public MissedCall[] newArray(int size) {
            return new MissedCall[size];
        }
    };

    @Override
    public int compare(MissedCall missedCall, MissedCall t1) {
        return 0;
    }

    public int getPriority() {
        return priority;
    }

    public void setPriority(int priority) {
        this.priority = priority;
    }

    public String getCallNumber() {
        return callNumber;
    }

    public void setCallNumber(String callNumber) {
        this.callNumber = callNumber;
    }

    public String getCallerName() {
        return callerName;
    }

    public void setCallerName(String callerName) {
        this.callerName = callerName;
    }

    public long getLastCalled() {
        return lastCalled;
    }

    public void setLastCalled(long lastCalled) {
        this.lastCalled = lastCalled;
    }
}
