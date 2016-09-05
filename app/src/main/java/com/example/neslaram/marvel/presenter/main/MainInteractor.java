package com.example.neslaram.marvel.presenter.main;

import com.example.neslaram.marvel.data.model.responses.CharacterResponse;

import rx.Observable;

/**
 * Created by desarrollo on 7/6/16.
 */
public interface MainInteractor {
    Observable<CharacterResponse> getCharacters(String page);
}
