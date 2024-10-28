package ru.mirea.pkmn.ChepelIV;
import com.fasterxml.jackson.databind.JsonNode;
import ru.mirea.pkmn.Card;
import ru.mirea.pkmn.Student;
import ru.mirea.pkmn.web.http.*;
import ru.mirea.pkmn.web.jdbc.DatabaseService;
import ru.mirea.pkmn.web.jdbc.DatabaseServiceImpl;

import java.io.IOException;
import java.sql.SQLException;
import java.util.stream.Collectors;

public class PkmnApplication {
    public static void main(String[] args) throws ClassNotFoundException, IOException, SQLException {
        PkmnHttpClient httpClient = new PkmnHttpClient();
        DatabaseServiceImpl db = new DatabaseServiceImpl();

        Card myCard = CardImport.importCardFromTxt("src/main/resources/my_card.txt");
        CardImport.updateAttackSkill(myCard, httpClient);

        db.saveCardToDatabase(myCard);
    }
}