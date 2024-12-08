/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package it.unisa.diem.mycontacts.datastructure;

import it.unisa.diem.mycontacts.data.Contatto;
import java.io.IOException;
import java.util.Set;
import java.util.TreeSet;
import javafx.collections.FXCollections;
import javafx.collections.ObservableSet;

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
        this.elenco = FXCollections.observableSet(new TreeSet()); // TreeSet garantisce l'ordinamento naturale dei contatti.
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
    
    /**
     * Aggiunge un contatto all'elenco della rubrica.Se il contatto è già presente, non verrà aggiunto nuovamente.
     *
     * @param c il contatto da aggiungere.
     * @return 
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
            elencoPreferiti.removeContattoPreferito(c);
        }

        return true;
    }

    /**
     * Rimuove un contatto dall'elenco della rubrica.
     *
     * @param c il contatto da rimuovere.
     * @return
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

    /**
     * Aggiunge un contatto alla lista dei preferiti.
     *
     * @param c il contatto da aggiungere ai preferiti.
     * @throws IllegalArgumentException se il contatto non è presente nella rubrica.
     */
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
    
    public static void importaRubrica(String nomefile) throws IOException {
        // Implementazione da completare per leggere e importare la rubrica da un file.
        throw new UnsupportedOperationException("Metodo non implementato.");
    }
    
    /**
     * Esporta la rubrica in un file specificato.
     * Questo metodo deve essere implementato per salvare i dati su disco.
     * 
     * @param nomefile il nome del file su cui esportare la rubrica.
     * @throws IOException se si verifica un errore durante l'esportazione.
     */
    public void esportaRubrica(String nomefile) throws IOException {
        // Implementazione da completare per salvare la rubrica su un file.
        throw new UnsupportedOperationException("Metodo non implementato.");
    }
    
}

    
    
