package sample;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;

import java.util.LinkedList;
import java.util.List;

public class Main extends Application {

    List<Telebook> listaNumerow = new LinkedList<>();
    List<Telebook> listaZnalezionychNumerow = new LinkedList<>();
    Stage primaryStage;
    Label emptyLabel = new Label("");

    public GridPane dodajDoListy(GridPane siatka){
        //TYTUŁ
        Image zdjecieTelefonu = new Image(getClass().getResourceAsStream("telefon.jpg"),250,200,true,false);
        Label title = new Label("TELEBOOK", new ImageView(zdjecieTelefonu));
        title.setFont(Font.font(30));
        title.setTextFill(Color.ORANGERED);
        GridPane.setConstraints(title,1,1);
        siatka.getChildren().add(title);
        //DODAJ
        Label wpiszImie = new Label("Wpisz imie:");
        TextField imie = new TextField("wpisz imie");
        Label wpiszNr = new Label("Wpisz numer telefonu:");
        TextField telefon = new TextField("wpisz nr telefonu");
        Button dodaj = new Button("Dodaj");
        dodaj.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                System.out.println("dodano " + imie.getText() + " " + telefon.getText());
                listaNumerow.add(new Telebook(imie.getText(), telefon.getText()));
                imie.clear();
                telefon.clear();
               refreshScene();
            }
        });
        GridPane.setConstraints(wpiszImie,1,2);
        GridPane.setConstraints(imie,1,3);
        GridPane.setConstraints(wpiszNr,1,4);
        GridPane.setConstraints(telefon,1,5);
        GridPane.setConstraints(dodaj,1,6);
        siatka.getChildren().addAll(wpiszImie,imie,wpiszNr,telefon,dodaj);
        return siatka;
    }

    public GridPane biezacaLista(GridPane siatka){
        Button listaNumerowButton = new Button("Lista numerów");
        listaNumerowButton.setOnAction(e -> {
            //refreshScene();
            System.out.println("uzupelniono listę");
            int rowIndex = 1;
            for(Telebook l: listaNumerow) {
                Label listaLabela = new Label(rowIndex  + ". " + l.getImie() + " " + l.getNumer());
                siatka.getChildren().add(listaLabela);
                siatka.setConstraints(listaLabela, 2, rowIndex+2);
                rowIndex++;
            }

        });
        GridPane.setConstraints(listaNumerowButton,2,2);
        siatka.getChildren().add(listaNumerowButton);
        return siatka;
    }

    public GridPane usunZListy(GridPane siatka){
        Label usun = new Label("Który kontakt chcesz usunąć? ");
        TextField usunImie = new TextField("podaj imię ");
        Button usunButton = new Button("Usuń");
        usunButton.setOnAction(e -> {
            for(Telebook l: listaNumerow){
                if (usunImie.getText().equals(l.getImie())){
                    System.out.println("erased");
                    //listaNumerow.
                    listaNumerow.remove(l);    //TU SIE WYKRZACZA JAK SIE WPISZE W USUN PIERWSZE IMIE Z LISTY, JAK NASTEPNE TO OK
                    }
            }
            /*for(Telebook l: listaNumerow){
                System.out.println(l.getImie());
            }*/
            refreshScene();
        });
        GridPane.setConstraints(usun,1,8);
        GridPane.setConstraints(usunImie,1,9);
        GridPane.setConstraints(usunButton,1,10);
        siatka.getChildren().addAll(usun,usunImie,usunButton);
        return siatka;
    }

    public GridPane szukajKontakt(GridPane siatka){
        Label szukaj = new Label("Wpisz imię, którego szukasz ");
        TextField szukajImie = new TextField("podaj imię ");
        Button szukajButton = new Button("Szukaj");
        szukajButton.setOnAction(e -> {
            for (Telebook l : listaNumerow) {
                if (szukajImie.getText().equals(l.getImie())) {
                    System.out.println("found");
                    listaZnalezionychNumerow.add(l);
                }
           }
            refreshScene();
        });
        GridPane.setConstraints(szukaj,1,12);
        GridPane.setConstraints(szukajImie,1,13);
        GridPane.setConstraints(szukajButton,1,14);
        siatka.getChildren().addAll(szukaj,szukajImie,szukajButton);
        return siatka;
    }

    public GridPane listaZnalezionychKontaktow(GridPane siatka){
        System.out.println("uzupelniono szukane kontakty");
        int rowIndex = 1;
        for(Telebook l: listaZnalezionychNumerow) {
            Label listaLabela = new Label(rowIndex  + ". " + l.getImie() + " " + l.getNumer());
            siatka.getChildren().add(listaLabela);
            siatka.setConstraints(listaLabela, 3, rowIndex+2);
            rowIndex++;
        }
        return siatka;
    }

    public GridPane zakoncz(GridPane siatka){
        Image zdjecieTelefonuKoniec = new Image(getClass().getResourceAsStream("telefon.jpg"),50,40,true,false);
        Button zakoncz = new Button("Zakończ", new ImageView(zdjecieTelefonuKoniec));
        zakoncz.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                Platform.exit();
            }
        });
        GridPane.setConstraints(zakoncz,1,100);
        GridPane.setConstraints(emptyLabel,1,15);
        siatka.getChildren().addAll(emptyLabel,zakoncz);
        return siatka;
    }

    public Scene homeScene(){
        var siatka = new GridPane();
        siatka = dodajDoListy(siatka);
        siatka = biezacaLista(siatka);
        siatka = usunZListy(siatka);
        siatka = szukajKontakt(siatka);
        siatka = listaZnalezionychKontaktow(siatka);
        siatka = zakoncz(siatka);
        return new Scene(siatka, 600,600);    }

        public void refreshScene(){
        primaryStage.setScene(homeScene());
        }

    @Override
    public void start(Stage primaryStage) throws Exception{
        //Parent root = FXMLLoader.load(getClass().getResource("sample.fxml"));
        this.primaryStage = primaryStage;
        primaryStage.setTitle("Telebook");
        Image icon = new Image(getClass().getResourceAsStream("sluchawka.png"));
        primaryStage.getIcons().add(icon);
        primaryStage.setScene(homeScene());
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
