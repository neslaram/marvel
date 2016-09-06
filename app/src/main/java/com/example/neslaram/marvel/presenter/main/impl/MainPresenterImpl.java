package com.example.neslaram.marvel.presenter.main.impl;

import com.example.neslaram.marvel.data.model.Character;
import com.example.neslaram.marvel.data.model.responses.CharacterResponse;
import com.example.neslaram.marvel.presenter.main.MainInteractor;
import com.example.neslaram.marvel.presenter.main.MainPresenter;
import com.example.neslaram.marvel.ui.main.MainView;

import java.util.List;

import io.realm.Realm;
import rx.Observer;
import rx.Subscription;
import rx.subscriptions.CompositeSubscription;

/**
 * Created by desarrollo on 7/6/16.
 */
public class MainPresenterImpl implements MainPresenter {

    private static final String TAG = MainPresenterImpl.class.getSimpleName();

    private MainView mainView;
    private MainInteractor mainInteractor;
    private CompositeSubscription mSubscriptions;

    public MainPresenterImpl(MainView mainView) {
        this.mainView = mainView;
        this.mainInteractor = new MainInteractorImpl();
        this.mSubscriptions = new CompositeSubscription();
    }

    @Override
    public void onDestroy() {
        this.mainView = null;
        mSubscriptions.clear();
    }

    @Override
    public void getCharacters(int offset) {
        mainView.showProgressBar();
        Subscription subscription = mainInteractor.getCharacters(offset).subscribe(new Observer<CharacterResponse>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {
                getCharacterError(e.getMessage());
            }

            @Override
            public void onNext(CharacterResponse characterResponse) {
                getCharacterSuccess(characterResponse);

            }
        });
        addSubscription(subscription);
    }

    @Override
    public void getLocalCharacters(int offset) {
        mainView.showProgressBar();
        List<Character> results = mainInteractor.getLocalCharacters(offset);
        getLocalCharacterSuccess(results);
    }

    private void addSubscription(Subscription subscription) {
        mSubscriptions.add(subscription);
    }


    private void getCharacterSuccess(CharacterResponse response) {
        if (mainView != null) {
            CharacterResponse.Data data = response.getData();
            List<Character> results = data.getResults();

            copyToRealmOrUpdate(results);

            mainView.setItems(results, data.getTotal());
            mainView.hideProgressBar();
        }
    }

    private void getCharacterError(String error) {
        if (mainView != null) {
            mainView.showErrorMessage(error);
            mainView.hideProgressBar();
        }
    }

    private void getLocalCharacterSuccess(List<Character> results) {
        if (mainView != null) {
            mainView.setItems(results, 1000);
            mainView.hideProgressBar();
        }
    }

    public void copyToRealmOrUpdate(List<Character> objects) {
        Realm realm = Realm.getDefaultInstance();
        realm.beginTransaction();
        realm.copyToRealmOrUpdate(objects);
        realm.commitTransaction();
    }


}
