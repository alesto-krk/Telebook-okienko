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

    @FXML
    GridPane siatka;

    public void pokaz(LinkedList<Telebook> lista) {
        int rowIndex = 0;
        for (Telebook e : lista) {
            Label listaLabela = new Label(rowIndex+1 + ". " + e.getImie() + " " + e.getNumer());
            siatka.getChildren().add(listaLabela);
            siatka.setConstraints(listaLabela, 0, rowIndex);
            rowIndex++;
        }
    }

    public void wroc(ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("sample.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

}
