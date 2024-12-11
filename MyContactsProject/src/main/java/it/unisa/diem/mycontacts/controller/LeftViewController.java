/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package it.unisa.diem.mycontacts.controller;

import it.unisa.diem.mycontacts.data.Contatto;
import it.unisa.diem.mycontacts.datastructure.Rubrica;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
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
    
    private Rubrica rubrica;
    
    private ObservableList<Contatto> listaContatti;  
    
    private ObservableList<Contatto> listaContattiPreferiti;  
    
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
        
        rubrica = mainViewController.getRubrica();
        
        listaContatti = FXCollections.observableArrayList(rubrica.getElenco());
        contattiTable.setItems(listaContatti); // Collega la lista osservabile alla TableView
        
        listaContattiPreferiti = FXCollections.observableArrayList(rubrica.getElencoPreferiti().getElencoPreferiti());
        
        ObservableSet<Contatto> rubricaSet = rubrica.getElenco();
        rubricaSet.addListener((SetChangeListener<Contatto>) change -> {
                if (change.wasAdded()) {
                    listaContatti.add(change.getElementAdded());
                }
                if (change.wasRemoved()) {
                    listaContatti.remove(change.getElementRemoved());
                }
        });  
        
        ObservableSet<Contatto> rubricaPreferitiSet = rubrica.getElencoPreferiti().getElencoPreferiti();
        rubricaPreferitiSet.addListener((SetChangeListener<Contatto>) change -> {
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
                for (int i = 2; i < 5; i++) {  // Fino a 3 numeri
                    if (i < contattoData.length && !contattoData[i].trim().isEmpty()) {
                        numeri.add(contattoData[i].trim());  // Aggiungi solo se non vuoto
                    }
                }

                // Gestisci le email (assumendo che siano separati da virgola)
                  Set<String> email = new HashSet<>();
                for (int i = 5; i < 8; i++) {  // Fino a 3 email
                    if (i < contattoData.length && !contattoData[i].trim().isEmpty()) {
                        email.add(contattoData[i].trim());  // Aggiungi solo se non vuoto
                    }
                }

                // Gestisci il campo preferito (vero o falso)
                boolean preferito = Boolean.parseBoolean(contattoData[8]);

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
        // Crea un FileChooser per salvare il file
        FileChooser fileChooser = new FileChooser();
        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("Text File", "*.txt"));
        fileChooser.setTitle("Salva Rubrica");

        // Imposta un nome di file predefinito
        fileChooser.setInitialFileName("rubrica.txt");

        // Mostra la finestra di dialogo per scegliere il file
        File file = fileChooser.showSaveDialog(null);

        if (file != null) {
            try (PrintWriter writer = new PrintWriter(new FileWriter(file))) {
                // Itera attraverso i contatti della rubrica
                for (Contatto contatto : mainViewController.getRubrica().getElenco()) {
                    // Costruisci una stringa per ogni contatto
                    StringBuilder contattoLine = new StringBuilder();
                    contattoLine.append(contatto.getNome()).append(",");
                    contattoLine.append(contatto.getCognome()).append(",");

                    // Aggiungi numeri (se presenti)
                    List<String> numeri = new ArrayList<>(contatto.getNumeri());
                    for (int i = 0; i < 3; i++) {
                        contattoLine.append(i < numeri.size() ? numeri.get(i) : "").append(",");
                    }
                    // Aggiungi email (se presenti)
                     List<String> email = new ArrayList<>(contatto.getEmail());
                    for (int i = 0; i < 3; i++) {
                        contattoLine.append(i < email.size() ? email.get(i) : "").append(",");
                    }

                    // Aggiungi il flag preferito
                    contattoLine.append(contatto.isPreferito());

                    // Scrivi nel file
                    writer.println(contattoLine.toString());
                }

                System.out.println("Rubrica esportata con successo.");
            } catch (IOException e) {
                e.printStackTrace();
                System.out.println("Errore durante l'esportazione della rubrica.");
            }
        }

    }

    @FXML
    private void ricercaContatto(KeyEvent event) {
            // Ottieni il testo dalla searchField
    String searchText = searchField.getText();

    // Esegui la ricerca nella rubrica
    ObservableSet<Contatto> risultati = mainViewController.getRubrica().ricercaContatti(searchText);

    // Aggiorna la TableView con i risultati della ricerca
    ObservableList<Contatto> risultatiList = FXCollections.observableArrayList(risultati);
    contattiTable.setItems(risultatiList);  // Imposta i risultati filtrati nella TableView
    }

    @FXML
    private void mostraPreferiti(ActionEvent event) {
        // Verifica se il bottone è attivo
        if (prefToggle.isSelected()) {
            // Se il toggle è selezionato, mostra solo i contatti preferiti
            contattiTable.setItems(listaContattiPreferiti); // Imposta la lista di contatti preferiti
        } else {
            // Se il toggle non è selezionato, mostra tutti i contatti
            contattiTable.setItems(listaContatti); // Imposta la lista di tutti i contatti
        }
    }
    
}
