package com.example.neslaram.marvel.presenter.main;


/**
 * Created by desarrollo on 7/6/16.
 */
public interface MainPresenter {

    void onCreate();
    void onDestroy();

    void getCharacters(int offset);
    void getLocalCharacters();

}
