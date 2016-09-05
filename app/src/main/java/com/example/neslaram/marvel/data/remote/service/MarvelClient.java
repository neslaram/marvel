package com.example.neslaram.marvel.data.remote.service;

import com.example.neslaram.marvel.data.model.responses.CharacterResponse;

import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;
import rx.Observable;

/**
 * Created by desarrollo on 4/18/16.
 */
public interface MarvelClient {

    @GET("characters?orderBy=name")
    Observable<CharacterResponse> getCharacters(@Query("offset")int offset);
    @GET("characters/{id}")
    Observable<CharacterResponse> getCharacter(@Path("id") int id);
}