package com.example.neslaram.marvel.data.remote.repository;

import com.example.neslaram.marvel.data.model.responses.CharacterResponse;
import com.example.neslaram.marvel.data.remote.service.MarvelApi;
import com.example.neslaram.marvel.data.remote.service.MarvelClient;

import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by neslaram on 04/09/16.
 */
public class DetailRepositoryImpl implements DetailRepository {
    private MarvelClient apiService;

    public DetailRepositoryImpl() {
        apiService = MarvelApi.getApiService();
    }

    @Override
    public Observable<CharacterResponse> getCharacter(int id) {
        return apiService.getCharacter(id)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .unsubscribeOn(Schedulers.io());
    }
}
