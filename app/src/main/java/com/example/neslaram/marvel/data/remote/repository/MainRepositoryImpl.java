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
public class MainRepositoryImpl implements MainRepository {
    private MarvelClient apiService;

    public MainRepositoryImpl() {
        apiService = MarvelApi.getApiService();
    }

    @Override
    public Observable<CharacterResponse> getCharacters(int offset) {
        return apiService.getCharacters(offset)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .unsubscribeOn(Schedulers.io());
    }
}
