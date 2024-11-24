package ru.mirea.pkmn.repository;

import jakarta.persistence.EntityManager;
import ru.mirea.pkmn.entities.*;

import lombok.RequiredArgsConstructor;

import java.util.UUID;

@RequiredArgsConstructor
public class CardRepoImpl implements CardRepo{

    private final EntityManager entityManager;

    @Override
    public Card save(Card entity) {
        entityManager.getTransaction().begin();
        entityManager.persist(entity);
        entityManager.getTransaction().commit();
        return entity;
    }

    @Override
    public Card findById(UUID id) {
        return entityManager.find(Card.class, id);
    }

    @Override
    public Card findByName(String cardName) {
        return entityManager.createQuery("""
            select c from Card c
            where c.name = :cardName
            """, Card.class)
                .setParameter("cardName", cardName)
                .getResultList().getFirst();
    }

    @Override
    public Card findByNumber(Integer number) {
        return entityManager.createQuery("""
            SELECT c FROM Card c
            WHERE c.number= :number
            """, Card.class)
                .setParameter("number", number)
                .getResultList().getFirst();
    }

    @Override
    public Card findByFullName(String firstName, String familyName, String surName) {

        return entityManager.createQuery("""
            select c from Card c
            join Student s on c.pokemonOwner.id = s.id
            where s.firstName = :firstName
              and s.familyName = :familyName
              and s.surName = :surName
        """, Card.class)
                .setParameter("firstName", firstName)
                .setParameter("familyName", familyName)
                .setParameter("surName", surName)
                .getResultList().getFirst();
    }

    @Override
    public boolean cardExists(Card card) {
        /*AND
                        c.number = :number AND
                        c.pokemonOwner = :pokemonOwner AND
                        c.evolvesFrom = :evolveFrom AND
                        c.gameSet = :gameSet*/
        return entityManager.createQuery("""
                        SELECT COUNT(c) > 0 FROM Card c WHERE c.name = :name
                        """, Boolean.class)
                .setParameter("name", card.getName())
                .getSingleResult();
    }
}
