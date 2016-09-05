package com.example.neslaram.marvel.presenter.main.impl;

import com.example.neslaram.marvel.data.model.responses.CharacterResponse;
import com.example.neslaram.marvel.data.remote.repository.MainRepository;
import com.example.neslaram.marvel.data.remote.repository.MainRepositoryImpl;
import com.example.neslaram.marvel.presenter.main.MainInteractor;

import rx.Observable;

/**
 * Created by desarrollo on 7/6/16.
 */
public class MainInteractorImpl implements MainInteractor {

    private MainRepository mainRepository;


    public MainInteractorImpl() {
        this.mainRepository = new MainRepositoryImpl();
    }

    @Override
    public Observable<CharacterResponse> getCharacters(String page) {
        return mainRepository.getCharacters(page);
    }
}
