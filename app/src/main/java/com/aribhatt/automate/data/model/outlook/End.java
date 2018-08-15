package com.aribhatt.automate.data.model.outlook;

import java.io.Serializable;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class End implements Serializable, Parcelable
{

    @SerializedName("DateTime")
    @Expose
    private String dateTime;
    @SerializedName("TimeZone")
    @Expose
    private String timeZone;
    public final static Parcelable.Creator<End> CREATOR = new Creator<End>() {


        @SuppressWarnings({
                "unchecked"
        })
        public End createFromParcel(Parcel in) {
            return new End(in);
        }

        public End[] newArray(int size) {
            return (new End[size]);
        }

    }
            ;
    private final static long serialVersionUID = -9059872277292774447L;

    protected End(Parcel in) {
        this.dateTime = ((String) in.readValue((String.class.getClassLoader())));
        this.timeZone = ((String) in.readValue((String.class.getClassLoader())));
    }

    public End() {
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
