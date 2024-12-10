/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package it.unisa.diem.mycontacts.controller;

import it.unisa.diem.mycontacts.data.Contatto;
import it.unisa.diem.mycontacts.datastructure.Rubrica;
import java.io.IOException;
import java.net.URL;
import java.util.HashSet;
import java.util.ResourceBundle;
import java.util.Set;
import java.util.logging.Logger;
import javafx.collections.ObservableSet;
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
    private StackPane leftPane;
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
        
        /*Set <Integer>numeri = new HashSet<>();
        numeri.add(3);*/
        Set <String>email = new HashSet<>();
        email.add("prova@gmail.com");
        email.add("pro.34553.va@gmail.com");
        
        rubrica.aggiungiContatto(new Contatto("Mario", "Rossi",new HashSet<>(),new HashSet<>(),false));
        rubrica.aggiungiContatto(new Contatto("Gian", "Marco",new HashSet<>(),email,true));
        
        try {
            FXMLLoader view = new FXMLLoader(getClass().getResource("../view/leftView.fxml"));
            leftPane.getChildren().clear();
            leftPane.getChildren().add(view.load());
            
            LeftViewController leftPanelController = view.getController();
            leftPanelController.setMainViewController(this);
            
        } catch (IOException e) {
            Logger.getLogger(MainViewController.class.getName()).severe("Errore durante il caricamento di leftView: " + e.getMessage());
        }
    }    

    public Rubrica getRubrica() {
        return rubrica;
    }
    
    private void loadView(String fxmlFileName, Contatto contatto) {
        try {
            FXMLLoader view = new FXMLLoader(getClass().getResource("../view/" + fxmlFileName));
            VBox root = view.load();
            rightPane.getChildren().clear();
            rightPane.getChildren().add(root);

            if (fxmlFileName.equals("rightView1.fxml")) {
                RightView1Controller ctrl = view.getController();
                ctrl.setMainViewController(this);
                ctrl.setContatto(contatto);
            } else if (fxmlFileName.equals("rightView2.fxml")) {
                RightView2Controller ctrl = view.getController();
                ctrl.setMainViewController(this);
                ctrl.setContatto(contatto);
            } else throw new IOException("File non trovato");
            
        } catch (IOException e) {
            Logger.getLogger(MainViewController.class.getName()).severe("Errore durante il caricamento di " + fxmlFileName + ": " + e.getMessage());
        }
    }
    
    public void loadView1(Contatto contatto) {
        loadView("rightView1.fxml", contatto);
    }

    public void loadView2(Contatto contatto) {
        loadView("rightView2.fxml", contatto);
    }
    
}
