package com.aribhatt.automate.service;

import com.aribhatt.automate.data.model.outlook.Event;

import io.reactivex.Observable;
import io.reactivex.Observer;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by aribhatt on 04/11/17.
 */

public class RetrofitHelper {
    Retrofit retrofit;

    public RetrofitHelper(String baseUrl){
        retrofit = new Retrofit.Builder()
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl(baseUrl)
                .build();
    }


//    public static Observable<Event> getObservable(Retrofit retrofit, String format, String startTime, String endDate, int limit){
//        APIService apiService = retrofit.create(OutlookService.class);
//        return apiService.getEvents(format, startTime, endDate, limit);
//    }
}
