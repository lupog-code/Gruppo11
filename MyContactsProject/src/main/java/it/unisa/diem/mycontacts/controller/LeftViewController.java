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
import javafx.collections.SetChangeListener;
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
    
    private ObservableList<Contatto> listaContatti;  
    
    private MainViewController mainViewController;
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        nomeColumn.setCellValueFactory(c -> { return new SimpleStringProperty(c.getValue().getNome()); });
        cognomeColumn.setCellValueFactory(c -> { return new SimpleStringProperty(c.getValue().getCognome()); });
        
        listaContatti = FXCollections.observableArrayList();
        contattiTable.setItems(listaContatti);
        
        contattiTable.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue != null) {
                // Passa il contatto selezionato al MainViewController per caricare la RightView2
                mainViewController.loadView1(newValue);
            }
        });
    }    

    public void setMainViewController(MainViewController mainViewController) {
        this.mainViewController = mainViewController;
        
        listaContatti = FXCollections.observableArrayList(mainViewController.getRubrica().getElenco());
        contattiTable.setItems(listaContatti); // Collega la lista osservabile alla TableView
        
        ObservableSet<Contatto> rubricaSet = mainViewController.getRubrica().getElenco();
        rubricaSet.addListener((SetChangeListener<Contatto>) change -> {
                if (change.wasAdded()) {
                    listaContatti.add(change.getElementAdded());
                }
                if (change.wasRemoved()) {
                    listaContatti.remove(change.getElementRemoved());
                }
        });    
        
    }

    @FXML
    private void aggiungiContatto(ActionEvent event) {
        mainViewController.loadView2(null);
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
