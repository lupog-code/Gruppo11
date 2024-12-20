package it.unisa.diem.mycontacts.datastructure;

import it.unisa.diem.mycontacts.data.Contatto;
import it.unisa.diem.mycontacts.exceptions.InvalidContactException;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Scanner;
import java.util.Set;
import java.util.TreeSet;
import javafx.collections.FXCollections;
import javafx.collections.ObservableSet;
import javafx.scene.control.Alert;
import javafx.stage.FileChooser;

/**
 * @class Rubrica
 * @brief Classe che rappresenta una rubrica di contatti. Permette di gestire contatti,
 *        inclusi quelli preferiti, e offre funzionalità per l'importazione e l'esportazione.
 */
public class Rubrica {

    // Contenitore per i contatti, ordinato e senza duplicati grazie all'implementazione di TreeSet.
    private ObservableSet<Contatto> elenco;

    // Oggetto per la gestione dei contatti preferiti.
    private RubricaPreferiti elencoPreferiti;

    /**
     * @brief Costruttore della classe Rubrica. Inizializza l'elenco dei contatti e l'elenco dei preferiti.
     */
    public Rubrica() {
        this.elenco = FXCollections.observableSet(new TreeSet<>()); // TreeSet garantisce l'ordinamento naturale dei contatti.
        this.elencoPreferiti = new RubricaPreferiti(); // Crea una nuova istanza di RubricaPreferiti.
    }

    /**
     * @brief Restituisce l'elenco dei contatti nella rubrica.
     * 
     * @return un Set contenente tutti i contatti presenti nella rubrica.
     */
    public ObservableSet<Contatto> getElenco() {
        return elenco;
    }

    /**
     * @brief Restituisce l'elenco dei contatti preferiti.
     * 
     * @return un oggetto RubricaPreferiti per gestire i contatti preferiti.
     */
    public ObservableSet<Contatto> getElencoPreferiti() {
        return elencoPreferiti.getElencoPreferiti();
    }

    /**
     * @brief Esegue una ricerca dei contatti in base al testo inserito.
     * La ricerca avviene per cognome o nome.
     * 
     * @param text Testo da ricercare.
     * @return un Set contenente i contatti che corrispondono alla ricerca.
     */
    public ObservableSet<Contatto> ricercaContatti(String text) {
        ObservableSet<Contatto> risultati = FXCollections.observableSet(new TreeSet<>());

        // Restituisce l'intera rubrica se il testo di ricerca è nullo o vuoto.
        if (text == null || text.isEmpty()) return getElenco();

        // Aggiunge i contatti che corrispondono alla ricerca.
        for (Contatto c : getElenco()) {
            if (c.getCognome().toLowerCase().startsWith(text.toLowerCase()) || 
                c.getNome().toLowerCase().startsWith(text.toLowerCase())) {
                risultati.add(c);
            }
        }

        return risultati;
    }
    
    /**
     * @brief Esegue una ricerca dei preferiti in base al testo inserito.
     * La ricerca avviene per cognome o nome.
     * 
     * @param text Testo da ricercare.
     * @return un Set contenente i contatti che corrispondono alla ricerca.
     */
    public ObservableSet<Contatto> ricercaPreferiti(String text) {
        return elencoPreferiti.ricercaContattiPreferiti(text);
    }

    /**
     * @brief Aggiunge un contatto alla rubrica.
     * Se il contatto è marcato come preferito, lo aggiunge anche alla lista dei preferiti.
     * 
     * @param c Contatto da aggiungere.
     */
    public void aggiungiContatto(Contatto c) {
        // Aggiunge il contatto alla rubrica.
        elenco.add(c);

        // Aggiunge il contatto ai preferiti se è contrassegnato come preferito.
        
         elencoPreferiti.addContattoPreferito(c);
        
    }
    
    /**
     * @brief Rimuove un contatto dalla rubrica.
     * Se il contatto è preferito, viene rimosso anche dalla lista dei preferiti.
     * 
     * @param c Contatto da rimuovere.
     */
    public void rimuoviContatto(Contatto c) {
        // Verifica se la rubrica è vuota.
        if (isRubricaVuota()) {
            return;
        }

        // Rimuove il contatto dall'elenco principale.
        elenco.remove(c);

        // Rimuove il contatto dalla lista dei preferiti se esiste.
        
         elencoPreferiti.removeContattoPreferito(c);
        

    }

    /**
     * @brief Rimuove tutti i contatti dalla rubrica, svuotandola completamente.
     */
    public void resetRubrica() {
        elenco.clear();
        elencoPreferiti.resetRubricaPreferiti();
    }

    /**
     * @brief Verifica se la rubrica è vuota.
     * 
     * @return true se la rubrica è vuota, false altrimenti.
     */
    public boolean isRubricaVuota() {
        return elenco.isEmpty();
    }
    
    /**
     * @brief Aggiunge un contatto alla lista dei preferiti.
     * 
     * @param c Contatto da aggiungere ai preferiti.
     */
    public void aggiungiAPreferiti(Contatto c) {
        elencoPreferiti.addContattoPreferito(c);
    }

