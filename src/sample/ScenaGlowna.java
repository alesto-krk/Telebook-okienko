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
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class ScenaGlowna {

    LinkedList<Telebook> listaNumerow = new LinkedList<>();
    private Stage stage;
    private Scene scene;
    private Parent root;

    @FXML
    TextField imie;
    @FXML
    TextField numerTelefonu;

    public void dodajDoListy(ActionEvent e){
        System.out.println("dodano " + imie.getText() + "  " + numerTelefonu.getText());
        //listaNumerow.add(imie.getText());
        listaNumerow.add(new Telebook(imie.getText(), numerTelefonu.getText()));
        imie.clear();
        numerTelefonu.clear();
    }


    public void lista(ActionEvent event) throws IOException {
        LinkedList<Telebook> username = listaNumerow;
        FXMLLoader loader = new FXMLLoader(getClass().getResource("listaKontaktow.fxml"));
        root = loader.load();
        ScenaListaKontaktow scene2Controller = loader.getController();
        scene2Controller.pokaz(username);
        //root = FXMLLoader.load(getClass().getResource("Scene2.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public void zakoncz(){
        Platform.exit();}
}
