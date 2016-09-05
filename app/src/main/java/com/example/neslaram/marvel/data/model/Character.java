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
    @SerializedName("description")
    private String description;
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

    public int getId() {
        return id;
    }

    public String getDescription() {
        return description;
    }

    public String getModified() {
        return modified;
    }

    public ComicResponse getComics() {
        return comics;
    }

    public SerieResponse getSeries() {
        return series;
    }

    public StorieResponse getStories() {
        return stories;
    }

    public EventResponse getEvents() {
        return events;
    }
}
