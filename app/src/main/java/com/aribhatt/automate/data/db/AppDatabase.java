package com.aribhatt.automate.data.db;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.arch.persistence.room.TypeConverters;
import android.content.Context;

import com.aribhatt.automate.data.db.converter.Converters;
import com.aribhatt.automate.data.db.dao.PriorityContactDao;
import com.aribhatt.automate.data.db.entity.Event;
import com.aribhatt.automate.data.db.entity.MissedCall;
import com.aribhatt.automate.data.db.entity.PriorityContact;

/**
 * Created by aribhatt on 02/01/18.
 */
@Database(entities = {PriorityContact.class, MissedCall.class, Event.class}, version = 1, exportSchema = false)
@TypeConverters({Converters.class})
public abstract class AppDatabase extends RoomDatabase{
    private static AppDatabase instance;
    public abstract PriorityContactDao priorityContactDao();

    public static AppDatabase getDatabase(Context context){
        if(instance == null){
            instance = Room.databaseBuilder(context.getApplicationContext(), AppDatabase.class, "automatedb").build();
        }
        return instance;
    }
}
