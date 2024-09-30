package ru.mirea.ChepelIV;

public class PkmnApplication {
    public static void main(String[] args) throws ClassNotFoundException {
        Card myCard = CardImport.cardImportByte("src\\main\\resources\\Gengar Gigantamax.crd");
        System.out.println(myCard.toString());
        CardExport.cardSerialization(myCard);
    }
}