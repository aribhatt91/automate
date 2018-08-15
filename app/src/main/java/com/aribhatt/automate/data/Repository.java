package com.aribhatt.automate.data;

import android.app.Application;
import android.arch.lifecycle.LiveData;
import android.os.AsyncTask;

import com.aribhatt.automate.data.db.AppDatabase;
import com.aribhatt.automate.data.db.dao.PriorityContactDao;
import com.aribhatt.automate.data.db.entity.PriorityContact;
import com.aribhatt.automate.data.model.Contact;

import java.util.List;

/**
 * Created by aribhatt on 31/03/18.
 */

public class Repository {
    private PriorityContactDao priorityContactDao;

    public Repository(Application application){
        AppDatabase db = AppDatabase.getDatabase(application);
        priorityContactDao = db.priorityContactDao();
    }

    public LiveData<List<PriorityContact>> getAllPriorityContacts(){
        return priorityContactDao.getAll();
    }

    //public LiveData<List<Contact>> getAllContacts(){

    //}

    public void insertPriorityContact(PriorityContact contact){
        new InsertContactAsyncTask(this.priorityContactDao).execute(contact);
    }

    private static class InsertContactAsyncTask extends AsyncTask<PriorityContact, Void, Void>{
        PriorityContactDao dao;

        InsertContactAsyncTask(PriorityContactDao dao){
            this.dao = dao;
        }

        @Override
        protected Void doInBackground(PriorityContact... priorityContacts) {
            this.dao.insert(priorityContacts[0]);
            return null;
        }
    }

    public void deletePriorityContact(PriorityContact contact){
        new DeleteContactAsyncTask(this.priorityContactDao).execute(contact);
    }

    private static class DeleteContactAsyncTask extends AsyncTask<PriorityContact, Void, Void>{
        PriorityContactDao dao;

        DeleteContactAsyncTask(PriorityContactDao dao){
            this.dao = dao;
        }

        @Override
        protected Void doInBackground(PriorityContact... priorityContacts) {
            this.dao.delete(priorityContacts[0]);
            return null;
        }
    }

}
