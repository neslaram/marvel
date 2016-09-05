package com.example.neslaram.marvel.data.remote.service;

import com.example.neslaram.marvel.data.model.responses.CharacterResponse;

import retrofit2.http.GET;
import rx.Observable;

/**
 * Created by desarrollo on 4/18/16.
 */
public interface MarvelClient {

    @GET("characters?orderBy=name")
    Observable<CharacterResponse> getCharacters();
}