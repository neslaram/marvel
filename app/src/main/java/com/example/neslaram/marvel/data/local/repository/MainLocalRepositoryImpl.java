package com.example.neslaram.marvel.data.local.repository;

import com.example.neslaram.marvel.data.model.Character;
import com.example.neslaram.marvel.utils.Contants;

import io.realm.Realm;
import io.realm.RealmResults;
import rx.Observable;

/**
 * Created by neslaram on 04/09/16.
 */
public class MainLocalRepositoryImpl implements MainLocalRepository {


    @Override
    public Observable<RealmResults<Character>> getCharacters() {
        Realm realm = Realm.getDefaultInstance();
        return realm.where(Character.class)
                .findAllSortedAsync(Contants.KEY_NAME)
                .asObservable();

    }
}
