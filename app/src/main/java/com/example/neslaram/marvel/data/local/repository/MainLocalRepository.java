package com.example.neslaram.marvel.data.local.repository;

import com.example.neslaram.marvel.data.model.Character;

import io.realm.RealmResults;
import rx.Observable;

/**
 * Created by neslaram on 04/09/16.
 */
public interface MainLocalRepository {
    Observable<RealmResults<Character>> getCharacters();
}
