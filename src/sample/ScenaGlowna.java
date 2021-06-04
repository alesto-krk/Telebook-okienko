package sample;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
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
    @FXML
    TextField usunImie;
    @FXML
    TextField szukaj;
    @FXML
    GridPane znalezioneKontakty;

    public void dodajDoListy(ActionEvent e){
        //System.out.println("dodano " + imie.getText() + "  " + numerTelefonu.getText());
        listaNumerow.add(new Telebook(imie.getText(), numerTelefonu.getText()));
        System.out.println("dodano " + listaNumerow);
        imie.clear();
        numerTelefonu.clear();
    }

    public void usun(ActionEvent e){
        int r=0;
        for (Telebook l : listaNumerow) {
                if (usunImie.getText().equals(l.getImie())) {
                    System.out.println("erased");
                    listaNumerow.remove(l);    //TU SIE WYKRZACZA JAK SIE WPISZE W USUN PIERWSZE IMIE Z LISTY, JAK NASTEPNE TO OK
                    r++;
                    break;
                    }
        }
        if(r==0) {
            System.out.println("nie ma takiego kontaktu");
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Coś poszło nie tak");
            alert.setHeaderText("Nie ma takiego kontaktu");
            alert.setContentText("");
            alert.show();
        }
        usunImie.clear();
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

    public void pokaz2(LinkedList<Telebook> lista) {
        this.listaNumerow.addAll(lista);
    }

    //do metody szukajKontakt:
    public void listaZnalezionychNumerow(LinkedList<Telebook> listaZ, String fragmentZ){
        int rowIndex = 0;
        this.listaNumerow.addAll(listaZ);
        System.out.println(listaNumerow);
        for (Telebook l : listaNumerow) {
            if (l.getImie().contains(fragmentZ)) {
                Label listaLabela = new Label(rowIndex+1 + ". " + l.getImie() + " " + l.getNumer());
                znalezioneKontakty.getChildren().add(listaLabela);
                znalezioneKontakty.setConstraints(listaLabela, 0, rowIndex);
                rowIndex++;
            }
        }
    }
    //

    public void szukajKontakt(ActionEvent event) throws IOException{
        LinkedList<Telebook> username = listaNumerow;
        String fragment = szukaj.getText();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("sample.fxml"));
        root = loader.load();
        ScenaGlowna scene2Controller = loader.getController();
        scene2Controller.listaZnalezionychNumerow(username, fragment);
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public void zakoncz(){
        Platform.exit();}
}
