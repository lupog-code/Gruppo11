/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package it.unisa.diem.mycontacts.controller;

import it.unisa.diem.mycontacts.data.Contatto;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.ObservableList;
import javafx.collections.ObservableSet;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleButton;
import javafx.scene.input.KeyEvent;

/**
 * FXML Controller class
 *
 * @author lupo
 */
public class LeftViewController implements Initializable {

    @FXML
    private MenuItem addButton;
    @FXML
    private MenuItem importButton;
    @FXML
    private MenuItem exportButton;
    @FXML
    private TextField searchField;
    @FXML
    private ToggleButton prefToggle;
    @FXML
    private TableView<Contatto> contattiTable;
    @FXML
    private TableColumn<Contatto, String> nomeColumn;
    @FXML
    private TableColumn<Contatto, String> cognomeColumn;
    
    private ObservableSet<Contatto> contatti;
    
    private MainViewController mainViewController;
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
    }    

    public void setMainViewController(MainViewController mainViewController) {
        this.mainViewController = mainViewController;
    }
    
    private void loadView2() {
        if (mainViewController != null) {
            mainViewController.loadView2();
        }else System.err.print("stampa dal left");
    }

    @FXML
    private void aggiungiContatto(ActionEvent event) {
        loadView2();
    }

    @FXML
    private void importaRubrica(ActionEvent event) {
    }

    @FXML
    private void esportaRubrica(ActionEvent event) {
    }

    @FXML
    private void ricercaContatto(KeyEvent event) {
    }

    @FXML
    private void mostraPreferiti(ActionEvent event) {
    }
    
}
