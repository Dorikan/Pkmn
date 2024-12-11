package su.drkn.pkmn.services;

import su.drkn.pkmn.models.card.Card;
import su.drkn.pkmn.models.card.CardEntity;
import su.drkn.pkmn.models.card.PokemonStage;

import java.util.List;
import java.util.UUID;

public interface CardService {
    public Card getCardById(UUID id);
    public Card getCardByCardName(String cardName);
    public Card getCardByOwnerId(String ownerId);
    public Card getCardByOwnerName(String ownerName);
    public Card getCardByOwnerName(String ownerFamilyName, String ownerFirstName, String ownerSurName);
    public List<Card> getAllCards();

    public CardEntity saveCard(Card card);
    public void deleteCard(Card card);

    public String getCardImageLink(Card card);
}