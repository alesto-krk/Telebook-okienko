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

    public GridPane tytul(GridPane siatka){
        Image zdjecieTelefonu = new Image(getClass().getResourceAsStream("telefon.jpg"),250,200,true,false);
        Label title = new Label("TELEBOOK", new ImageView(zdjecieTelefonu));
        title.setFont(Font.font(30));
        title.setTextFill(Color.ORANGERED);
        Label emptyTitleLabel = new Label("");
        GridPane.setConstraints(title,1,1);
        GridPane.setConstraints(emptyTitleLabel,1,2);
        siatka.getChildren().addAll(title,emptyTitleLabel);
        return siatka;
    }

    public GridPane dodajDoListy(GridPane siatka){
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
        GridPane.setConstraints(wpiszImie,1,10);
        GridPane.setConstraints(imie,1,11);
        GridPane.setConstraints(wpiszNr,1,12);
        GridPane.setConstraints(telefon,1,13);
        GridPane.setConstraints(dodaj,1,14);
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
            refreshScene();
        });
        GridPane.setConstraints(usun,1,20);
        GridPane.setConstraints(usunImie,1,21);
        GridPane.setConstraints(usunButton,1,22);
        siatka.getChildren().addAll(usun,usunImie,usunButton);
        return siatka;
    }

    public GridPane szukajKontakt(GridPane siatka){
        Label szukaj = new Label("Wpisz imię, którego szukasz lub jego fragment");
        TextField szukajImie = new TextField("podaj imię ");
        Image zdjecieLupy = new Image(getClass().getResourceAsStream("lupa.jpg"),40,40 ,true,false);
        Button szukajButton = new Button("Szukaj", new ImageView(zdjecieLupy));
        szukajButton.setOnAction(e -> {
            listaZnalezionychNumerow.clear();
            for (Telebook l : listaNumerow) {
                if (l.getImie().toLowerCase().contains(szukajImie.getText().toLowerCase())) {
                    System.out.println("found");
                    listaZnalezionychNumerow.add(l);
                }
           }
            refreshScene();
        });

        GridPane.setConstraints(szukaj,1,30);
        GridPane.setConstraints(szukajImie,1,31);
        GridPane.setConstraints(szukajButton,1,32);
        siatka.getChildren().addAll(szukaj,szukajImie,szukajButton);
        return siatka;
    }

    public GridPane listaZnalezionychKontaktow(GridPane siatka){
        Label znaleziono = new Label("Znaleziono:");
        znaleziono.setTextFill(Color.PERU);
        siatka.getChildren().add(znaleziono);
        siatka.setConstraints(znaleziono, 3, 2);
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
        Label emptyFinishLabel = new Label("");
        GridPane.setConstraints(zakoncz,1,100);
        GridPane.setConstraints(emptyFinishLabel,1,90);
        siatka.getChildren().addAll(emptyFinishLabel,zakoncz);
        return siatka;
    }

    public Scene homeScene(){
        var siatka = new GridPane();
        siatka = tytul(siatka);
        siatka = dodajDoListy(siatka);
        siatka = biezacaLista(siatka);
        siatka = usunZListy(siatka);
        siatka = szukajKontakt(siatka);
        siatka = listaZnalezionychKontaktow(siatka);
        siatka = zakoncz(siatka);
        return new Scene(siatka, 600,600, Color.LIGHTGOLDENRODYELLOW);    }

        public void refreshScene(){
        primaryStage.setScene(homeScene());
        }


    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("sample.fxml"));
        this.primaryStage = primaryStage;
        primaryStage.setTitle("Telebook");
        Image icon = new Image(getClass().getResourceAsStream("sluchawka.png"));
        primaryStage.getIcons().add(icon);
        //primaryStage.setScene(homeScene());
        primaryStage.setScene(new Scene(root, 700,500));
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
        System.out.println("java version: "+System.getProperty("java.version"));
        System.out.println("javafx.version: " + System.getProperty("javafx.version"));
    }
}
