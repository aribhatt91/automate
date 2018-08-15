package com.aribhatt.automate.data.model;

import android.support.annotation.NonNull;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by aribhatt on 29/12/17.
 */

public class Contact implements Comparable<Contact>, Serializable{

    private String contactName;
    private String photoUri;
    private long contactId;
    private String ringtone;
    ArrayList<String> phoneNumbers;

    public Contact(long contactId, String contactName, String photoUri, String ringtone, ArrayList<String> phoneNumberss){
        this.contactId = contactId;
        this.contactName = contactName;
        this.photoUri = photoUri;
        this.ringtone = ringtone;
        this.phoneNumbers = phoneNumbers;
    }

    public long getContactId() {
        return contactId;
    }

    public void setContactId(long contactId) {
        this.contactId = contactId;
    }

    public String getContactName() {
        return contactName;
    }

    public void setContactName(String contactName) {
        this.contactName = contactName;
    }

    public String getPhotoUri() {
        return photoUri;
    }

    public void setPhotoUri(String photoUri) {
        this.photoUri = photoUri;
    }

    public ArrayList<String> getPhoneNumbers() {
        return phoneNumbers;
    }

    public void setPhoneNumbers(ArrayList<String> phoneNumbers) {
        this.phoneNumbers = phoneNumbers;
    }

    public String toString(){
        return this.contactName;
    }

    @Override
    public int compareTo(@NonNull Contact contact) {
        try {
            return this.contactName.compareTo(contact.contactName);
        }catch (Exception e){
            return 0;
        }
    }
}

