/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package it.unisa.diem.mycontacts.controller;

import it.unisa.diem.mycontacts.data.Contatto;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuItem;

/**
 * FXML Controller class
 *
 * @author lupo
 */
public class RightView1Controller implements Initializable {

    @FXML
    private MenuItem modifyButton;
    @FXML
    private MenuItem deleteButton;
    @FXML
    private Label nomeLabel;
    @FXML
    private Label cognomeLabel;
    @FXML
    private Label numero1Label;
    @FXML
    private Label email1Label;
    @FXML
    private Label numero2Label;
    @FXML
    private Label email2Label;
    @FXML
    private Label numero3Label;
    @FXML
    private Label email3Label;
    @FXML
    private CheckBox preferitoCheck;
    
    private MainViewController mainViewController;
    
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
    public void setMainViewController(MainViewController mainViewController) {
        this.mainViewController = mainViewController;
    }
    
    public void setContatto(Contatto contatto) {
        if (contatto != null) {
            nomeLabel.setText(contatto.getNome());
            cognomeLabel.setText(contatto.getCognome());
            preferitoCheck.setSelected(contatto.isPreferito());

            // Popola i campi dei numeri di telefono e delle email
            if (contatto.getNumeri().size() > 0) numero1Label.setText(contatto.getNumeri().toArray()[0].toString());
            if (contatto.getNumeri().size() > 1) numero2Label.setText(contatto.getNumeri().toArray()[1].toString());
            if (contatto.getNumeri().size() > 2) numero3Label.setText(contatto.getNumeri().toArray()[2].toString());

            if (contatto.getEmail().size() > 0) email1Label.setText(contatto.getEmail().toArray()[0].toString());
            if (contatto.getEmail().size() > 1) email2Label.setText(contatto.getEmail().toArray()[1].toString());
            if (contatto.getEmail().size() > 2) email3Label.setText(contatto.getEmail().toArray()[2].toString());
        }
    }

    private void loadView2() {
        if (mainViewController != null) {
            mainViewController.loadView2();
        }else System.err.print("stampa dal left");
    }

    @FXML
    private void modificaContatto(ActionEvent event) {
        loadView2();
    }

    @FXML
    private void eliminaContatto(ActionEvent event) {
    }

    @FXML
    private void switchPreferito(ActionEvent event) {
    }
    
}
