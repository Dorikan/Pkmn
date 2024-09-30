package ru.mirea.ChepelIV;

import java.io.*;
import java.util.ArrayList;
import java.util.Date;

public class CardImport {
    public static Card importCardFromTxt(String path){

        Card result = new Card();

        try{
            BufferedReader br = new BufferedReader(new FileReader(path));
            for (int i = 0; i < 12; i++){
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
                    case 11: result.setOwner(getStudentFromLine(tmp)); break;
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
        if (s.equalsIgnoreCase("none")) return result;

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

}
