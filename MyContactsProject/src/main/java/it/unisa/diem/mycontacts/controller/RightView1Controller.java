package it.unisa.diem.mycontacts.controller;

import it.unisa.diem.mycontacts.data.Contatto;
import it.unisa.diem.mycontacts.datastructure.Rubrica;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.MenuItem;

/**
 * @class RightView1Controller
 * @brief Questa classe funge da controller per la vista a destra dell'applicazione, gestendo
 *        la visualizzazione e la gestione dei dettagli di un contatto selezionato. Permette
 *        di visualizzare, modificare ed eliminare un contatto dalla rubrica.
 */
public class RightView1Controller implements Initializable {

    // Riferimenti agli elementi dell'interfaccia utente
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

    // Oggetti per gestire la rubrica e il contatto selezionato
    private MainViewController mainViewController;
    private Rubrica rubrica;
    private Contatto contatto;

    /**
     * @brief Metodo di inizializzazione del controller. Inizializza la checkbox preferito come disabilitata
     *        e configura la rubrica, ma non carica ancora alcun contatto.
     * 
     * @param url L'URL del file FXML.
     * @param rb Le risorse locali utilizzate nel file FXML.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        preferitoCheck.setDisable(true); // Disabilita il checkbox per la selezione di "preferito"
    }

    /**
     * @brief Imposta il controller della vista principale e inizializza la rubrica.
     * 
     * @param mainViewController Il controller della vista principale.
     */
    public void setMainViewController(MainViewController mainViewController) {
        this.mainViewController = mainViewController;
        rubrica = mainViewController.getRubrica(); // Ottiene la rubrica dal MainViewController
    }

    /**
     * @brief Imposta un contatto da visualizzare. Popola i campi della vista con i dati del contatto.
     *        Se il contatto ha più numeri o email, popola i rispettivi campi.
     * 
     * @param contatto Il contatto da visualizzare.
     */
    public void setContatto(Contatto contatto) {
        this.contatto = contatto; // Assegna il contatto corrente
        
        if (contatto != null) {
            // Popola i campi della vista con i dettagli del contatto
            nomeLabel.setText(contatto.getNome());
            cognomeLabel.setText(contatto.getCognome());
            preferitoCheck.setSelected(contatto.isPreferito()); // Imposta lo stato della checkbox preferito

            // Popola i campi dei numeri di telefono
            if (contatto.getNumeri().size() > 0) numero1Label.setText(contatto.getNumeri().toArray()[0].toString());
            if (contatto.getNumeri().size() > 1) numero2Label.setText(contatto.getNumeri().toArray()[1].toString());
            if (contatto.getNumeri().size() > 2) numero3Label.setText(contatto.getNumeri().toArray()[2].toString());

            // Popola i campi delle email
            if (contatto.getEmail().size() > 0) email1Label.setText(contatto.getEmail().toArray()[0].toString());
            if (contatto.getEmail().size() > 1) email2Label.setText(contatto.getEmail().toArray()[1].toString());
            if (contatto.getEmail().size() > 2) email3Label.setText(contatto.getEmail().toArray()[2].toString());
        }
    }

    /**
     * @brief Gestisce l'azione di modifica di un contatto. Carica la vista per modificare il contatto.
     * 
     * @param event L'evento scatenato dal clic sul bottone "Modifica".
     */
    @FXML
    private void modificaContatto(ActionEvent event) {
        mainViewController.loadView2(contatto); // Carica la vista di modifica per il contatto selezionato
    }

    /**
     * @brief Gestisce l'azione di eliminazione di un contatto dalla rubrica. Se il contatto viene eliminato,
     *        la vista verrà aggiornata per mostrare il prossimo contatto disponibile.
     * 
     * @param event L'evento scatenato dal clic sul bottone "Elimina".
     */
    @FXML
    private void eliminaContatto(ActionEvent event) {
        if (contatto != null && rubrica != null) {
            // Rimuove il contatto dalla rubrica
            if (rubrica.rimuoviContatto(contatto)) {
                // Se la rubrica è vuota, carica una vista vuota
                if(rubrica.isRubricaVuota()) {
                    mainViewController.loadView2(null); // Carica una vista vuota
                } else {
                    // Se ci sono altri contatti, carica il primo contatto della lista
                    List<Contatto> list = new ArrayList<>(rubrica.getElenco());
                    mainViewController.loadView2(list.get(0)); // Carica il primo contatto
                }
            } else {
                // Stampa un errore se la rimozione non è andata a buon fine
                System.out.println("Errore nella rimozione del contatto.");
            }
        }
    }
}
