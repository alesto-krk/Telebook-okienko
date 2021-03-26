package sample;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.util.LinkedList;
import java.util.List;

public class MetodyDoFxml {

    List<Telebook> listaNumerow = new LinkedList<>();
    //String imie = "-";
    //String telefon = "--";

    @FXML
    private TextField imie;

    public void dodajDoListy(ActionEvent e){
        System.out.println("dodano " + imie.getText());

    }

    public void zakoncz(){
        Platform.exit();}
}
