package ru.mirea.pkmn.web.jdbc;

import com.fasterxml.jackson.core.JsonProcessingException;
import ru.mirea.pkmn.entities.Card;
import ru.mirea.pkmn.entities.Student;

import java.util.UUID;

public interface DatabaseService {
    Card getCardFromDatabase(String cardName);

    Student getStudentFromDatabase(String studentName);

    void saveCardToDatabase(Card card) throws JsonProcessingException;

    UUID createPokemonOwner(Student owner);
}
