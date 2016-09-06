package com.example.neslaram.marvel.data.model;

import com.google.gson.annotations.SerializedName;

import io.realm.RealmObject;

/**
 * Created by neslaram on 04/09/16.
 */
public class Thumbnail extends RealmObject{
    @SerializedName("path")
    private String path;
    @SerializedName("extension")
    private String extension;

    public String getFullPath() {
        return String.format("%s.%s", path, extension);
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getExtension() {
        return extension;
    }

    public void setExtension(String extension) {
        this.extension = extension;
    }
}
