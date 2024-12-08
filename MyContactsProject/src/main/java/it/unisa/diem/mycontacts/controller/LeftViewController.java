/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package it.unisa.diem.mycontacts.controller;

import it.unisa.diem.mycontacts.data.Contatto;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.ObservableSet;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleButton;
import javafx.scene.control.cell.PropertyValueFactory;
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
    
    private MainViewController mainViewController;
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        nomeColumn.setCellValueFactory(new PropertyValueFactory("nome"));
        cognomeColumn.setCellValueFactory(new PropertyValueFactory("cognome"));
        
        ObservableList<Contatto> contattiList = FXCollections.observableArrayList(mainViewController.getRubrica().getElenco());

        contattiTable.setItems(contattiList);
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

        // dobbiamo decidere come sviluppare la Gui
    }

    @FXML
    private void esportaRubrica(ActionEvent event) {
        // dobbiamo decidere come sviluppare la Gui
    }

    @FXML
    private void ricercaContatto(KeyEvent event) {
        String ricerca = searchField.getText(); // Contiene il testo da cercare
        
        if(!ricerca.isEmpty()) {
            ObservableList<Contatto> ricercati = FXCollections.observableArrayList(mainViewController.getRubrica().ricercaContatti(ricerca)); //creo il set

            contattiTable.setItems(ricercati);
        }
        
    }

    @FXML
    private void mostraPreferiti(ActionEvent event) {
        ObservableList<Contatto> preferiti = FXCollections.observableArrayList(mainViewController.getRubrica().getElencoPreferiti()); //creo il set

        contattiTable.setItems(preferiti);
    }
    
}
