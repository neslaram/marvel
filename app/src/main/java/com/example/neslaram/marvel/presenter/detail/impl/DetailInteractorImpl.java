package com.example.neslaram.marvel.presenter.detail.impl;

import com.example.neslaram.marvel.data.model.responses.CharacterResponse;
import com.example.neslaram.marvel.data.remote.repository.DetailRepository;
import com.example.neslaram.marvel.data.remote.repository.DetailRepositoryImpl;
import com.example.neslaram.marvel.presenter.detail.DetailInteractor;

import rx.Observable;

/**
 * Created by desarrollo on 7/6/16.
 */
public class DetailInteractorImpl implements DetailInteractor {

    private DetailRepository detailRepository;


    public DetailInteractorImpl() {
        this.detailRepository = new DetailRepositoryImpl();
    }

    @Override
    public Observable<CharacterResponse> getCharacter(int id) {
        return detailRepository.getCharacter(id);
    }
}
