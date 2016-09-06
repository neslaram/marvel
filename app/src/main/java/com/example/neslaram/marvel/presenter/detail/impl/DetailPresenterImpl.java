package com.example.neslaram.marvel.presenter.detail.impl;

import com.example.neslaram.marvel.data.model.Character;
import com.example.neslaram.marvel.data.model.responses.CharacterResponse;
import com.example.neslaram.marvel.presenter.detail.DetailInteractor;
import com.example.neslaram.marvel.presenter.detail.DetailPresenter;
import com.example.neslaram.marvel.ui.detail.DetailView;

import java.util.List;

import io.realm.Realm;
import rx.Observer;
import rx.Subscription;
import rx.subscriptions.CompositeSubscription;

/**
 * Created by desarrollo on 7/6/16.
 */
public class DetailPresenterImpl implements DetailPresenter {

    private static final String TAG = DetailPresenterImpl.class.getSimpleName();

    private DetailView detailView;
    private DetailInteractor detailInteractor;
    private CompositeSubscription mSubscriptions;
    private Realm realm;

    public DetailPresenterImpl(DetailView detailView) {
        this.detailView = detailView;
        this.detailInteractor = new DetailInteractorImpl();
        this.mSubscriptions = new CompositeSubscription();

    }

    @Override
    public void onCreate() {
        realm = Realm.getDefaultInstance();
    }

    @Override
    public void onDestroy() {
        this.detailView = null;
        mSubscriptions.clear();
        realm.close();
    }

    @Override
    public void getCharacter(int id) {
        detailView.showProgressBar();
        Subscription subscription = detailInteractor.getCharacter(id).subscribe(new Observer<CharacterResponse>() {
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
    public void getLocalCharacter(int id) {
        detailView.showProgressBar();
        Character result = detailInteractor.getLocalCharacter(id);
        getLocalCharacterSuccess(result);
    }

    private void addSubscription(Subscription subscription) {
        mSubscriptions.add(subscription);
    }


    private void getCharacterSuccess(CharacterResponse response) {
        if (detailView != null) {
            CharacterResponse.Data data = response.getData();
            List<Character> results = data.getResults();
            Character character = results.get(0);
            if (character != null)
                copyToRealmOrUpdate(character);
            detailView.setItem(character);
            detailView.hideProgressBar();
        }
    }

    private void getCharacterError(String error) {
        if (detailView != null) {
            detailView.showErrorMessage(error);
            detailView.hideProgressBar();
        }
    }

    public void copyToRealmOrUpdate(Character object) {
        realm.beginTransaction();
        realm.copyToRealmOrUpdate(object);
        realm.commitTransaction();
    }

    private void getLocalCharacterSuccess(Character result) {
        if (detailView != null) {
            if (result != null)
                copyToRealmOrUpdate(result);
            detailView.setItem(result);
            detailView.hideProgressBar();
        }
    }

}
