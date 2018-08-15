package com.aribhatt.automate.service;

import com.aribhatt.automate.data.model.outlook.Event;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by aribhatt on 28/12/17.
 */

public interface OutlookService {

    @GET("fdsnws/event/1/query?")
    Observable<Event> getEvents(@Query("format") String format, @Query("starttime") String startTime, @Query("endtime") String endTime, @Query("limit") int limit);
}