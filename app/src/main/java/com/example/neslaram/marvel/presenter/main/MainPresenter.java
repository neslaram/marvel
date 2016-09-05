package com.example.neslaram.marvel.presenter.main;


import com.example.neslaram.marvel.ui.main.MainView;

/**
 * Created by desarrollo on 7/6/16.
 */
public interface MainPresenter {

    void onDestroy();

    void getCharacters(String page);

}
