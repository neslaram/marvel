package com.example.neslaram.marvel.data.model.responses;

import com.google.gson.annotations.SerializedName;

/**
 * Created by neslaram on 04/09/16.
 */
public class StorieResponse {
    @SerializedName("available")
    private int available;

    public int getAvailable() {
        return available;
    }
}
