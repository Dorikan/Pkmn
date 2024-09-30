package ru.mirea.ChepelIV;

public class PkmnApplication {
    public static void main(String[] args) {
        Card myCard = CardImport.importCardFromTxt("C:\\Users\\User\\IdeaProjects\\Pkmn\\src\\main\\resources\\my_card.txt");
        System.out.println(myCard.toString());
        CardExport.cardSerialization(myCard);
    }
}