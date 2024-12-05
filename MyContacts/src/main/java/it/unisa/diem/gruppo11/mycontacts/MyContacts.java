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
        FXMLLoader loader = new FXMLLoader(MyContacts.class.getResource("LeftView.fxml"));      
        SplitPane root = loader.load();
        LeftViewController lvc = loader.getController();
        
        StackPane rightPane = new StackPane();
        
        loadRightView("RightView1.fxml", rightPane);
        
        lvc.setOnViewChangeRequested(viewName -> {
            try {
                loadRightView(viewName, rightPane);
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
        
        root.getItems().add(rightPane);
        root.setDividerPositions(0.3);
        
        scene = new Scene(loadFXML("LeftView"), 1000, 750);
        stage.setTitle("MyContacts");
        stage.setScene(scene);
        stage.show();
    }

    static void setRoot(String fxml) throws IOException {
        scene.setRoot(loadFXML(fxml));
    }

    private static Parent loadFXML(String fxml) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(MyContacts.class.getResource(fxml + ".fxml"));
        return fxmlLoader.load();
    }
    
    private void loadRightView(String fxmlName, StackPane rightPane) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource(fxmlName));
        StackPane view = fxmlLoader.load();
        rightPane.getChildren().setAll(view);
    }
    
    public static void main(String[] args) {
        launch();
    }

}