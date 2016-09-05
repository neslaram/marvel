package com.example.neslaram.marvel.data.remote.repository;

import com.example.neslaram.marvel.data.model.responses.CharacterResponse;

import rx.Observable;

/**
 * Created by neslaram on 04/09/16.
 */
public interface DetailRepository {
    Observable<CharacterResponse> getCharacter(int id);
}
