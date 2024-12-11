/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package it.unisa.diem.mycontacts.controller;

import it.unisa.diem.mycontacts.data.Contatto;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.util.HashSet;
import java.util.ResourceBundle;
import java.util.Scanner;
import java.util.Set;
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
import javafx.stage.FileChooser;

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
    // Crea un FileChooser per selezionare il file da importare
    FileChooser fileChooser = new FileChooser();
    fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("Text File", "*.txt"));
    fileChooser.setTitle("Seleziona un file da importare");

    // Mostra la finestra di dialogo
    File file = fileChooser.showOpenDialog(null);

    if (file != null) {
        try {
            // Crea uno scanner per leggere il file
            Scanner scanner = new Scanner(file);

            // Leggi il file riga per riga
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                
                // Dividi la riga in base alla virgola
                String[] contattoData = line.split(",");
                
                

                String nome = contattoData[0];
                String cognome = contattoData[1];

                // Gestisci i numeri (assumendo che siano separati da virgola)
                Set<String> numeri = new HashSet<>();
                for (int i = 2; i < 4; i++) {
                    try {
                        numeri.add((contattoData[i]));
                    } catch (NumberFormatException e) {
                        System.out.println("Numero non valido nella riga: " + line);
                    }
                }

                // Gestisci le email (assumendo che siano separati da virgola)
                Set<String> email = new HashSet<>();
                for (int i = 4; i < 6; i++) {
                    email.add(contattoData[i]);
                }

                // Gestisci il campo preferito (vero o falso)
                boolean preferito = Boolean.parseBoolean(contattoData[6]);

                // Crea il nuovo contatto
                Contatto nuovoContatto = new Contatto(nome, cognome, numeri, email, preferito);
                mainViewController.getRubrica().aggiungiContatto(nuovoContatto);
            }

            scanner.close();
            System.out.println("Rubrica importata con successo.");
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Errore nell'importazione del file.");
        }
    }

     
}




    @FXML
    private void esportaRubrica(ActionEvent event) {
    }

    @FXML
    private void ricercaContatto(KeyEvent event) {
        
    }

    @FXML
    private void mostraPreferiti(ActionEvent event) {
        // Verifica se il bottone è attivo
    if (prefToggle.isSelected()) {
        // Se il toggle è selezionato, mostra solo i contatti preferiti
        ObservableList<Contatto> preferiti = FXCollections.observableArrayList();
        for (Contatto contatto : mainViewController.getRubrica().getElenco()) {
            if (contatto.isPreferito()) {
                preferiti.add(contatto);
            }
        }
        contattiTable.setItems(preferiti); // Imposta la lista di contatti preferiti
    } else {
        // Se il toggle non è selezionato, mostra tutti i contatti
        ObservableList<Contatto> tuttiContatti = FXCollections.observableArrayList(mainViewController.getRubrica().getElenco());
        contattiTable.setItems(tuttiContatti); // Imposta la lista di tutti i contatti
    }
    }
    
}
