/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package it.unisa.diem.mycontacts.controller;

import it.unisa.diem.mycontacts.datastructure.Rubrica;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Logger;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
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
    
    private Rubrica rubrica;
    
    /**
     * Initializes the controller class.
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        rubrica = new Rubrica();
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("../view/leftView.fxml"));
            leftPane.getChildren().clear();
            leftPane.getChildren().add(loader.load());
            
            LeftViewController leftPanelController = loader.getController();
            leftPanelController.setMainViewController(this);
            
        } catch (IOException e) {
            Logger.getLogger(MainViewController.class.getName()).severe("Errore durante il caricamento di leftView: " + e.getMessage());
        }
    }    
    
    private void loadView(String fxmlFileName) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("../view/" + fxmlFileName));
            VBox root = loader.load();
            rightPane.getChildren().clear();
            rightPane.getChildren().add(root);

            if (fxmlFileName.equals("rightView1.fxml")) {
                RightView1Controller ctrl = loader.getController();
                ctrl.setMainViewController(this);
            } else if (fxmlFileName.equals("rightView2.fxml")) {
                RightView2Controller ctrl = loader.getController();
                ctrl.setMainViewController(this);
            } else throw new IOException("File non trovato");
            
        } catch (IOException e) {
            Logger.getLogger(MainViewController.class.getName()).severe("Errore durante il caricamento di " + fxmlFileName + ": " + e.getMessage());
        }
    }

    public Rubrica getRubrica() {
        return rubrica;
    }

    public void loadView1() {
        loadView("rightView1.fxml");
    }

    public void loadView2() {
        loadView("rightView2.fxml");
    }
    
}
