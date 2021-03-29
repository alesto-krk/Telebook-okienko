package sample;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

public class MetodyDoFxml {

    List<Telebook> listaNumerow = new LinkedList<>();
    private Stage stage;
    private Scene scene;
    //private Parent root;

    @FXML
    private TextField imie;
    @FXML
    private TextField numerTelefonu;
    @FXML
    private Label tooo;

    public void dodajDoListy(ActionEvent e){
        System.out.println("dodano " + imie.getText() + "  " + numerTelefonu.getText());
        listaNumerow.add(new Telebook(imie.getText(), numerTelefonu.getText()));
        imie.clear();
        numerTelefonu.clear();
        for(Telebook l: listaNumerow) {
            System.out.println(l.getImie());
    }
    }

    public void biezacaLista(){     //NIE DZIAALA!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
        //System.out.println("uzupelniono listę");
        tooo.setText("Nie dziala");

/*
        int rowIndex = 1;
        for(Telebook l: listaNumerow) {
            tooo.setText(l.getImie());
            //tooo = new Label(rowIndex  + ". " + l.getImie() + " " + l.getNumer());
            System.out.println(l.getImie());
            rowIndex++;
        }
*/

    }
    public void przelaczDoListyKontakow(ActionEvent event) throws IOException {
        System.out.println("przelaczono");
        Parent root = FXMLLoader.load(getClass().getResource("listaKontaktow.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public void zakoncz(){
        Platform.exit();}
}
