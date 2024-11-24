package ru.mirea.pkmn.converters;

import ru.mirea.pkmn.AttackSkill;

import java.util.List;

public interface AttackSkillConverter {
    String convertToDatabaseColumn(List<AttackSkill> attackSkills);
    List<AttackSkill> convertToEntityAttribute(String dbData);
}
