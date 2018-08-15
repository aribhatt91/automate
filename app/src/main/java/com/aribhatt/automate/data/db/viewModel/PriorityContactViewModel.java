package com.aribhatt.automate.data.db.viewModel;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.support.annotation.NonNull;

import com.aribhatt.automate.data.Repository;
import com.aribhatt.automate.data.db.entity.PriorityContact;

import java.util.List;

/**
 * Created by aribhatt on 10/02/18.
 */

public class PriorityContactViewModel extends AndroidViewModel {
    private Repository repository;
    private LiveData<List<PriorityContact>> priorityContactObservable;

    public PriorityContactViewModel(@NonNull Application application, @NonNull Repository repository){
        super(application);
        this.repository = repository;
        priorityContactObservable = repository.getAllPriorityContacts();
    }

    public LiveData<List<PriorityContact>> getPriorityContacts(){
        return priorityContactObservable;
    }
}
