package com.aribhatt.automate.data.model.outlook;

import java.io.Serializable;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Start implements Serializable, Parcelable
{

    @SerializedName("DateTime")
    @Expose
    private String dateTime;
    @SerializedName("TimeZone")
    @Expose
    private String timeZone;
    public final static Parcelable.Creator<Start> CREATOR = new Creator<Start>() {


        @SuppressWarnings({
                "unchecked"
        })
        public Start createFromParcel(Parcel in) {
            return new Start(in);
        }

        public Start[] newArray(int size) {
            return (new Start[size]);
        }

    }
            ;
    private final static long serialVersionUID = 7412690278490322467L;

    protected Start(Parcel in) {
        this.dateTime = ((String) in.readValue((String.class.getClassLoader())));
        this.timeZone = ((String) in.readValue((String.class.getClassLoader())));
    }

    public Start() {
    }

    public String getDateTime() {
        return dateTime;
    }

    public void setDateTime(String dateTime) {
        this.dateTime = dateTime;
    }

    public String getTimeZone() {
        return timeZone;
    }

    public void setTimeZone(String timeZone) {
        this.timeZone = timeZone;
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(dateTime);
        dest.writeValue(timeZone);
    }

    public int describeContents() {
        return 0;
    }

}