package sample;

import java.util.HashMap;
import java.util.Map;

public class Telebook {
    private  Map<String, String> kontakty = new HashMap<>();
    private String imie = "-";
    private String numer = "--";

    Telebook(String imie, String numer){
        this.imie = imie;
        this.numer = numer;
    }

    Telebook(Map<String,String> kontakty){
        this.kontakty = kontakty;
    }

    public String getImie() {
        return imie;
    }

    public String getNumer() {
        return numer;
    }

    public Map<String, String> getKontakty() {
        return kontakty;
    }

    public void addNewContact(String name, String number) {
        kontakty.put(name, number);
    }

    public void eraseContact(String name) {
        if (kontakty.containsKey(name)) {
            kontakty.remove(name);
            System.out.println("Teraz masz " + kontakty.size() + " kontaktów");
        } else System.out.println("Nie ma takiego kontaktu");
    }

    public void bookSize() {
        System.out.println(kontakty.size());
    }
    public String koko(){
        return "kokokokoko";
    }

    public void searchByName(String fragment) {
        int r = 0;
        for (Map.Entry<String, String> e : kontakty.entrySet()) {
            if (e.getKey().toLowerCase().contains(fragment.toLowerCase())) {
                System.out.println("Found -> " + e.getKey());
                r++;
            }
        }
        if (r == 0)
            System.out.println("Nie ma takiego kontaktu");
    }

    public String toString(){
        return this.imie + " " + this.numer;
    }
}
