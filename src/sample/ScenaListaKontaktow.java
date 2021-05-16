package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.LinkedList;

public class ScenaListaKontaktow {

    private Stage stage;
    private Scene scene;
    private Parent root;
    LinkedList<Telebook> listaNumerow2 = new LinkedList<>();

    @FXML
    GridPane siatka;

    public void pokaz(LinkedList<Telebook> lista) {
        this.listaNumerow2.addAll(lista);
        int rowIndex = 0;
        for (Telebook e : listaNumerow2) {
            Label listaLabela = new Label(rowIndex+1 + ". " + e.getImie() + " " + e.getNumer());
            siatka.getChildren().add(listaLabela);
            siatka.setConstraints(listaLabela, 0, rowIndex);
            rowIndex++;
        }
    }

    public void wroc(ActionEvent event) throws IOException {
        LinkedList<Telebook> username = listaNumerow2;
        FXMLLoader loader = new FXMLLoader(getClass().getResource("sample.fxml"));
        root = loader.load();
        ScenaGlowna scene1Controller = loader.getController();
        scene1Controller.pokaz2(username);
        //root = FXMLLoader.load(getClass().getResource("Scene2.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

}
