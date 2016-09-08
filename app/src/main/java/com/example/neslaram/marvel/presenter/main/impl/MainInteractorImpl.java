package com.example.neslaram.marvel.presenter.main.impl;

import com.example.neslaram.marvel.data.local.repository.MainLocalRepository;
import com.example.neslaram.marvel.data.local.repository.MainLocalRepositoryImpl;
import com.example.neslaram.marvel.data.model.Character;
import com.example.neslaram.marvel.data.model.responses.CharacterResponse;
import com.example.neslaram.marvel.data.remote.repository.MainRepository;
import com.example.neslaram.marvel.data.remote.repository.MainRepositoryImpl;
import com.example.neslaram.marvel.presenter.main.MainInteractor;

import io.realm.RealmResults;
import rx.Observable;

/**
 * Created by desarrollo on 7/6/16.
 */
public class MainInteractorImpl implements MainInteractor {

    private MainRepository remoteRepository;
    private MainLocalRepository localRepository;


    public MainInteractorImpl() {
        this.remoteRepository = new MainRepositoryImpl();
        this.localRepository = new MainLocalRepositoryImpl();
    }

    @Override
    public Observable<CharacterResponse> getCharacters(int offset) {
        return remoteRepository.getCharacters(offset);
    }

    @Override
    public Observable<RealmResults<Character>> getLocalCharacters() {
        return localRepository.getCharacters();
    }
}
