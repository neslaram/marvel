package com.example.neslaram.marvel.data.model.responses;

import com.google.gson.annotations.SerializedName;

import io.realm.RealmObject;

/**
 * Created by neslaram on 04/09/16.
 */
public class EventResponse extends RealmObject {
    @SerializedName("available")
    private int available;

    public int getAvailable() {
        return available;
    }

    public void setAvailable(int available) {
        this.available = available;
    }
}
