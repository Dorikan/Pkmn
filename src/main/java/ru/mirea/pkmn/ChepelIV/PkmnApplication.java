package ru.mirea.pkmn.ChepelIV;
import ru.mirea.pkmn.Card;

public class PkmnApplication {
    public static void main(String[] args) throws ClassNotFoundException {
        Card card2 = CardImport.cardImportByte("src\\main\\resources\\crd\\Pyroar.crd");
        Card card3 = CardImport.cardImportByte("src\\main\\resources\\crd\\Croagunk.crd");
        Card myCard = CardImport.importCardFromTxt("src\\main\\resources\\my_card.txt");

        System.out.println(card2.toString());
        System.out.println(card3.toString());
    }
}