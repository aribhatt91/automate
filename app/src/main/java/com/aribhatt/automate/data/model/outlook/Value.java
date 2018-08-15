package com.aribhatt.automate.data.model.outlook;

import java.io.Serializable;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Value implements Serializable, Parcelable
{

    @SerializedName("Id")
    @Expose
    private String id;
    @SerializedName("Subject")
    @Expose
    private String subject;
    @SerializedName("Start")
    @Expose
    private Start start;
    @SerializedName("End")
    @Expose
    private End end;
    @SerializedName("Organizer")
    @Expose
    private Organizer organizer;
    public final static Parcelable.Creator<Value> CREATOR = new Creator<Value>() {


        @SuppressWarnings({
                "unchecked"
        })
        public Value createFromParcel(Parcel in) {
            return new Value(in);
        }

        public Value[] newArray(int size) {
            return (new Value[size]);
        }

    }
            ;
    private final static long serialVersionUID = -6342450765650544812L;

    protected Value(Parcel in) {
        this.id = ((String) in.readValue((String.class.getClassLoader())));
        this.subject = ((String) in.readValue((String.class.getClassLoader())));
        this.start = ((Start) in.readValue((Start.class.getClassLoader())));
        this.end = ((End) in.readValue((End.class.getClassLoader())));
        this.organizer = ((Organizer) in.readValue((Organizer.class.getClassLoader())));
    }

    public Value() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public Start getStart() {
        return start;
    }

    public void setStart(Start start) {
        this.start = start;
    }

    public End getEnd() {
        return end;
    }

    public void setEnd(End end) {
        this.end = end;
    }

    public Organizer getOrganizer() {
        return organizer;
    }

    public void setOrganizer(Organizer organizer) {
        this.organizer = organizer;
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(id);
        dest.writeValue(subject);
        dest.writeValue(start);
        dest.writeValue(end);
        dest.writeValue(organizer);
    }

    public int describeContents() {
        return 0;
    }

}
