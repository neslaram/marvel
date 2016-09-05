package com.example.neslaram.marvel.presenter.main.impl;

import com.example.neslaram.marvel.data.model.Character;
import com.example.neslaram.marvel.data.model.responses.CharacterResponse;
import com.example.neslaram.marvel.presenter.main.MainInteractor;
import com.example.neslaram.marvel.presenter.main.MainPresenter;
import com.example.neslaram.marvel.ui.main.MainView;

import java.util.List;

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

    private void addSubscription(Subscription subscription) {
        mSubscriptions.add(subscription);
    }


    private void getCharacterSuccess(CharacterResponse response) {
        if (mainView != null) {
            List<Character> results = response.getData().getResults();
            int size = results.size();
            mainView.setItems(results);
            mainView.hideProgressBar();
        }
    }

    private void getCharacterError(String error) {
        if (mainView != null) {
            mainView.showErrorMessage(error);
            mainView.hideProgressBar();
        }
    }

}
