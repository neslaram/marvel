package com.example.neslaram.marvel.presenter.main;

import com.example.neslaram.marvel.data.model.Character;
import com.example.neslaram.marvel.data.model.responses.CharacterResponse;

import io.realm.RealmResults;
import rx.Observable;

/**
 * Created by desarrollo on 7/6/16.
 */
public interface MainInteractor {
    Observable<CharacterResponse> getCharacters(int offset);
    Observable<RealmResults<Character>> getLocalCharacters();
}
