package it.unisa.diem.mycontacts.controller;

import it.unisa.diem.mycontacts.data.Contatto;
import it.unisa.diem.mycontacts.datastructure.Rubrica;
import it.unisa.diem.mycontacts.exceptions.InvalidContactException;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.ObservableSet;
import javafx.collections.SetChangeListener;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleButton;
import javafx.scene.input.KeyEvent;

/**
 * @class LeftViewController
 * @brief Questa classe funge da controller per la vista a sinistra dell'applicazione, gestendo
 *        la visualizzazione e l'interazione con la rubrica di contatti. Gestisce l'aggiunta,
 *        ricerca, importazione, esportazione e visualizzazione dei contatti preferiti.
 */
public class LeftViewController implements Initializable {

    // Riferimenti agli elementi dell'interfaccia utente
    @FXML
    private Label labelRubrica;
    @FXML
    private MenuItem addButton;
    @FXML
    private MenuItem importButton;
    @FXML
    private MenuItem exportButton;
    @FXML
    private MenuItem resetButton;
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
    
    // Oggetti per gestire la rubrica e le liste di contatti
    private Rubrica rubrica;
    private static ObservableList<Contatto> listaContatti;  
    private ObservableList<Contatto> listaContattiPreferiti;  
    
    // Riferimento al controller della vista principale
    private MainViewController mainViewController;
    @FXML
    private Menu cambiaSfondo;
    
    /**
     * @brief Metodo di inizializzazione del controller. Configura le colonne della TableView
     *        e imposta i listener per le modifiche nella rubrica.
     * 
     * @param url L'URL del file FXML.
     * @param rb Le risorse locali utilizzate nel file FXML.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // Configura le celle per le colonne nome e cognome
        nomeColumn.setCellValueFactory(c -> { return new SimpleStringProperty(c.getValue().getNome()); });
        cognomeColumn.setCellValueFactory(c -> { return new SimpleStringProperty(c.getValue().getCognome()); });
        
        // Inizializza la lista dei contatti e la associa alla TableView
        listaContatti = FXCollections.observableArrayList();
        contattiTable.setItems(listaContatti);
        
        // Aggiunge un listener per la selezione di un contatto nella TableView
        contattiTable.getSelectionModel().selectedItemProperty().addListener((observable, oldVContact, newContact) -> {
            if (newContact != null) {
                // Passa il contatto selezionato al MainViewController per caricare la RightView2
                mainViewController.loadView1(newContact);
            }
        });
    }    

    /**
     * @brief Imposta il controller della vista principale e inizializza la rubrica.
     *        Popola la TableView con i contatti della rubrica e aggiorna le liste di contatti preferiti.
     * 
     * @param mainViewController Il controller della vista principale.
     */
    public void setMainViewController(MainViewController mainViewController) {
        this.mainViewController = mainViewController;
        
        // Ottiene la rubrica dal MainViewController e la associa alla TableView
        rubrica = mainViewController.getRubrica();
        listaContatti = FXCollections.observableArrayList(rubrica.getElenco());
        contattiTable.setItems(listaContatti);
        
        // Crea e aggiorna la lista dei contatti preferiti
        listaContattiPreferiti = FXCollections.observableArrayList(rubrica.getElencoPreferiti());
        
        // Aggiunge un listener per gestire le modifiche nella rubrica
        ObservableSet<Contatto> rubricaSet = rubrica.getElenco();
        rubricaSet.addListener((SetChangeListener<Contatto>) change -> {
            if (change.wasAdded()) {
                listaContatti.add(change.getElementAdded());
            }
            if (change.wasRemoved()) {
                listaContatti.remove(change.getElementRemoved());
            }
        });
        
        // Aggiunge un listener per gestire le modifiche nei contatti preferiti
        ObservableSet<Contatto> rubricaPreferitiSet = rubrica.getElencoPreferiti();
        rubricaPreferitiSet.addListener((SetChangeListener<Contatto>) change -> {
            if (change.wasAdded()) {
                listaContattiPreferiti.add(change.getElementAdded());
            }
            if (change.wasRemoved()) {
                listaContattiPreferiti.remove(change.getElementRemoved());
            }
        });
    }

    /**
     * @brief Gestisce l'azione di aggiunta di un nuovo contatto.
     *        Carica la vista per aggiungere un nuovo contatto.
     * 
     * @param event L'evento scatenato dal clic sul bottone.
     */
    @FXML
    private void aggiungiContatto(ActionEvent event) {
        mainViewController.loadView2(null); // Carica la vista per aggiungere un contatto
    }

    /**
     * @brief Gestisce l'azione di importazione della rubrica.
     *        Carica i contatti da un file esterno.
     * 
     * @param event L'evento scatenato dal clic sul bottone.
     * @throws IOException Se si verifica un errore durante l'importazione.
     */
    @FXML
    private void importaRubrica(ActionEvent event) throws IOException, InvalidContactException {
        rubrica.importaRubrica(); // Importa la rubrica
    }
    
    /**
     * @brief Gestisce l'azione di esportazione della rubrica.
     *        Salva i contatti su un file esterno.
     * 
     * @param event L'evento scatenato dal clic sul bottone.
     * @throws IOException Se si verifica un errore durante l'esportazione.
     */
    @FXML
    private void esportaRubrica(ActionEvent event) throws IOException {
        rubrica.esportaRubrica(); // Esporta la rubrica
    }

    /**
     * @brief Gestisce la ricerca di contatti nella rubrica.
     *        Filtra i contatti in base al testo inserito nel campo di ricerca.
     * 
     */
    @FXML
    private void ricercaContatto() {
        // Ottieni il testo dalla searchField
        String searchText = searchField.getText();
        ObservableList<Contatto> risultatiList;

        // Esegui la ricerca nella rubrica
        if(!prefToggle.isSelected()) {
            // Aggiorna la TableView con i risultati della ricerca
            risultatiList = FXCollections.observableArrayList(rubrica.ricercaContatti(searchText));
        } else {
            risultatiList = FXCollections.observableArrayList(rubrica.ricercaPreferiti(searchText));
        }
            
        contattiTable.setItems(risultatiList); // Imposta i risultati filtrati nella TableView
    }

    /**
     * @brief Mostra i contatti preferiti o tutti i contatti in base allo stato del ToggleButton.
     * 
     * @param event L'evento scatenato dal clic sul bottone di Toggle.
     */
    @FXML
    private void mostraPreferiti(ActionEvent event) {
        // Verifica se il bottone è attivo
        if (prefToggle.isSelected()) {
            // Se il toggle è selezionato, mostra solo i contatti preferiti
            contattiTable.setItems(listaContattiPreferiti); // Imposta la lista di contatti preferiti
            labelRubrica.setText("Rubrica preferiti:"); 
        } else {
            // Se il toggle non è selezionato, mostra tutti i contatti
            contattiTable.setItems(listaContatti); // Imposta la lista di tutti i contatti
            labelRubrica.setText("Rubrica:");
        }
    }

    /**
     * @brief Resetta la rubrica e carica la vista per aggiungere un nuovo contatto.
     * 
     * @param event L'evento scatenato dal clic sul bottone di reset.
     */
    @FXML
    private void resetRubrica(ActionEvent event) {
        rubrica.resetRubrica(); // Resetta la rubrica
        mainViewController.loadView2(null); // Carica la vista per aggiungere un contatto
    }

    public static ObservableList<Contatto> getListaContatti() {
        return listaContatti;
    }
    
    
}
