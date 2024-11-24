package ru.mirea.pkmn.converters;

import jakarta.persistence.Converter;
import jakarta.persistence.AttributeConverter;
import ru.mirea.pkmn.AttackSkill;
import ru.mirea.pkmn.utils.UtilsJSON;

import java.util.ArrayList;
import java.util.List;

@Converter
public class AttackSkillConverterJSON implements AttackSkillConverter, AttributeConverter<List<AttackSkill>, String> {

    @Override
    public String convertToDatabaseColumn(List<AttackSkill> attackSkills) {
        return UtilsJSON.AttackSkillsToJson(attackSkills);
    }

    @Override
    public ArrayList<AttackSkill> convertToEntityAttribute(String dbData) {
        return UtilsJSON.parseAttackSkillsFromJson(dbData);
    }
}