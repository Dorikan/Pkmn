package su.drkn.pkmn.dtos;

import lombok.*;
import su.drkn.pkmn.models.card.AttackSkill;
import su.drkn.pkmn.models.card.Card;
import su.drkn.pkmn.models.card.EnergyType;
import su.drkn.pkmn.models.card.PokemonStage;
import su.drkn.pkmn.models.student.Student;
import su.drkn.pkmn.services.CardService;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter @Setter
public class CardDTO {

    private String imageLink;
    private String name;
    private String number;
    private String retreatCost;
    private String gameSet;

    private PokemonStage pokemonStage;

    private EnergyType pokemonType;
    private EnergyType weaknessType;
    private EnergyType resistanceType;

    private char regulationMark;
    private short hp;

    private Student pokemonOwner;

    private List<AttackSkill> skills;

    private CardDTO evolvesFrom;

    public static CardDTO fromModel(Card card, CardService service)
    {
        CardDTOBuilder builder = CardDTO.builder()
                .name(card.getName())
                .number(card.getNumber())
                .retreatCost(card.getRetreatCost())
                .gameSet(card.getGameSet())
                .pokemonStage(card.getPokemonStage())
                .pokemonType(card.getPokemonType())
                .weaknessType(card.getWeaknessType())
                .resistanceType(card.getResistanceType())
                .regulationMark(card.getRegulationMark())
                .hp(card.getHp())
                .pokemonOwner(card.getPokemonOwner())
                .skills(card.getSkills());
        if (card.getEvolvesFrom() != null)
        {
            builder.evolvesFrom(fromModel(card.getEvolvesFrom(), service));
        }
        builder.imageLink(service.getCardImageLink(card));
        return builder.build();
    }
}