/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package it.unisa.diem.mycontacts.controller;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.CheckBox;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextField;

/**
 * FXML Controller class
 *
 * @author lupo
 */
public class RightView2Controller implements Initializable {

    @FXML
    private MenuItem stopButton;
    @FXML
    private MenuItem confirmButton;
    @FXML
    private TextField nomeField;
    @FXML
    private TextField cognomeField;
    @FXML
    private TextField numero1Field;
    @FXML
    private TextField email1Field;
    @FXML
    private TextField numero2Field;
    @FXML
    private TextField email2Field;
    @FXML
    private TextField numero3Field;
    @FXML
    private TextField email3Field;
    @FXML
    private CheckBox preferitoCheck;
    
    private MainViewController mainViewController;
    
    /**
     * Initializes the controller class.
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
    public void setMainViewController(MainViewController mainViewController) {
        this.mainViewController = mainViewController;
    }

    private void loadView1() {
        if (mainViewController != null) {
            mainViewController.loadView1();
        }else System.err.print("stampa dal left");
    }

    @FXML
    private void annullaAzione(ActionEvent event) {
        loadView1();
    }

    @FXML
    private void confermaAzione(ActionEvent event) {
        loadView1();
    }

    @FXML
    private void switchPreferito(ActionEvent event) {

        
    }
    
}