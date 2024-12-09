package it.unisa.diem.mycontacts.app;

import it.unisa.diem.mycontacts.datastructure.Rubrica;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * JavaFX MyContacts
 */
public class MyContacts extends Application {

    private static Scene scene;

    @Override
    public void start(Stage stage) throws IOException {
        scene = new Scene(loadFXML("../view/mainView"), 1000, 800);
        stage.setScene(scene);
        stage.setTitle("MyContacts");
        stage.show();
       
      }

    private static Parent loadFXML(String fxml) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(MyContacts.class.getResource(fxml + ".fxml"));
        return fxmlLoader.load();
    }

    public static void main(String[] args) {
        launch();
         Rubrica rubrica= new Rubrica();
    }

}