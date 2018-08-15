package com.aribhatt.automate.data.db.dao;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.PrimaryKey;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import com.aribhatt.automate.data.db.entity.PriorityContact;

import java.util.List;

/**
 * Created by aribhatt on 06/01/18.
 */

@Dao
public interface PriorityContactDao {

    @Query("SELECT * FROM pcontacts ORDER BY priority_level ASC")
    LiveData<List<PriorityContact>> getAll();

    @Query("SELECT * FROM pcontacts WHERE contactId IN (:contactIds)")
    LiveData<List<PriorityContact>> loadAllByIds(int[] contactIds);

    @Query("SELECT * FROM pcontacts WHERE contact_name LIKE :name")
    LiveData<PriorityContact> findByName(String name);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertAll(PriorityContact... users);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(PriorityContact user);

    @Delete
    void delete(PriorityContact... users);

    @Update
    void updatePriority(PriorityContact... users);

}
