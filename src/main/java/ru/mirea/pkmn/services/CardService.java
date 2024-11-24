package ru.mirea.pkmn.services;

import jakarta.servlet.http.HttpServletRequest;
import ru.mirea.pkmn.entities.Card;

public interface CardService {
    Card saveCard(Card card);
    Card findCardByRequest(HttpServletRequest request);
}