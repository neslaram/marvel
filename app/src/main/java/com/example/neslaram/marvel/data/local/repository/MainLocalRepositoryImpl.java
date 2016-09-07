package com.example.neslaram.marvel.data.local.repository;

import com.example.neslaram.marvel.data.model.Character;
import com.example.neslaram.marvel.utils.Contants;

import java.util.List;

import io.realm.Realm;

/**
 * Created by neslaram on 04/09/16.
 */
public class MainLocalRepositoryImpl implements MainLocalRepository {


    @Override
    public List<Character> getCharacters() {
        Realm realm = Realm.getDefaultInstance();
        return realm.where(Character.class)
                .findAllSorted(Contants.KEY_NAME);
    }
}
