package sample;

import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class ScenaListaKontaktow {

    @FXML
    Label imieZKsiazki;

    public void displayName(String imieScena2){
        imieZKsiazki.setText(imieScena2);
    }
}
