package com.aribhatt.automate.data.db.entity;

/**
 * Created by aribhatt on 13/05/18.
 */
import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

@Entity(tableName="mcalls")
public class MissedCall {

    @NonNull
    @PrimaryKey
    @ColumnInfo(name = "cnumber")
    private String cnumber;

    @ColumnInfo(name = "ctimes")
    private int ctimes;

    @ColumnInfo(name = "lastcalled")
    private String lastcalled;

    public MissedCall(@NonNull String cnumber, int ctimes, String lastcalled){
        this.cnumber = cnumber;
        this.ctimes = ctimes;
        this.lastcalled = lastcalled;
    }

    public int getCtimes() {
        return ctimes;
    }

    public String getCnumber() {
        return cnumber;
    }

    public String getLastcalled() {
        return lastcalled;
    }

    public void setCnumber(String cnumber) {
        this.cnumber = cnumber;
    }

    public void setCtimes(int ctimes) {
        this.ctimes = ctimes;
    }

    public void setLastcalled(String lastcalled) {
        this.lastcalled = lastcalled;
    }
}
