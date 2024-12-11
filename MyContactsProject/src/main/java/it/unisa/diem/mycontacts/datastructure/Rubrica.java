package it.unisa.diem.mycontacts.datastructure;

import it.unisa.diem.mycontacts.data.Contatto;
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
     * @brief Aggiunge un contatto alla rubrica.
     * Se il contatto è marcato come preferito, lo aggiunge anche alla lista dei preferiti.
     * 
     * @param c Contatto da aggiungere.
     * @return true se il contatto è stato aggiunto con successo, false se non valido.
     */
    public boolean aggiungiContatto(Contatto c) {
        // Verifica la validità del contatto.
        if (!c.contattoValido()) {
            return false;
        }

        // Aggiunge il contatto alla rubrica.
        elenco.add(c);

        // Aggiunge il contatto ai preferiti se è contrassegnato come preferito.
        if (c.isPreferito()) {
            elencoPreferiti.addContattoPreferito(c);
        }

        return true;
    }
    
    /**
     * @brief Rimuove un contatto dalla rubrica.
     * Se il contatto è preferito, viene rimosso anche dalla lista dei preferiti.
     * 
     * @param c Contatto da rimuovere.
     * @return true se il contatto è stato rimosso con successo, false se la rubrica è vuota.
     */
    public boolean rimuoviContatto(Contatto c) {
        // Verifica se la rubrica è vuota.
        if (isRubricaVuota()) {
            return false;
        }

        // Rimuove il contatto dall'elenco principale.
        elenco.remove(c);

        // Rimuove il contatto dalla lista dei preferiti se esiste.
        if (c.isPreferito()) {
            elencoPreferiti.removeContattoPreferito(c);
        }

        return true;
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
                for (Contatto contatto : elenco) {
                    // Costruisce una stringa per ogni contatto.
                    StringBuilder contattoLine = new StringBuilder();
                    contattoLine.append(contatto.getNome()).append(",");
                    contattoLine.append(contatto.getCognome()).append(",");

                    // Aggiunge numeri (se presenti).
                    List<String> numeri = new ArrayList<>(contatto.getNumeri());
                    for (int i = 0; i < 3; i++) {
                        contattoLine.append(i < numeri.size() ? numeri.get(i) : "").append(",");
                    }

                    // Aggiunge email (se presenti).
                    List<String> email = new ArrayList<>(contatto.getEmail());
                    for (int i = 0; i < 3; i++) {
                        contattoLine.append(i < email.size() ? email.get(i) : "").append(",");
                    }

                    // Aggiunge il flag preferito.
                    contattoLine.append(contatto.isPreferito());

                    // Scrive nel file.
                    writer.println(contattoLine.toString());
                }

                System.out.println("Rubrica esportata con successo.");
            } catch (IOException e) {
                System.out.println("Errore durante l'esportazione della rubrica.");
            }
        }
    }
    
    /**
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
            try {
                // Crea uno scanner per leggere il file.
                Scanner scanner = new Scanner(file);

                // Legge il file riga per riga.
                while (scanner.hasNextLine()) {
                    String line = scanner.nextLine();

                    // Dividi la riga in base alla virgola.
                    String[] contattoData = line.split(",");

                    String nome = contattoData[0];
                    String cognome = contattoData[1];

                    // Gestisce i numeri (fino a 3 numeri).
                    Set<String> numeri = new HashSet<>();
                    for (int i = 2; i < 5; i++) {  // Fino a 3 numeri
                        if (i < contattoData.length && !contattoData[i].trim().isEmpty()) {
                            numeri.add(contattoData[i].trim());  // Aggiunge solo se non vuoto
                        }
                    }

                    // Gestisce le email (fino a 3 email).
                    Set<String> email = new HashSet<>();
                    for (int i = 5; i < 8; i++) {  // Fino a 3 email
                        if (i < contattoData.length && !contattoData[i].trim().isEmpty()) {
                            email.add(contattoData[i].trim());  // Aggiunge solo se non vuoto
                        }
                    }

                    // Gestisce il campo preferito (vero o falso).
                    boolean preferito = Boolean.parseBoolean(contattoData[8]);

                    // Crea il nuovo contatto.
                    Contatto nuovoContatto = new Contatto(nome, cognome, numeri, email, preferito);
                    aggiungiContatto(nuovoContatto);
                }

                scanner.close();
                System.out.println("Rubrica importata con successo.");
            } catch (IOException e) {
                System.out.println("Errore nell'importazione del file.");
            }
        }
    }
}
