package com.aribhatt.automate.data.model.outlook;

import java.io.Serializable;
import java.util.List;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Event implements Serializable, Parcelable
{

    @SerializedName("@odata.context")
    @Expose
    private String odataContext;
    @SerializedName("value")
    @Expose
    private List<Value> value = null;
    public final static Parcelable.Creator<Event> CREATOR = new Creator<Event>() {


        @SuppressWarnings({
                "unchecked"
        })
        public Event createFromParcel(Parcel in) {
            return new Event(in);
        }

        public Event[] newArray(int size) {
            return (new Event[size]);
        }

    }
            ;
    private final static long serialVersionUID = -548561020894107555L;

    protected Event(Parcel in) {
        this.odataContext = ((String) in.readValue((String.class.getClassLoader())));
        in.readList(this.value, (Value.class.getClassLoader()));
    }

    public Event() {
    }

    public String getOdataContext() {
        return odataContext;
    }

    public void setOdataContext(String odataContext) {
        this.odataContext = odataContext;
    }

    public List<Value> getValue() {
        return value;
    }

    public void setValue(List<Value> value) {
        this.value = value;
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(odataContext);
        dest.writeList(value);
    }

    public int describeContents() {
        return 0;
    }

}
