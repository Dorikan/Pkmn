package ru.mirea.pkmn;

import java.io.Serial;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Card implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    private PokemonStage pokemonStage;
    private String name;
    private int hp;
    private Card evolvesFrom;
    private List<AttackSkill> skills;
    private EnergyType pokemonType,  weaknessType, resistanceType;
    private String retreatCost;
    private String gameSet;
    private char regulationMark;
    private Student pokemonOwner;

    public Card(){}

    public Card(PokemonStage pokemonStage, String name, int hp, Card evolvesFrom, List<AttackSkill> skills,
                EnergyType pokemonType, EnergyType weaknessType, EnergyType resistanceType,
                String retreatCost, String gameSet, char regulationMark) {
        this.pokemonStage = pokemonStage;
        this.name = name;
        this.hp = hp;
        this.evolvesFrom = evolvesFrom;
        this.skills = skills;
        this.pokemonType = pokemonType;
        this.weaknessType = weaknessType;
        this.resistanceType = resistanceType;
        this.retreatCost = retreatCost;
        this.gameSet = gameSet;
        this.regulationMark = regulationMark;
    }

    public PokemonStage getPokemonStage() {
        return pokemonStage;
    }

    public void setPokemonStage(PokemonStage pokemonStage) {
        this.pokemonStage = pokemonStage;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getHp() {
        return hp;
    }

    public void setHp(int hp) {
        this.hp = hp;
    }

    public Card getEvolvesFrom() {
        return evolvesFrom;
    }

    public void setEvolvesFrom(Card evolvesFrom) {
        this.evolvesFrom = evolvesFrom;
    }

    public List<AttackSkill> getSkills() {
        return skills;
    }

    public void setSkills(ArrayList<AttackSkill> skills) {
        this.skills = skills;
    }

    public EnergyType getPokemonType() {
        return pokemonType;
    }

    public void setPokemonType(EnergyType pokemonType) {
        this.pokemonType = pokemonType;
    }

    public EnergyType getResistanceType() {
        return resistanceType;
    }

    public void setResistanceType(EnergyType resistanceType) {
        this.resistanceType = resistanceType;
    }

    public EnergyType getWeaknessType() {
        return weaknessType;
    }

    public void setWeaknessType(EnergyType weaknessType) {
        this.weaknessType = weaknessType;
    }

    public String getRetreatCost() {
        return retreatCost;
    }

    public void setRetreatCost(String retreatCost) {
        this.retreatCost = retreatCost;
    }

    public String getGameSet() {
        return gameSet;
    }

    public void setGameSet(String gameSet) {
        this.gameSet = gameSet;
    }

    public char getRegulationMark() {
        return regulationMark;
    }

    public void setRegulationMark(char regulationMark) {
        this.regulationMark = regulationMark;
    }

    public Student getPokemonOwner() {
        return pokemonOwner;
    }

    public void setPokemonOwner(Student pokemonOwner) {
        this.pokemonOwner = pokemonOwner;
    }

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
                '}';
    }
}
