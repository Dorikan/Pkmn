package ru.mirea.ChepelIV.pkmn;

public class PkmnApplication {
    public static void main(String[] args) throws ClassNotFoundException {
        Card myCard = CardImport.cardImportByte("src\\main\\resources\\Durant.crd");
        System.out.println(myCard.toString());
        CardExport.cardSerialization(myCard);
    }
}