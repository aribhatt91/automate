package com.aribhatt.automate.data.model.outlook;

import java.io.Serializable;
import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class EmailAddress implements Serializable, Parcelable
{

    @SerializedName("Name")
    @Expose
    private String name;
    @SerializedName("Address")
    @Expose
    private String address;
    public final static Parcelable.Creator<EmailAddress> CREATOR = new Creator<EmailAddress>() {


        @SuppressWarnings({
                "unchecked"
        })
        public EmailAddress createFromParcel(Parcel in) {
            return new EmailAddress(in);
        }

        public EmailAddress[] newArray(int size) {
            return (new EmailAddress[size]);
        }

    }
            ;
    private final static long serialVersionUID = -6462592689528416767L;

    protected EmailAddress(Parcel in) {
        this.name = ((String) in.readValue((String.class.getClassLoader())));
        this.address = ((String) in.readValue((String.class.getClassLoader())));
    }

    public EmailAddress() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(name);
        dest.writeValue(address);
    }

    public int describeContents() {
        return 0;
    }

}