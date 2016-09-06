package com.example.neslaram.marvel.data.model;

import com.example.neslaram.marvel.data.model.responses.ComicResponse;
import com.example.neslaram.marvel.data.model.responses.EventResponse;
import com.example.neslaram.marvel.data.model.responses.SerieResponse;
import com.example.neslaram.marvel.data.model.responses.StorieResponse;
import com.google.gson.annotations.SerializedName;

import io.realm.RealmList;
import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

/**
 * Created by neslaram on 04/09/16.
 */
public class Character extends RealmObject {
    @SerializedName("id")
    @PrimaryKey
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
    private RealmList<Url> urls;


    public String getThumbnail() {
        return thumbnail.getFullPath();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getModified() {
        return modified;
    }

    public void setModified(String modified) {
        this.modified = modified;
    }

    public void setThumbnail(Thumbnail thumbnail) {
        this.thumbnail = thumbnail;
    }

    public ComicResponse getComics() {
        return comics;
    }

    public void setComics(ComicResponse comics) {
        this.comics = comics;
    }

    public SerieResponse getSeries() {
        return series;
    }

    public void setSeries(SerieResponse series) {
        this.series = series;
    }

    public StorieResponse getStories() {
        return stories;
    }

    public void setStories(StorieResponse stories) {
        this.stories = stories;
    }

    public EventResponse getEvents() {
        return events;
    }

    public void setEvents(EventResponse events) {
        this.events = events;
    }

    public RealmList<Url> getUrls() {
        return urls;
    }

    public void setUrls(RealmList<Url> urls) {
        this.urls = urls;
    }
}
