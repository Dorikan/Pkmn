package ru.mirea.ChepelIV;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.Date;

public class CardExport {
    public static void cardSerialization(Card target) {
        try {
            ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream("C:\\Users\\User\\IdeaProjects\\Pkmn\\src\\main\\resources\\" + target.getName() + ".crd"));
            Date now = new Date(System.currentTimeMillis());
            out.writeObject(now);
            out.writeObject(target);
            out.flush();
            out.close();
        }
        catch (IOException e){
            throw new RuntimeException("Ошибка создания файла");
        }
    }
}
