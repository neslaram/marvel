package com.example.neslaram.marvel.data.local.repository;

import com.example.neslaram.marvel.data.model.Character;
import com.example.neslaram.marvel.utils.Contants;

import io.realm.Realm;
import rx.Observable;

/**
 * Created by neslaram on 04/09/16.
 */
public class DetailLocalRepositoryImpl implements DetailLocalRepository {


    @Override
    public Observable<Character> getCharacter(int id) {
        Realm realm = Realm.getDefaultInstance();
        return realm.where(Character.class)
                .equalTo(Contants.KEY_ID, id)
                .findFirstAsync().asObservable();

    }
}