    /**
     * @brief Rimuove un contatto dalla lista dei preferiti.
     * 
     * @param c Contatto da rimuovere.
     * @throws IllegalArgumentException se il contatto non è presente nei preferiti.
     */
    public void rimuoviDaPreferiti(Contatto c) {
        if (!c.isPreferito()) {
            throw new IllegalArgumentException("Il contatto non è presente nella lista dei preferiti.");
        }
        elencoPreferiti.removeContattoPreferito(c);
    }
    
    /**
     * @brief Esporta la rubrica in un file specificato.
     * 
     * @throws IOException se si verifica un errore durante l'esportazione.
     */
    public void esportaRubrica() throws IOException {
        // Crea un FileChooser per salvare la rubrica su un file.
        FileChooser fileChooser = new FileChooser();
        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("Text File", "*.txt"));
        fileChooser.setTitle("Salva Rubrica");

        // Imposta un nome di file predefinito.
        fileChooser.setInitialFileName("rubrica.txt");

        // Mostra la finestra di dialogo per scegliere il file.
        File file = fileChooser.showSaveDialog(null);

        if (file != null) {
            try (PrintWriter writer = new PrintWriter(new FileWriter(file))) {
                // Itera attraverso i contatti della rubrica e li scrive nel file.
                if(elenco.isEmpty()){
                    writer.println("Non sono presenti elementi in rubrica");
                    return;
                }
                    
                for (Contatto contatto : elenco) {
                    // Scrive nel file.
                    writer.println(contatto.toString());
                }

            } catch (IOException e) {
                System.out.println("Errore durante l'esportazione della rubrica.");
            }
        }
    }
    
    /**
     * @throws it.unisa.diem.mycontacts.exceptions.InvalidContactException
     * @brief Importa una rubrica da un file specificato.
     * 
     * @throws IOException se si verifica un errore durante l'importazione.
     */
    public void importaRubrica() throws IOException {
    // Crea un FileChooser per selezionare il file da importare.
    FileChooser fileChooser = new FileChooser();
    fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("Text File", "*.txt"));
    fileChooser.setTitle("Seleziona un file da importare");

    // Mostra la finestra di dialogo per scegliere il file.
    File file = fileChooser.showOpenDialog(null);

    if (file != null) {
        boolean errorOccurred = false;
        try (Scanner scanner = new Scanner(file)) { // Uso try-with-resources per gestire lo scanner.
            // Legge il file riga per riga.
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                
                try {
                    // Dividi la riga in base a virgole, punti e virgola o trattini.
                    String[] contattoData = line.split("[,;\\-]");

                    // Controlla se ci sono esattamente 9 campi.
                    if (contattoData.length != 9) {
                        throw new InvalidContactException("Formato contatto non valido: campi insufficienti o eccessivi.");
                    }

                    String nome = contattoData[0].trim();
                    String cognome = contattoData[1].trim();

                    // Controllo lunghezza massima per nome e cognome.
                    if (nome.length() > 20 || cognome.length() > 20) {
                        throw new InvalidContactException("Nome o cognome troppo lunghi: massimo 20 caratteri.");
                    }
                    
                    // Verifica che i campi 3-4 siano numerici e abbiano lunghezza massima 15.
                    Set<String> numeri = new HashSet<>();
                    for (int i = 2; i < 5; i++) {
                        if (!contattoData[i].trim().isEmpty()) {
                            if (!contattoData[i].trim().matches("\\d+") || contattoData[i].trim().length() > 15) {
                                throw new InvalidContactException("Formato numero non valido: devono essere solo cifre e massimo 15 caratteri.");
                            }
                            numeri.add(contattoData[i].trim());
                        }
                    }

                    // Gestisce le email (fino a 3 email).
                    Set<String> email = new HashSet<>();
                    for (int i = 5; i < 8; i++) {
                        if (!contattoData[i].trim().isEmpty()) {
                            email.add(contattoData[i].trim());
                        }
                    }

                    // Verifica che l'ultimo campo sia true o false.
                    String preferitoField = contattoData[8].trim();
                    if (!preferitoField.equalsIgnoreCase("true") && !preferitoField.equalsIgnoreCase("false")) {
                        throw new InvalidContactException("Il campo 'preferito' deve essere 'true' o 'false'.");
                    }
                    boolean preferito = Boolean.parseBoolean(preferitoField);

                    // Crea il nuovo contatto.
                    Contatto nuovoContatto = new Contatto(nome, cognome, numeri, email, preferito);
                    aggiungiContatto(nuovoContatto);
                } catch (InvalidContactException e) {
                    // Gestisce eventuali errori relativi a un singolo record.
                    errorOccurred = true;
                    showAlert("Errore Importazione Contatto", "Alcuni contatti non sono stati importati correttamente.\n" + e.getMessage());
                }
            }
        } catch (IOException e1) {
            errorOccurred = true;
        }

        if (!errorOccurred) {
            showAlert("Importazione completata", "Tutti i contatti sono stati importati con successo.");
        }
    }
}
    
    /**
     * @brief Mostra un messaggio di errore in un'alert box.
     * 
     * @param title Il titolo della finestra di alert.
     * @param message Il messaggio da visualizzare nell'alert.
     */
    private void showAlert(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR); // Crea un alert di tipo errore
        alert.setTitle(title); // Imposta il titolo dell'alert
        alert.setHeaderText(null); // Nessun header
        alert.setContentText(message); // Imposta il messaggio dell'alert
        alert.showAndWait(); // Mostra l'alert e aspetta che l'utente lo chiuda
    }

}
