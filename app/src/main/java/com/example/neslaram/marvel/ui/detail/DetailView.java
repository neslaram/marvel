package com.example.neslaram.marvel.ui.detail;

import com.example.neslaram.marvel.data.model.Character;

/**
 * Created by neslaram on 04/09/16.
 */
public interface DetailView {
    void setItem(Character item);
    void showProgressBar();
    void hideProgressBar();
    void showErrorMessage(String error);

}
