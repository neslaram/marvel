package com.example.neslaram.marvel.data.model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by neslaram on 04/09/16.
 */
public class Thumbnail {
    @SerializedName("path")
    private String path;
    @SerializedName("extension")
    private String extension;

    public String getFullPath() {
        return String.format("%s.%s", path, extension);
    }
}
