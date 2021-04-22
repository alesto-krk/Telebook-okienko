package sample;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;

import java.util.LinkedList;

public class ScenaListaKontaktow {

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


}
