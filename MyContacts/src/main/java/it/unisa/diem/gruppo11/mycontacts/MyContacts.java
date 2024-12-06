package it.unisa.diem.gruppo11.mycontacts;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import javafx.scene.control.SplitPane;
import javafx.scene.layout.StackPane;

/**
 * JavaFX MyContacts
 */
public class MyContacts extends Application {

    private static Scene scene;

    @Override
    public void start(Stage stage) throws Exception {
        
        scene = new Scene(loadFXML("mainView"), 1000, 800);
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
    }

}