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
 * @brief Classe che rappresenta una raccolta di contatti con funzionalità per gestirli.
 *        Utilizza un `Set` per garantire che i contatti siano univoci e ordinati.
 *        Include anche un elenco separato per gestire i contatti preferiti.
 * @author Gruppo11
 */
public class Rubrica {

    private ObservableSet<Contatto> elenco;  /**< Set di contatti ordinati e senza duplicati. */
    private RubricaPreferiti elencoPreferiti;  /**< Gestione dei contatti preferiti. */

    /**
     * @brief Costruttore della classe Rubrica. Inizializza l'elenco dei contatti e dei preferiti.
     */
    public Rubrica() {
        this.elenco = FXCollections.observableSet(new TreeSet<>()); // TreeSet garantisce l'ordinamento naturale.
        this.elencoPreferiti = new RubricaPreferiti();
    }

    /**
     * @brief Restituisce l'elenco dei contatti nella rubrica.
     * @return Un `ObservableSet` contenente tutti i contatti presenti.
     */
    public ObservableSet<Contatto> getElenco() {
        return elenco;
    }

    /**
     * @brief Restituisce l'elenco dei contatti preferiti.
     * @return Un `ObservableSet` contenente i contatti preferiti.
     */
    public ObservableSet<Contatto> getElencoPreferiti() {
        return elencoPreferiti.getElencoPreferiti();
    }

    /**
     * @brief Cerca i contatti nella rubrica in base a un testo dato.
     *        Confronta il nome o il cognome con il testo.
     * @param text Testo da cercare.
     * @return Un `ObservableSet` contenente i risultati della ricerca.
     */
    public ObservableSet<Contatto> ricercaContatti(String text) {
        ObservableSet<Contatto> risultati = FXCollections.observableSet(new TreeSet<>());

        if (text == null || text.isEmpty()) {
            return getElenco();
        }

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
     * @param c Contatto da aggiungere.
     * @return true se il contatto è stato aggiunto con successo, false altrimenti.
     */
    public boolean aggiungiContatto(Contatto c) {
        if (!c.contattoValido()) {
            return false;
        }

        elenco.add(c);

        if (c.isPreferito()) {
            elencoPreferiti.addContattoPreferito(c);
        }

        return true;
    }

    /**
     * @brief Rimuove un contatto dalla rubrica.
     * @param c Contatto da rimuovere.
     * @return true se il contatto è stato rimosso con successo, false altrimenti.
     */
    public boolean rimuoviContatto(Contatto c) {
        if (isRubricaVuota()) {
            return false;
        }

        elenco.remove(c);

        if (c.isPreferito()) {
            elencoPreferiti.removeContattoPreferito(c);
        }

        return true;
    }

    /**
     * @brief Rimuove tutti i contatti dalla rubrica.
     */
    public void resetRubrica() {
        elenco.clear();
        elencoPreferiti.resetRubricaPreferiti();
    }

    /**
     * @brief Verifica se la rubrica è vuota.
     * @return true se la rubrica è vuota, false altrimenti.
     */
    public boolean isRubricaVuota() {
        return elenco.isEmpty();
    }

    /**
     * @brief Aggiunge un contatto alla lista dei preferiti.
     * @param c Contatto da aggiungere ai preferiti.
     */
    public void aggiungiAPreferiti(Contatto c) {
        if(!c.isPreferito())
            elencoPreferiti.addContattoPreferito(c);
    }

    /**
     * @brief Rimuove un contatto dalla lista dei preferiti.
     * @param c Contatto da rimuovere dai preferiti.
     */
    public void rimuoviDaPreferiti(Contatto c) {
        if (c.isPreferito()) 
            elencoPreferiti.removeContattoPreferito(c);
    }

    /**
     * @brief Esporta la rubrica in un file specificato dall'utente.
     * @throws IOException Se si verifica un errore durante l'esportazione.
     */
    public void esportaRubrica() throws IOException {
        FileChooser fileChooser = new FileChooser();
        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("Text File", "*.txt"));
        fileChooser.setTitle("Salva Rubrica");
        fileChooser.setInitialFileName("rubrica.txt");

        File file = fileChooser.showSaveDialog(null);

        if (file != null) {
            try (PrintWriter writer = new PrintWriter(new FileWriter(file))) {
                for (Contatto contatto : elenco) {
                    StringBuilder contattoLine = new StringBuilder();
                    contattoLine.append(contatto.getNome()).append(",");
                    contattoLine.append(contatto.getCognome()).append(",");

                    List<String> numeri = new ArrayList<>(contatto.getNumeri());
                    for (int i = 0; i < 3; i++) {
                        contattoLine.append(i < numeri.size() ? numeri.get(i) : "").append(",");
                    }

                    List<String> email = new ArrayList<>(contatto.getEmail());
                    for (int i = 0; i < 3; i++) {
                        contattoLine.append(i < email.size() ? email.get(i) : "").append(",");
                    }

                    contattoLine.append(contatto.isPreferito());
                    writer.println(contattoLine.toString());
                }
                System.out.println("Rubrica esportata con successo.");
            } catch (IOException e) {
                System.out.println("Errore durante l'esportazione della rubrica.");
            }
        }
    }

    /**
     * @brief Importa i contatti da un file scelto dall'utente.
     * @throws IOException Se si verifica un errore durante l'importazione.
     */
    public void importaRubrica() throws IOException {
        FileChooser fileChooser = new FileChooser();
        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("Text File", "*.txt"));
        fileChooser.setTitle("Seleziona un file da importare");

        File file = fileChooser.showOpenDialog(null);

        if (file != null) {
            try (Scanner scanner = new Scanner(file)) {
                while (scanner.hasNextLine()) {
                    String line = scanner.nextLine();
                    String[] contattoData = line.split(",");

                    String nome = contattoData[0];
                    String cognome = contattoData[1];

                    Set<String> numeri = new HashSet<>();
                    for (int i = 2; i < 5; i++) {
                        if (i < contattoData.length && !contattoData[i].trim().isEmpty()) {
                            numeri.add(contattoData[i].trim());
                        }
                    }

                    Set<String> email = new HashSet<>();
                    for (int i = 5; i < 8; i++) {
                        if (i < contattoData.length && !contattoData[i].trim().isEmpty()) {
                            email.add(contattoData[i].trim());
                        }
                    }

                    boolean preferito = Boolean.parseBoolean(contattoData[8]);
                    Contatto nuovoContatto = new Contatto(nome, cognome, numeri, email, preferito);
                    aggiungiContatto(nuovoContatto);
                }
                System.out.println("Rubrica importata con successo.");
            } catch (IOException e) {
                System.out.println("Errore nell'importazione del file.");
            }
        }
    }
}