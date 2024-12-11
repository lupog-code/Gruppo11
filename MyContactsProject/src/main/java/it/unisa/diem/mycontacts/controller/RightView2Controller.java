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
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;

/**
 * @class RightView2Controller
 * @brief Controller per la visualizzazione e modifica dei dettagli di un contatto nella vista RightView2.
 * Gestisce la visualizzazione dei dettagli del contatto e l'aggiornamento dei dati.
 * Permette di aggiungere o modificare un contatto e gestirne lo stato "preferito".
 * 
 * @author Gruppo11
 */
public class RightView2Controller implements Initializable {

    @FXML
    private MenuItem stopButton;  /**< Bottone per annullare l'operazione */
    @FXML
    private MenuItem confirmButton; /**< Bottone per confermare le modifiche */
    @FXML
    private TextField nomeField; /**< Campo di testo per il nome del contatto */
    @FXML
    private TextField cognomeField; /**< Campo di testo per il cognome del contatto */
    @FXML
    private TextField numero1Field; /**< Campo di testo per il primo numero di telefono */
    @FXML
    private TextField email1Field; /**< Campo di testo per la prima email */
    @FXML
    private TextField numero2Field; /**< Campo di testo per il secondo numero di telefono */
    @FXML
    private TextField email2Field; /**< Campo di testo per la seconda email */
    @FXML
    private TextField numero3Field; /**< Campo di testo per il terzo numero di telefono */
    @FXML
    private TextField email3Field; /**< Campo di testo per la terza email */
    @FXML
    private CheckBox preferitoCheck; /**< CheckBox per segnare il contatto come preferito */
    
    private MainViewController mainViewController; /**< Controller della vista principale */
    
    private Rubrica rubrica; /**< La rubrica che gestisce i contatti */
    
    private Contatto contatto; /**< Il contatto corrente da modificare o visualizzare */
    
    /**
     * @brief Inizializza il controller della vista.
     * Configura gli elementi dell'interfaccia utente, come la disabilitazione della checkbox preferito.
     * 
     * @param url URL per la localizzazione
     * @param rb Risorse di localizzazione
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        preferitoCheck.setDisable(false);
    }    
    
    /**
     * @brief Imposta il controller principale della vista.
     * Assegna la rubrica gestita dal controller principale.
     * 
     * @param mainViewController il controller della vista principale.
     */
    public void setMainViewController(MainViewController mainViewController) {
        this.mainViewController = mainViewController;
        
        this.rubrica = mainViewController.getRubrica();
    }
    
    /**
     * @brief Imposta il contatto da visualizzare o modificare.
     * Popola i campi con i dati del contatto selezionato.
     * 
     * @param contatto Il contatto da visualizzare o modificare.
     */
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

    /**
     * @brief Annulla l'operazione e torna alla vista principale con il contatto precedente.
     * Se il contatto è stato modificato, lo salva e aggiorna la vista.
     * 
     * @param event L'evento di clic del bottone "annulla".
     */
    @FXML
    private void annullaAzione(ActionEvent event) {
        if(contatto != null) {
            mainViewController.loadView1(contatto); // Gestire il ritorno alla vista principale
        }
    }

    /**
     * @brief Conferma l'operazione di modifica del contatto.
     * Crea un nuovo oggetto `Contatto` con i dati inseriti e aggiorna la rubrica.
     * Se il contatto esiste già, lo rimuove dalla rubrica e lo sostituisce con i nuovi dati.
     * 
     * @param event L'evento di clic del bottone "conferma".
     */
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
        
        if(contatto != null)
            rubrica.rimuoviContatto(contatto);

            
        contatto = new Contatto(nomeField.getText(), cognomeField.getText(), numeri, email, preferitoCheck.isSelected());
        
        if (!rubrica.aggiungiContatto(contatto)) showAlert("Errore nel caricamento", "Controlla che i campi nome o cognome siano presenti");
        
        mainViewController.loadView1(contatto);
    }

    /**
     * @brief Cambia lo stato "preferito" del contatto.
     * Inverte lo stato di preferito del contatto e aggiorna la rubrica e l'interfaccia.
     * 
     * @param event L'evento di clic del bottone "preferito".
     */
    @FXML
    private void switchPreferito(ActionEvent event) {
       // Inverte lo stato di preferito di un contatto (da false a true e viceversa)
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
    
    /**
     * @brief Mostra un messaggio di errore in un alert.
     * Visualizza un messaggio di errore con il titolo e il testo specificati.
     * 
     * @param title Il titolo dell'alert.
     * @param message Il messaggio da visualizzare nell'alert.
     */
    private void showAlert(String title, String message) {
        Alert alert = new Alert(AlertType.ERROR);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

    /**
     * @brief Controlla che l'input nel campo numero sia numerico.
     * Intercetta l'evento di inserimento carattere per garantire che solo numeri siano inseriti.
     * 
     * @param event L'evento di inserimento di un carattere.
     */
    @FXML
    private void controlloNumero(KeyEvent event) {
        if(!event.getCharacter().matches("\\d"))
            event.consume();
    }
}
