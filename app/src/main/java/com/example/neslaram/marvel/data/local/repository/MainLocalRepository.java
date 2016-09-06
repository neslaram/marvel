package com.example.neslaram.marvel.data.local.repository;

import com.example.neslaram.marvel.data.model.Character;

import java.util.List;

/**
 * Created by neslaram on 04/09/16.
 */
public interface MainLocalRepository {
    List<Character> getCharacters(int offset);
}
