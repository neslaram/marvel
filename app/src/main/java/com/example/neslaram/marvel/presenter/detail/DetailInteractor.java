package com.example.neslaram.marvel.presenter.detail;

import com.example.neslaram.marvel.data.model.responses.CharacterResponse;

import rx.Observable;

/**
 * Created by desarrollo on 7/6/16.
 */
public interface DetailInteractor {
    Observable<CharacterResponse> getCharacter(int id);
}
