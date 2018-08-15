package com.aribhatt.automate.data.db.viewModel;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.support.annotation.NonNull;

import com.aribhatt.automate.data.Repository;
import com.aribhatt.automate.data.db.entity.MissedCall;

import java.util.List;

/**
 * Created by aribhatt on 13/05/18.
 */

public class MissedCallViewModel extends AndroidViewModel{
    private Repository repository;
    private LiveData<List<MissedCall>> missedCallObservable;

    public MissedCallViewModel(@NonNull Application application, @NonNull Repository repository){
        super(application);
        this.repository = repository;
        //missedCallObservable =
    }
}
