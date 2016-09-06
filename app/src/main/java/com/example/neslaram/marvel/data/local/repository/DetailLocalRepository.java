package com.example.neslaram.marvel.data.local.repository;

import com.example.neslaram.marvel.data.model.Character;

/**
 * Created by neslaram on 04/09/16.
 */
public interface DetailLocalRepository {
    Character getCharacter(int id);
}
