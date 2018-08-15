package com.aribhatt.automate.data.db.converter;

import android.arch.persistence.room.TypeConverter;

import com.aribhatt.automate.data.db.entity.Action;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.Collections;
import java.util.Date;
import java.util.List;

public class Converters {
    static Gson gson = new Gson();

    @TypeConverter
    public static List<Action> stringToActionList(String data){
        if(data == null){
            return Collections.emptyList();
        }

        Type listType = new TypeToken<List<Action>>() {}.getType();
        return gson.fromJson(data, listType);
    }

    @TypeConverter
    public static String actionListToString(List<Action> actions){
        return gson.toJson(actions);
    }

    @TypeConverter
    public static Date fromTimestamp(Long value) {
        return value == null ? null : new Date(value);
    }

    @TypeConverter
    public static Long dateToTimestamp(Date date) {
        return date == null ? null : date.getTime();
    }

}
