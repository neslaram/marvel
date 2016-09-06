package com.example.neslaram.marvel.presenter.detail;


/**
 * Created by desarrollo on 7/6/16.
 */
public interface DetailPresenter {

    void onCreate();
    void onDestroy();

    void getCharacter(int id);
    void getLocalCharacter(int id);

}
