/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
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
 * La classe Rubrica rappresenta una raccolta di contatti con funzionalità per gestirli.
 * Utilizza un Set per garantire che i contatti siano univoci e ordinati.
 * Include anche un elenco separato per gestire i contatti preferiti.
 */
public class Rubrica {

    // Contenitore per i contatti, ordinato e senza duplicati grazie all'implementazione TreeSet.
    private ObservableSet<Contatto> elenco;

    // Oggetto per la gestione dei contatti preferiti.
    private RubricaPreferiti elencoPreferiti;

    /**
     * Costruttore della classe Rubrica. Inizializza l'elenco dei contatti e l'elenco dei preferiti.
     */
    public Rubrica() {
        this.elenco = FXCollections.observableSet(new TreeSet<>()); // TreeSet garantisce l'ordinamento naturale dei contatti.
        this.elencoPreferiti = new RubricaPreferiti(); // Crea una nuova istanza di RubricaPreferiti.
    }

    /**
     * Restituisce l'elenco dei contatti nella rubrica.
     *
     * @return un Set contenente tutti i contatti presenti nella rubrica.
     */
    public ObservableSet<Contatto> getElenco() {
        return elenco;
    }

    /**
     * Restituisce l'elenco dei contatti preferiti.
     *
     * @return un oggetto RubricaPreferiti per gestire i contatti preferiti.
     */
    public ObservableSet<Contatto> getElencoPreferiti() {
        return elencoPreferiti.getElencoPreferiti();
    }
    
    
    public ObservableSet<Contatto> ricercaContatti(String text) {
        ObservableSet<Contatto> risultati = FXCollections.observableSet(new TreeSet<>());

        // Restituisce l'intera rubrica se il testo è nullo o vuoto.
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
     * Rimuove tutti i contatti dalla rubrica, svuotandola completamente.
     */
    public void resetRubrica() {
        elenco.clear();
        elencoPreferiti.resetRubricaPreferiti();
    }

    /**
     * Verifica se la rubrica è vuota.
     *
     * @return true se la rubrica è vuota, false altrimenti.
     */
    public boolean isRubricaVuota() {
        return elenco.isEmpty();
    }
    
    public void aggiungiAPreferiti(Contatto c) {
        elencoPreferiti.addContattoPreferito(c);
    }

    /**
     * Rimuove un contatto dalla lista dei preferiti.
     *
     * @param c il contatto da rimuovere dai preferiti.
     * @throws IllegalArgumentException se il contatto non è presente nella lista dei preferiti.
     */
    public void rimuoviDaPreferiti(Contatto c) {
        if (!c.isPreferito()) {
            throw new IllegalArgumentException("Il contatto non è presente nella lista dei preferiti.");
        }
        elencoPreferiti.removeContattoPreferito(c);
    }
    
     /**
     * Esporta la rubrica in un file specificato.
     * Questo metodo deve essere implementato per salvare i dati su disco.
     * 
     * @throws IOException se si verifica un errore durante l'esportazione.
     */
    public void esportaRubrica() throws IOException {
        // Implementazione da completare per salvare la rubrica su un file.
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
                for (Contatto contatto : elenco) {
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
                System.out.println("Errore durante l'esportazione della rubrica.");
            }
        }
    }
    
    public void importaRubrica() throws IOException {
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
                    elenco.add(nuovoContatto);
                }

                scanner.close();
                System.out.println("Rubrica importata con successo.");
            } catch (IOException e) {
                System.out.println("Errore nell'importazione del file.");
            }
        }
    }
    
}

    
    
