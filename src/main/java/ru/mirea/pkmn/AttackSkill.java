package ru.mirea.pkmn;

import lombok.Getter;
import lombok.Setter;

import java.io.Serial;
import java.io.Serializable;

@Setter
@Getter
public class AttackSkill  implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    private String name;
    private String description;
    private String cost;
    private int damage;

    public AttackSkill() {}

    public AttackSkill(String name, String description, String cost, int damage) {
        this.name = name;
        this.description = description;
        this.cost = cost;
        this.damage = damage;
    }

    @Override
    public String toString() {
        return "{" +
                "\"name\":\"" + name + '\"' +
                ", \"description\":\"" + description + '\"' +
                ", \"cost\":\"" + cost + '\"' +
                ", \"damage\":\"" + damage +
                "\"}";
    }
}
