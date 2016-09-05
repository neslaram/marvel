package com.example.neslaram.marvel.presenter.detail.impl;

import com.example.neslaram.marvel.data.model.Character;
import com.example.neslaram.marvel.data.model.responses.CharacterResponse;
import com.example.neslaram.marvel.presenter.detail.DetailInteractor;
import com.example.neslaram.marvel.presenter.detail.DetailPresenter;
import com.example.neslaram.marvel.ui.detail.DetailView;

import java.util.List;

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

    public DetailPresenterImpl(DetailView detailView) {
        this.detailView = detailView;
        this.detailInteractor = new DetailInteractorImpl();
        this.mSubscriptions = new CompositeSubscription();

    }

    @Override
    public void onDestroy() {
        this.detailView = null;
        mSubscriptions.clear();
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

    private void addSubscription(Subscription subscription) {
        mSubscriptions.add(subscription);
    }


    private void getCharacterSuccess(CharacterResponse response) {
        if (detailView != null) {
            CharacterResponse.Data data = response.getData();
            List<Character> results = data.getResults();
            detailView.setItem(results.get(0));
            detailView.hideProgressBar();
        }
    }

    private void getCharacterError(String error) {
        if (detailView != null) {
            detailView.showErrorMessage(error);
            detailView.hideProgressBar();
        }
    }

}
