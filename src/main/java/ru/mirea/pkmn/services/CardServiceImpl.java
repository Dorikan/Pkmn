package ru.mirea.pkmn.services;


import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import ru.mirea.pkmn.entities.Card;
import ru.mirea.pkmn.entities.Student;
import ru.mirea.pkmn.repository.CardRepo;
import ru.mirea.pkmn.repository.StudentRepo;

import jakarta.transaction.Transactional;
import java.util.Optional;

import static ru.mirea.pkmn.utils.ClassUtils.*;

public class CardServiceImpl implements CardService {
    private final CardRepo cardRepo;
    private final StudentRepo studentRepo;

    public CardServiceImpl(CardRepo cardRepo, StudentRepo studentRepo) {
        this.cardRepo = cardRepo;
        this.studentRepo = studentRepo;
    }

    @Override
    public Card saveCard(Card card) {
        if (cardRepo.cardExists(card)) {
            throw new IllegalArgumentException("есть такая карточка хихихихи удаляй удаляяяяй че деалешь то!!!");
        }
        if(card.getPokemonOwner() != null){
            if(studentRepo.studentExists(card.getPokemonOwner())){
                card.setPokemonOwner(studentRepo.findExceptOfId(card.getPokemonOwner()));
            }
            else {
                card.setPokemonOwner(studentRepo.save(card.getPokemonOwner()));
            }
        }
        if(card.getEvolvesFrom() != null)
        {
            if(cardRepo.cardExists(card.getEvolvesFrom())){
                card.setEvolvesFrom(cardRepo.findByName(card.getEvolvesFrom().getName()));
            }
            else {
                card.setEvolvesFrom(cardRepo.save(card.getEvolvesFrom()));
            }
        }
        return cardRepo.save(card);
    }

    @Override
    public Card findCardByRequest(HttpServletRequest request) {

        String cardName = request.getParameter("cardName");
        String cardNumber = request.getParameter("cardNumber");

        String cardOwnerFirstName = request.getParameter("cardOwnerFirstName");
        String cardOwnerFamilyName = request.getParameter("cardOwnerFamilyName");
        String cardOwnerSurName = request.getParameter("cardOwnerSurName");

        if (cardName != null) {
            return cardRepo.findByName(cardName);
        } else if (cardNumber != null) {
            return cardRepo.findByNumber(Integer.parseInt(cardNumber));
        } else if (cardOwnerFirstName != null && cardOwnerFamilyName != null && cardOwnerSurName != null) {
            return cardRepo.findByFullName(cardOwnerFirstName, cardOwnerFamilyName, cardOwnerSurName);
        } else {
            return null;
        }
    }
}