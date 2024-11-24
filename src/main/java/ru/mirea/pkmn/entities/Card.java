package ru.mirea.pkmn.entities;


import java.io.Serial;
import java.io.Serializable;
import java.util.List;
import java.util.UUID;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.annotations.Type;
import org.hibernate.annotations.UuidGenerator;
import ru.mirea.pkmn.AttackSkill;
import ru.mirea.pkmn.EnergyType;
import ru.mirea.pkmn.PokemonStage;
import ru.mirea.pkmn.converters.AttackSkillConverter;
import ru.mirea.pkmn.converters.AttackSkillConverterJSON;
import ru.mirea.pkmn.converters.EnergyTypeConverter;

import static org.hibernate.type.SqlTypes.JSON;

@Entity
@Table(name = "card")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Setter @Getter
public class Card implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue
    @UuidGenerator
    private UUID id;

    @Column(name="name")
    private String name;
    @Column(columnDefinition = "smallint")
    private short hp;

    @Column(name="card_number")
    private String number;
    @Enumerated(EnumType.STRING)
    @Column(name="stage")
    private PokemonStage pokemonStage;
    @Column(name="retreat_cost")
    private String retreatCost;

    @Convert(converter = EnergyTypeConverter.class)
    @Column(name="pokemon_type", nullable = true)
    private EnergyType pokemonType;
    @Convert(converter = EnergyTypeConverter.class)
    @Column(name="weakness_type", nullable = true)
    private EnergyType weaknessType;
    @Convert(converter = EnergyTypeConverter.class)
    @Column(name="resistance_type", nullable = true)
    private EnergyType resistanceType;
    @Column(name="game_set")
    private String gameSet;
    @Column(name="regulation_mark")
    private char regulationMark;
    @ManyToOne(cascade = CascadeType.ALL, optional = true)
    @JoinColumn(name = "pokemon_owner")
    private Student pokemonOwner;

    @JdbcTypeCode(JSON)
    @Column(name="attack_skills", columnDefinition = "JSON")
    private List<AttackSkill> skills;

    @ManyToOne(cascade = CascadeType.ALL, optional = true)
    @JoinColumn(name = "evolves_from")
    private Card evolvesFrom;

    @Override
    public String toString() {
        return "Card{" +
                "pokemonStage=" + pokemonStage +
                ", name='" + name + '\'' +
                ", hp=" + hp +
                ", evolvesFrom=" + evolvesFrom +
                ", skills=" + skills +
                ", pokemonType=" + pokemonType +
                ", weaknessType=" + weaknessType +
                ", resistanceType=" + resistanceType +
                ", retreatCost='" + retreatCost + '\'' +
                ", gameSet='" + gameSet + '\'' +
                ", regulationMark=" + regulationMark +
                ", owner=" + pokemonOwner.toString() +
                '}';
    }
}
