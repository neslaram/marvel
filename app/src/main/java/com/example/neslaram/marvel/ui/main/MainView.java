package com.example.neslaram.marvel.ui.main;

import com.example.neslaram.marvel.data.model.Character;

import java.util.List;

/**
 * Created by neslaram on 04/09/16.
 */
public interface MainView {
    void setItems(List<Character> items);
    void showErrorMessage(String error);

}
