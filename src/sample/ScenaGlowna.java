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

public class ScenaGlowna {

    List<Telebook> listaNumerow = new LinkedList<>();
    private Stage stage;
    private Scene scene;
    private Parent root;

    @FXML
    private TextField imie;             //----> to
    @FXML
    private TextField numerTelefonu;
    @FXML
    private Label label1, label2;
    // List<Label> biezacaListaLabela;

    public void dodajDoListy(ActionEvent e){
        System.out.println("dodano " + imie.getText() + "  " + numerTelefonu.getText());
        listaNumerow.add(new Telebook(imie.getText(), numerTelefonu.getText()));
        //imie.clear();
        //numerTelefonu.clear();
        for(Telebook l: listaNumerow) {
            System.out.println(l.getImie());
        }
    }

    /*public void biezacaLista(ActionEvent e){     //NIE DZIAALA!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!

        String imieScena2 = imie.getText();

        FXMLLoader loader = new FXMLLoader(getClass().getResource("listaKontaktow.fxml"));
        root2Sceny = loader.load();


    }
*/
   /* public void nateraz(){
        label1.setText(listaNumerow.get(0).getImie());
        label2.setText(listaNumerow.get(1).getImie());
        //biezacaListaLabela...........
    }

    */


    public void przelaczDoListyKontakow(ActionEvent event) throws IOException {
        String imieScena2 = imie.getText();

        FXMLLoader loader = new FXMLLoader(getClass().getResource("listaKontaktow.fxml"));
        root = loader.load();

        ScenaListaKontaktow scene2Controller = loader.getController();
        scene2Controller.displayName(imieScena2);
        //root = FXMLLoader.load(getClass().getResource("Scene2.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();


        System.out.println("przelaczono");
        /*
        Parent root = FXMLLoader.load(getClass().getResource("listaKontaktow.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();

         */
    }

    public void zakoncz(){
        Platform.exit();}
}
