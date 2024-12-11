/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package it.unisa.diem.mycontacts.controller;



import it.unisa.diem.mycontacts.data.Contatto;
import it.unisa.diem.mycontacts.datastructure.Rubrica;
import java.net.URL;
import java.util.HashSet;
import java.util.ResourceBundle;
import java.util.Set;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;

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
    
    private Rubrica rubrica;
    
    private Contatto contatto;
    
    /**
     * Initializes the controller class.
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        preferitoCheck.setDisable(false);
    }    
    
    public void setMainViewController(MainViewController mainViewController) {
        this.mainViewController = mainViewController;
        
        this.rubrica = mainViewController.getRubrica();
    }
    
    public void setContatto(Contatto contatto) {
        this.contatto = contatto;
        
        if (contatto != null) {
            nomeField.setText(contatto.getNome());
            cognomeField.setText(contatto.getCognome());
            preferitoCheck.setSelected(contatto.isPreferito());

            // Popola i campi dei numeri di telefono e delle email
            if (contatto.getNumeri().size() > 0) numero1Field.setText(contatto.getNumeri().toArray()[0].toString());
            if (contatto.getNumeri().size() > 1) numero2Field.setText(contatto.getNumeri().toArray()[1].toString());
            if (contatto.getNumeri().size() > 2) numero3Field.setText(contatto.getNumeri().toArray()[2].toString());

            if (contatto.getEmail().size() > 0) email1Field.setText(contatto.getEmail().toArray()[0].toString());
            if (contatto.getEmail().size() > 1) email2Field.setText(contatto.getEmail().toArray()[1].toString());
            if (contatto.getEmail().size() > 2) email3Field.setText(contatto.getEmail().toArray()[2].toString());
        }
    }

    @FXML
    private void annullaAzione(ActionEvent event) {
        if(contatto != null) {
            mainViewController.loadView1(contatto);//gestire
        }
    }

    @FXML
    private void confermaAzione(ActionEvent event) {
        
        Set <String> numeri = new HashSet<>();
        if(!numero1Field.getText().isEmpty()) numeri.add(numero1Field.getText());
        if(!numero2Field.getText().isEmpty()) numeri.add(numero2Field.getText());
        if(!numero3Field.getText().isEmpty()) numeri.add(numero3Field.getText());
        
        Set <String> email = new HashSet<>();
        if(!email1Field.getText().isEmpty()) email.add(email1Field.getText());
        if(!email2Field.getText().isEmpty()) email.add(email2Field.getText());
        if(!email3Field.getText().isEmpty()) email.add(email3Field.getText());
        
        Contatto c = new Contatto(nomeField.getText(), cognomeField.getText(), numeri, email, preferitoCheck.isSelected());
        
        // Controllo se i campi nome e cognome sono validi
        if (c.getNome().isEmpty() && c.getCognome().isEmpty()) {
            showAlert("Errore nel caricamento", "Controlla che i campi nome o cognome siano presenti");
            return;
        }

        // Rimuovi il vecchio contatto se esiste (caso modifica)
        if (contatto != null && rubrica.getElenco().contains(contatto)) {
            rubrica.rimuoviContatto(contatto);
        }

        // Aggiungi il nuovo contatto alla rubrica
        if (!rubrica.aggiungiContatto(c)) {
            showAlert("Errore", "Esiste già un contatto con gli stessi dati.");
            return;
        }

        /*if(!rubrica.aggiungiContatto(c)) {
            showAlert("Errore nel caricamento", "Controlla che i campi nome o cognome siano presenti");
        } else {
            if(contatto != null && !contatto.equals(c))
            rubrica.rimuoviContatto(contatto);
        }*/
        
        mainViewController.loadView1(c);
    }

    @FXML
    private void switchPreferito(ActionEvent event) {
       //inverte lo staso di preferito di un contatto ( da false a true e viceversa) 
         // Verifica se un contatto è stato selezionato
        if (contatto != null) {
            // Inverte lo stato del contatto (da preferito a non preferito e viceversa)
            contatto.switchPreferiti();

            // Aggiorna l'interfaccia utente per riflettere il nuovo stato di preferito
            preferitoCheck.setSelected(contatto.isPreferito());

            // Se necessario, aggiorna la rubrica (aggiungi o rimuovi il contatto dai preferiti)
            if (rubrica != null) {
                if (contatto.isPreferito()) {
                    rubrica.aggiungiAPreferiti(contatto);
                } else {
                    rubrica.rimuoviDaPreferiti(contatto);
                }
            }
        }
    }
    
    private void showAlert(String title, String message) {
        Alert alert = new Alert(AlertType.ERROR);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

    @FXML
    private void controlloNumero(KeyEvent event) {
        if(!event.getCharacter().matches("\\d"))
            event.consume();
    }
    
}
