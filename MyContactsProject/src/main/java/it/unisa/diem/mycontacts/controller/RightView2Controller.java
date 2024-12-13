package it.unisa.diem.mycontacts.controller;

import it.unisa.diem.mycontacts.data.Contatto;
import it.unisa.diem.mycontacts.datastructure.Rubrica;
import java.net.URL;
import java.util.HashSet;
import java.util.ResourceBundle;
import java.util.Set;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.CheckBox;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;

/**
 * @class RightView2Controller
 * @brief Questa classe gestisce la visualizzazione e la modifica dei dettagli di un contatto.
 *        Permette di modificare i dati di un contatto esistente o di aggiungere un nuovo contatto,
 *        oltre a gestire lo stato "preferito" e la validazione dei campi.
 */
public class RightView2Controller implements Initializable {

    // Riferimenti agli elementi dell'interfaccia utente
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
    
    // Riferimenti alla vista principale e alla rubrica
    private MainViewController mainViewController;
    private Rubrica rubrica;
    private Contatto contatto;

    /**
     * @brief Inizializza la vista e rende il checkbox "preferito" abilitato.
     * 
     * @param url L'URL del file FXML.
     * @param rb Le risorse locali utilizzate nel file FXML.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        preferitoCheck.setDisable(false); // Abilita il checkbox per la selezione del contatto preferito
    }    
    
    /**
     * @brief Imposta il controller della vista principale e inizializza la rubrica.
     * 
     * @param mainViewController Il controller della vista principale.
     */
    public void setMainViewController(MainViewController mainViewController) {
        this.mainViewController = mainViewController;
        this.rubrica = mainViewController.getRubrica(); // Ottiene la rubrica dal MainViewController
    }

    /**
     * @brief Imposta il contatto da modificare, popolando i campi con i dati del contatto selezionato.
     * 
     * @param contatto Il contatto da visualizzare e modificare.
     */
    public void setContatto(Contatto contatto) {
        this.contatto = contatto; // Assegna il contatto corrente
        
        if (contatto != null) {
            // Popola i campi della vista con i dettagli del contatto
            nomeField.setText(contatto.getNome());
            cognomeField.setText(contatto.getCognome());
            preferitoCheck.setSelected(contatto.isPreferito()); // Imposta lo stato della checkbox preferito

            // Popola i campi dei numeri di telefono
            if (contatto.getNumeri().size() > 0) numero1Field.setText(contatto.getNumeri().toArray()[0].toString());
            if (contatto.getNumeri().size() > 1) numero2Field.setText(contatto.getNumeri().toArray()[1].toString());
            if (contatto.getNumeri().size() > 2) numero3Field.setText(contatto.getNumeri().toArray()[2].toString());

            // Popola i campi delle email
            if (contatto.getEmail().size() > 0) email1Field.setText(contatto.getEmail().toArray()[0].toString());
            if (contatto.getEmail().size() > 1) email2Field.setText(contatto.getEmail().toArray()[1].toString());
            if (contatto.getEmail().size() > 2) email3Field.setText(contatto.getEmail().toArray()[2].toString());
        }
    }

    /**
     * @brief Annulla l'azione corrente e ritorna alla vista precedente mostrando il contatto selezionato.
     * 
     * @param event L'evento scatenato dal clic sul bottone "Annulla".
     */
    @FXML
    private void annullaAzione(ActionEvent event) {
        if(contatto != null) {
            mainViewController.loadView1(contatto); // Torna alla vista precedente con il contatto selezionato
        }
    }

    /**
     * @brief Conferma la modifica o l'aggiunta del contatto, aggiornando la rubrica. Se il contatto è nuovo,
     *        lo aggiunge, altrimenti aggiorna il contatto esistente. Visualizza un messaggio di errore se necessario.
     * 
     * @param event L'evento scatenato dal clic sul bottone "Conferma".
     */
    @FXML
    private void confermaAzione(ActionEvent event) {
        // Crea un set di numeri di telefono, escludendo i campi vuoti
        Set <String> numeri = new HashSet<>();
        if(!numero1Field.getText().isEmpty()) numeri.add(numero1Field.getText());
        if(!numero2Field.getText().isEmpty()) numeri.add(numero2Field.getText());
        if(!numero3Field.getText().isEmpty()) numeri.add(numero3Field.getText());
        
        // Crea un set di email, escludendo i campi vuoti
        Set <String> email = new HashSet<>();
        if(!email1Field.getText().isEmpty()) email.add(email1Field.getText());
        if(!email2Field.getText().isEmpty()) email.add(email2Field.getText());
        if(!email3Field.getText().isEmpty()) email.add(email3Field.getText());
        
        // Crea il nuovo contatto
        Contatto c = new Contatto(nomeField.getText(), cognomeField.getText(), numeri, email, preferitoCheck.isSelected());
        
        // Aggiunge o aggiorna il contatto nella rubrica
        if(contatto == null){
            if (!rubrica.aggiungiContatto(c)) {
                showAlert("Errore nel caricamento", "Controlla che i campi nome o cognome siano presenti");
                return; // Interrompe l'operazione in caso di errore
            }
        } else if (rubrica.getElenco().contains(contatto)) {
            rubrica.rimuoviContatto(contatto); // Rimuove il contatto esistente
            if (!rubrica.aggiungiContatto(c)) {
                showAlert("Errore nel caricamento", "Controlla che i campi nome o cognome siano presenti");
            }
        }
        
        // Ritorna alla vista precedente con il contatto aggiornato
        mainViewController.loadView1(c);
    }
    
    /**
     * @brief Mostra un messaggio di errore in un'alert box.
     * 
     * @param title Il titolo della finestra di alert.
     * @param message Il messaggio da visualizzare nell'alert.
     */
    private void showAlert(String title, String message) {
        Alert alert = new Alert(AlertType.ERROR); // Crea un alert di tipo errore
        alert.setTitle(title); // Imposta il titolo dell'alert
        alert.setHeaderText(null); // Nessun header
        alert.setContentText(message); // Imposta il messaggio dell'alert
        alert.showAndWait(); // Mostra l'alert e aspetta che l'utente lo chiuda
    }

    /**
     * @brief Controlla l'input nel campo di testo dei numeri, permettendo solo numeri.
     * 
     * @param event L'evento scatenato dalla pressione di un tasto.
     */
    @FXML
    private void controlloNumero(KeyEvent event) {
        // Permette solo l'inserimento di numeri
        if(!event.getCharacter().matches("\\d"))
            event.consume(); // Annulla l'evento se il carattere non è un numero
    }
}
