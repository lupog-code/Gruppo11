/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package it.unisa.diem.mycontacts.controller;
import it.unisa.diem.*;
import it.unisa.diem.mycontacts.data.Contatto;
import it.unisa.diem.mycontacts.datastructure.Rubrica;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.Set;
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
    
    private Contatto contattoSelezionato = null;
    private Rubrica rubrica;
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

    public void setContatto(Contatto contatto) {
    this.contattoSelezionato = contatto;

    if (contatto != null) {
        // Assegna direttamente i valori ai campi della GUI
        nomeLabel.setText(contatto.getNome());
        cognomeLabel.setText(contatto.getCognome());
        
        // Gestione dei numeri, iterando sul Set di numeri
        Set<Integer> numeriSet = contatto.getNumeri();
        int index = 0;
        for (Integer numero : numeriSet) {
            // Assegna i numeri ai rispettivi campi
            if (index == 0) {
                numero1Label.setText(numero.toString());
            } else if (index == 1) {
                numero2Label.setText(numero.toString());
            } else if (index == 2) {
                numero3Label.setText(numero.toString());
            }
            index++;
        }
        
        // Gestione delle email, iterando sul Set di email
        Set<String> emails = contatto.getEmail();
        index = 0;
        for (String email : emails) {
            // Assegna le email ai rispettivi campi
            if (index == 0) {
                email1Label.setText(email);
            } else if (index == 1) {
                email2Label.setText(email);
            } else if (index == 2) {
                email3Label.setText(email);
            }
            index++;
        }
        preferitoCheck.setSelected(contatto.isPreferito());
    }
}
    
    @FXML
    private void modificaContatto(ActionEvent event) {
        loadView2();
    }

    @FXML
    private void eliminaContatto(ActionEvent event) {
        if (contattoSelezionato != null && rubrica != null) {
            boolean successo = rubrica.rimuoviContatto(contattoSelezionato);
            if (successo) {
                // Pulisci i campi dell'interfaccia
                nomeLabel.setText("");
                cognomeLabel.setText("");
                numero1Label.setText("");
                numero2Label.setText("");
                numero3Label.setText("");
                email1Label.setText("");
                email2Label.setText("");
                email3Label.setText("");
                preferitoCheck.setSelected(false);

                // Ricarica o aggiorna la visualizzazione dei contatti
                // Se hai una TableView, chiamerai refresh o aggiornerai la lista
            } else {
                System.out.println("Errore nella rimozione del contatto.");
            }
        }
    }
    

    @FXML
    private void switchPreferito(ActionEvent event) {
       //inverte lo staso di preferito di un contatto ( da false a true e viceversa) 
         // Verifica se un contatto è stato selezionato
    if (contattoSelezionato != null) {
        // Inverte lo stato del contatto (da preferito a non preferito e viceversa)
        boolean nuovoStato = !contattoSelezionato.isPreferito();
        contattoSelezionato.setPreferito(nuovoStato);
        
        // Aggiorna l'interfaccia utente per riflettere il nuovo stato di preferito
        preferitoCheck.setSelected(nuovoStato);

        // Se necessario, aggiorna la rubrica (aggiungi o rimuovi il contatto dai preferiti)
        if (rubrica != null) {
            if (nuovoStato) {
                rubrica.aggiungiAPreferiti(contattoSelezionato);
            } else {
                rubrica.rimuoviDaPreferiti(contattoSelezionato);
            }
        }
    }
    }
    
}
