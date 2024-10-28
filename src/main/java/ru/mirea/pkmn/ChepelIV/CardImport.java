package ru.mirea.pkmn.ChepelIV;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import ru.mirea.pkmn.*;
import ru.mirea.pkmn.web.http.PkmnHttpClient;

public class CardImport {
    public static Card importCardFromTxt(String path){

        Card result = new Card();

        try{
            BufferedReader br = new BufferedReader(new FileReader(path));
            for (int i = 0; i < 13; i++){
                String tmp = br.readLine();
                switch (i){
                    case 0: result.setPokemonStage(PokemonStage.valueOf(tmp)); break;
                    case 1: result.setName(tmp); break;
                    case 2: result.setHp(Integer.parseInt(tmp)); break;
                    case 3: result.setPokemonType(EnergyType.valueOf(tmp.toUpperCase())); break;
                    case 4: result.setEvolvesFrom(
                            tmp.equalsIgnoreCase("none") ? null : importCardFromTxt(tmp)
                    ); break;
                    case 5: result.setSkills(getAttacksFromLine(tmp)); break;
                    case 6: result.setWeaknessType(
                            (tmp.equalsIgnoreCase("none")) ? null : EnergyType.valueOf(tmp.toUpperCase())
                    ); break;
                    case 7: result.setResistanceType(
                            (tmp.equalsIgnoreCase("none")) ? null : EnergyType.valueOf(tmp.toUpperCase())
                    ); break;
                    case 8: result.setRetreatCost(tmp); break;
                    case 9: result.setGameSet(tmp); break;
                    case 10: result.setRegulationMark(tmp.charAt(0)); break;
                    case 11: result.setPokemonOwner(getStudentFromLine(tmp)); break;
                    case 12: result.setNumber(Integer.parseInt(tmp)); break;
                }
            }
            br.close();
        }
        catch (FileNotFoundException e){
            throw new RuntimeException("Файл карты не найден");
        }
        catch (ArrayIndexOutOfBoundsException EnumConstantNotPresentException) {
            throw new RuntimeException("Неправильный формат файла карты");
        }
        catch (IOException e) {
            throw new RuntimeException(e);
        }

        return result;
    }

    public static <RandomClass> Card cardImportByte(String path) throws ClassNotFoundException {
        try {
            ObjectInputStream in = new ObjectInputStream(new FileInputStream(path));
            return (Card) in.readObject();
        }catch (IOException e){
            throw new RuntimeException("Путь до файла не найден");
        }
    }

    private static Student getStudentFromLine(String s) throws ArrayIndexOutOfBoundsException{
        Student result = new Student();
        if (s.equalsIgnoreCase("none")) return null;

        String[] tmp = s.split(" / ");

        result.setFirstName(tmp[1]);
        result.setSurName(tmp[2]);
        result.setFamilyName(tmp[0]);
        result.setGroup(tmp[3]);

        return result;
    }

    private static ArrayList<AttackSkill> getAttacksFromLine(String s) throws ArrayIndexOutOfBoundsException{
        ArrayList<AttackSkill> result = new ArrayList<>();

        for(String skill : s.split(",")){
            String[] tmp = skill.split(" / ");
            result.add(new AttackSkill(tmp[1], "", tmp[0], Integer.parseInt(tmp[2])));

        }

        return result;
    }

    public static void updateAttackSkill(Card card, PkmnHttpClient httpClient) throws IOException {
        if(card.getEvolvesFrom() != null){
            updateAttackSkill(card.getEvolvesFrom(), httpClient);
        }

        List<JsonNode> tmp = httpClient.getPokemonCard(card.getName(), card.getNumber()).findValues("text");
        for (int i = 0; i < tmp.size(); i++){
            card.getSkills().get(i).setDescription(tmp.get(i).asText());
        }
    }

    public static ArrayList<AttackSkill> parseAttackSkillsFromJson(String json) throws JsonProcessingException {
        ArrayList<AttackSkill> result = new ArrayList<>();
        ObjectMapper objectMapper = new ObjectMapper();
        ArrayNode tmp = (ArrayNode) objectMapper.readTree(json);
        for(int i = 0; i < tmp.size(); i++){
            JsonNode ji = tmp.get(i);
            AttackSkill as = new AttackSkill();
            as.setDescription(ji.findValue("text").toString());
            as.setCost(ji.findValue("cost").toString());
            as.setDamage((ji.get("damage").asInt()));
            as.setName(ji.findValue("name").toString());
            result.add(as);
        }
        return result;
    }

}
