/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package it.unisa.diem.mycontacts.datastructure;

import it.unisa.diem.mycontacts.data.Contatto;
import java.util.Set;
import java.util.TreeSet;
import javafx.collections.FXCollections;
import javafx.collections.ObservableSet;

/**
 * La classe RubricaPreferiti rappresenta un contenitore per gestire i contatti preferiti
 * all'interno di una rubrica. Utilizza un Set per garantire che i contatti siano univoci
 * e ordinati. La struttura dati utilizzata è un ObservableSet, che consente anche di 
 * notificare automaticamente gli ascoltatori (come l'interfaccia utente) in caso di modifiche.
 */
public class RubricaPreferiti {
    
    // Set che contiene i contatti preferiti, ordinato e senza duplicati.
    private ObservableSet<Contatto> elencoPreferiti;

    /**
     * Costruttore della classe RubricaPreferiti. Inizializza l'elenco dei contatti preferiti
     * come un TreeSet, che garantisce l'ordinamento naturale dei contatti.
     * Inoltre, l'ObservableSet permette di monitorare e aggiornare la UI quando la lista dei preferiti cambia.
     */
    public RubricaPreferiti() {
        // Inizializzazione dell'elenco dei preferiti come un TreeSet ordinato
        this.elencoPreferiti = FXCollections.observableSet(new TreeSet<>());
    }

    /**
     * Aggiunge un contatto alla lista dei preferiti.
     * Se il contatto è già presente nell'elenco, non verrà aggiunto di nuovo (grazie al comportamento del Set).
     *
     * @param c il contatto da aggiungere.
     */
    public void addContattoPreferito(Contatto c) {
        elencoPreferiti.add(c);  // Aggiunge il contatto all'elenco dei preferiti.
    }

    /**
     * Rimuove un contatto dalla lista dei preferiti.
     * Se il contatto non è presente, non succede nulla (comportamento del Set).
     *
     * @param c il contatto da rimuovere.
     */
    public void removeContattoPreferito(Contatto c) {
        elencoPreferiti.remove(c);  // Rimuove il contatto dall'elenco dei preferiti.
    }

    /**
     * Rimuove tutti i contatti dalla lista dei preferiti, svuotando completamente l'elenco.
     */
    public void resetRubricaPreferiti() {
        elencoPreferiti.clear();  // Svuota l'elenco dei preferiti.
    }

    /**
     * Restituisce l'elenco dei contatti preferiti.
     * L'elenco è rappresentato come un ObservableSet, che permette di monitorare 
     * eventuali modifiche e riflettere automaticamente le modifiche nell'interfaccia utente.
     *
     * @return un Set contenente tutti i contatti preferiti.
     */
    public ObservableSet<Contatto> getElencoPreferiti() {
        return elencoPreferiti;  // Restituisce l'elenco dei contatti preferiti.
    }
}
