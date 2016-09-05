package com.example.neslaram.marvel.data.model;

import com.example.neslaram.marvel.data.model.responses.ComicResponse;
import com.example.neslaram.marvel.data.model.responses.EventResponse;
import com.example.neslaram.marvel.data.model.responses.SerieResponse;
import com.example.neslaram.marvel.data.model.responses.StorieResponse;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by neslaram on 04/09/16.
 */
public class Character {
    @SerializedName("id")
    private int id;
    @SerializedName("name")
    private String name;
    @SerializedName("modified")
    private String modified;
    @SerializedName("thumbnail")
    private Thumbnail thumbnail;
    @SerializedName("comics")
    private ComicResponse comics;
    @SerializedName("series")
    private SerieResponse series;
    @SerializedName("stories")
    private StorieResponse stories;
    @SerializedName("events")
    private EventResponse events;
    @SerializedName("urls")
    private List<Url> urls;


    public String getName() {
        return name;
    }

    public String getThumbnail() {
        return thumbnail.getFullPath();
    }
}
