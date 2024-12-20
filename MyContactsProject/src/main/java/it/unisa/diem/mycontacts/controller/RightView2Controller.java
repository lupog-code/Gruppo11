package it.unisa.diem.mycontacts.controller;

import it.unisa.diem.mycontacts.data.Contatto;
import it.unisa.diem.mycontacts.datastructure.Rubrica;
import it.unisa.diem.mycontacts.exceptions.InvalidContactException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
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
        if(contatto == null && rubrica.isRubricaVuota()) {
            showAlert("Errore","La rubrica è vuota comincia aggiungendo un contatto");
            return;
        }
        
        if(contatto == null) {
            List<Contatto> list = new ArrayList<>(rubrica.getElenco());
            mainViewController.loadView1(list.get(0)); // Carica il primo contatto
            return;
        }
        
        mainViewController.loadView1(contatto); // Torna alla vista precedente con il contatto selezionato
    }

    /**
     * @brief Conferma la modifica o l'aggiunta del contatto, aggiornando la rubrica. Se il contatto è nuovo,
     *        lo aggiunge, altrimenti aggiorna il contatto esistente. Visualizza un messaggio di errore se necessario.
     * 
     * @param event L'evento scatenato dal clic sul bottone "Conferma".
     */
    @FXML
    private void confermaAzione(ActionEvent event) throws InvalidContactException {
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
        Contatto c;
        try {
            c = new Contatto(nomeField.getText(), cognomeField.getText(), numeri, email, preferitoCheck.isSelected());
        } catch(InvalidContactException e) {
            showAlert("Errore nel caricamento", "Controlla che i campi nome o cognome siano presenti");
            return;
        }
        
        // Aggiunge o aggiorna il contatto nella rubrica
        if(contatto == null){
            rubrica.aggiungiContatto(c);  
        } else if (rubrica.getElenco().contains(contatto)) {
            rubrica.rimuoviContatto(contatto); // Rimuove il contatto esistente
            rubrica.aggiungiContatto(c);
        }
        
        LeftViewController.getListaContatti().setAll(rubrica.getElenco());
        
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
     * @brief Controlla l'input nel campo 3 di testo dei numeri, permettendo solo numeri.
     * Controlla la lunghezza del testo e impedisce l'inserimento oltre i 12 caratteri
     * 
     * @param event L'evento scatenato dalla pressione di un tasto.
     */
    @FXML
    private void controlloNumero1(KeyEvent event) {
        // Permette solo l'inserimento di numeri
        if(!event.getCharacter().matches("\\d"))
            event.consume(); // Annulla l'evento se il carattere non è un numero
        
        // Controlla la lunghezza del testo e impedisce l'inserimento oltre i 12 caratteri
        if (numero1Field.getCharacters().length() > 15) {
            event.consume(); // Annulla l'evento se uno dei campi ha raggiunto la lunghezza massima
        }
    }
    
    /**
     * @brief Controlla l'input nel campo 2 di testo dei numeri, permettendo solo numeri.
     * Controlla la lunghezza del testo e impedisce l'inserimento oltre i 12 caratteri
     * 
     * @param event L'evento scatenato dalla pressione di un tasto.
     */
    @FXML
    private void controlloNumero2(KeyEvent event) {
        // Permette solo l'inserimento di numeri
        if(!event.getCharacter().matches("\\d"))
            event.consume(); // Annulla l'evento se il carattere non è un numero
        
        // Controlla la lunghezza del testo e impedisce l'inserimento oltre i 12 caratteri
        if (numero2Field.getCharacters().length() > 15) {
            event.consume(); // Annulla l'evento se uno dei campi ha raggiunto la lunghezza massima
        }
    }
    
    /**
     * @brief Controlla l'input nel campo 1 di testo dei numeri, permettendo solo numeri.
     * Controlla la lunghezza del testo e impedisce l'inserimento oltre i 12 caratteri
     * 
     * @param event L'evento scatenato dalla pressione di un tasto.
     */
    @FXML
    private void controlloNumero3(KeyEvent event) {
        // Permette solo l'inserimento di numeri
        if(!event.getCharacter().matches("\\d"))
            event.consume(); // Annulla l'evento se il carattere non è un numero
        
        // Controlla la lunghezza del testo e impedisce l'inserimento oltre i 12 caratteri
        if (numero3Field.getCharacters().length() > 15) {
            event.consume(); // Annulla l'evento se uno dei campi ha raggiunto la lunghezza massima
        }
    }

    /**
     * @brief Controlla l'input nel campo nome, permettendo solo caratteri senza spazi.
     * 
     * @param event L'evento scatenato dalla pressione di un tasto.
     */
    @FXML
    private void controlloEmail(KeyEvent event) {
        if(event.getCharacter().matches(" "))
            event.consume(); // Annulla l'evento se il carattere non è un numero
    }
    
    /**
     * @brief @brief Controlla la lunghezza del nome e impedisce l'inserimento oltre i 20 caratteri
     * 
     * @param event L'evento scatenato dalla pressione di un tasto.
     */
    @FXML
    private void controlloNome(KeyEvent event) {
        if (nomeField.getCharacters().length() > 20) {
            event.consume(); 
        }
    }
    
    /**
     * @brief Controlla la lunghezza del cognome e impedisce l'inserimento oltre i 20 caratteri
     * 
     * @param event L'evento scatenato dalla pressione di un tasto.
     */
    @FXML
    private void controlloCognome(KeyEvent event) {
        if (cognomeField.getCharacters().length() > 20) {
            event.consume(); 
        }
    }
}
