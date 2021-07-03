package sample;

public class SprawdzDane {

    public static boolean sprawdzImie(String imie){
        return imie.matches("[a-zA-Z]*");
    }

    public static boolean sprawdzNumer(String numer){
        return numer.matches("[0-9]{4}");
    }
}

