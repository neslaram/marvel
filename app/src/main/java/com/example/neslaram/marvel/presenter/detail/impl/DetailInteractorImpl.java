package com.example.neslaram.marvel.presenter.detail.impl;

import com.example.neslaram.marvel.data.local.repository.DetailLocalRepository;
import com.example.neslaram.marvel.data.local.repository.DetailLocalRepositoryImpl;
import com.example.neslaram.marvel.data.model.Character;
import com.example.neslaram.marvel.data.model.responses.CharacterResponse;
import com.example.neslaram.marvel.data.remote.repository.DetailRepository;
import com.example.neslaram.marvel.data.remote.repository.DetailRepositoryImpl;
import com.example.neslaram.marvel.presenter.detail.DetailInteractor;

import rx.Observable;

/**
 * Created by desarrollo on 7/6/16.
 */
public class DetailInteractorImpl implements DetailInteractor {

    private DetailRepository remoteRepository;
    private DetailLocalRepository localRepository;


    public DetailInteractorImpl() {
        this.remoteRepository = new DetailRepositoryImpl();
        this.localRepository = new DetailLocalRepositoryImpl();
    }

    @Override
    public Observable<CharacterResponse> getCharacter(int id) {
        return remoteRepository.getCharacter(id);
    }

    @Override
    public Character getLocalCharacter(int id) {
        return localRepository.getCharacter(id);
    }


}
