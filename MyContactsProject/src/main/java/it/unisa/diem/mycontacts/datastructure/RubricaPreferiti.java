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
 * e ordinati.
 */
public class RubricaPreferiti {
    
    // Set che contiene i contatti preferiti, ordinato e senza duplicati.
    private final ObservableSet<Contatto> elencoPreferiti;

    /**
     * Costruttore della classe RubricaPreferiti. Inizializza l'elenco dei contatti preferiti
     * come un TreeSet, che garantisce l'ordinamento naturale dei contatti.
     */
    public RubricaPreferiti() {
        this.elencoPreferiti = FXCollections.observableSet(new TreeSet<>());
    }

    /**
     * Aggiunge un contatto alla lista dei preferiti.
     *
     * @param c il contatto da aggiungere.
     * @throws IllegalArgumentException se il contatto è null.
     */
    public void addContattoPreferito(Contatto c) {
        if (c == null) {
            throw new IllegalArgumentException("Il contatto non può essere null.");
        }
        elencoPreferiti.add(c);
    }

    /**
     * Rimuove un contatto dalla lista dei preferiti.
     *
     * @param c il contatto da rimuovere.
     * @throws IllegalArgumentException se il contatto è null.
     */
    public void removeContattoPreferito(Contatto c) {
        if (c == null) {
            throw new IllegalArgumentException("Il contatto non può essere null.");
        }
        elencoPreferiti.remove(c);
    }

    /**
     * Rimuove tutti i contatti dalla lista dei preferiti, svuotando l'elenco.
     */
    public void resetRubricaPreferiti() {
        elencoPreferiti.clear();
    }

    /**
     * Restituisce l'elenco dei contatti preferiti.
     *
     * @return un Set contenente tutti i contatti preferiti.
     */
    public ObservableSet<Contatto> getElencoPreferiti() {
        return elencoPreferiti;
    }
}

