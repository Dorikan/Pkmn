package ru.mirea.pkmn.repository;

import ru.mirea.pkmn.entities.Card;

import java.util.UUID;

public interface CardRepo {

    Card save(Card entity);

    Card findById(UUID id);

    Card findByName(String cardName);

    Card findByNumber(Integer number);

    Card findByFullName(String firstName, String familyName, String surName);

    boolean cardExists(Card card);

}
