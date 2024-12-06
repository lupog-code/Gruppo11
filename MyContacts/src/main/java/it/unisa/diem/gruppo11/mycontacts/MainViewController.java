/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package it.unisa.diem.gruppo11.mycontacts;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;

/**
 * FXML Controller class
 *
 * @author lupo
 */
public class MainViewController implements Initializable {

    @FXML
    private AnchorPane leftPane;
    @FXML
    private StackPane rightPane;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("leftView.fxml"));
            leftPane.getChildren().clear();
            leftPane.getChildren().add(loader.load());
            
            LeftViewController leftViewController = loader.getController();
            leftViewController.setMainViewController(this);
            
        } catch (IOException e) {
            e.printStackTrace();
        }
    }    
    
    public void loadView1() {
        try {
            FXMLLoader loader =  new FXMLLoader(getClass().getResource("rightView1.fxml"));
            VBox root = loader.load();
            rightPane.getChildren().clear();
            rightPane.getChildren().add(root);
            RightView1Controller ctrl = loader.getController();
            ctrl.setMainViewController(this);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void loadView2() {
        try {
            FXMLLoader loader =  new FXMLLoader(getClass().getResource("rightView2.fxml"));
            VBox root = loader.load();
            rightPane.getChildren().clear();
            rightPane.getChildren().add(root);
            RightView2Controller ctrl = loader.getController();
            ctrl.setMainViewController(this);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
}
