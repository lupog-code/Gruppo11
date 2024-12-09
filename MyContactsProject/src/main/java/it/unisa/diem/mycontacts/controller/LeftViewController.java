/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package it.unisa.diem.mycontacts.controller;

import java.net.URL;
import java.util.ResourceBundle;

import it.unisa.diem.mycontacts.data.Contatto;
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
        contatti=FXCollections.observableSet();
        
        

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
            String ricerca = searchField.getText().toLowerCase(); // Contiene il testo da cercare
        ObservableSet<Contatto> ricercati = FXCollections.observableSet(); //creo il set
    
        for (Contatto contatto : contatti) {
            // Concatenazione di nome, cognome, numeri e email in modo da fare solamente un .contains per ogni contatto
            StringBuilder temporanea = new StringBuilder();
            temporanea.append(contatto.getNome().toLowerCase()).append(" ")
                      .append(contatto.getCognome().toLowerCase()).append(" ");
            
            // Concatenazione dei numeri e delle email in modo appropriato
            for (int numero : contatto.getNumeri()) {
                temporanea.append(numero).append(" ");
            }
            
            for (String email : contatto.getEmail()) {
                temporanea.append(email.toLowerCase()).append(" ");
            }
    
            // Controlla se la stringa concatenata contiene la ricerca e in caso lo aggiungiamo alla lista dei contatti contenenti la parola ricercata 
            if (temporanea.toString().contains(ricerca)) {
                ricercati.add(contatto);
            }
        }
    }

    @FXML
    private void mostraPreferiti(ActionEvent event) {
        ObservableSet<Contatto> preferiti = FXCollections.observableSet();

        // Effettuo ricerca per verificare fra i contatti presenti se sono contrassegnati come preferiti quindi uso il metodo isPreferito()
        for (Contatto contatto : contatti) {
            if (contatto.isPreferito()) { 
                preferiti.add(contatto);
            }
        }
    
        ObservableList<Contatto> preferitiList = FXCollections.observableArrayList(preferiti);
    contattiTable.setItems(preferitiList);
    }
    
}
