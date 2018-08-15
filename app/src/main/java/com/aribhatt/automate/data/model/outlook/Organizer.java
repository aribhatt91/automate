package com.aribhatt.automate.data.model.outlook;

import java.io.Serializable;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Organizer implements Serializable, Parcelable
{

    @SerializedName("EmailAddress")
    @Expose
    private EmailAddress emailAddress;
    public final static Parcelable.Creator<Organizer> CREATOR = new Creator<Organizer>() {


        @SuppressWarnings({
                "unchecked"
        })
        public Organizer createFromParcel(Parcel in) {
            return new Organizer(in);
        }

        public Organizer[] newArray(int size) {
            return (new Organizer[size]);
        }

    };
    private final static long serialVersionUID = -6439997317325946792L;

    protected Organizer(Parcel in) {
        this.emailAddress = ((EmailAddress) in.readValue((EmailAddress.class.getClassLoader())));
    }

    public Organizer() {
    }

    public EmailAddress getEmailAddress() {
        return emailAddress;
    }

    public void setEmailAddress(EmailAddress emailAddress) {
        this.emailAddress = emailAddress;
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(emailAddress);
    }

    public int describeContents() {
        return 0;
    }

}
