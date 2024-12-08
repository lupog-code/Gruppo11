/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package it.unisa.diem.mycontacts.datastructure;

import it.unisa.diem.mycontacts.data.Contatto;
import java.util.Set;
import java.util.TreeSet;

/**
 * La classe Rubrica rappresenta una raccolta di contatti con funzionalità per gestirli.
 * Utilizza un Set per garantire che i contatti siano univoci e ordinati.
 * Include anche un elenco separato per gestire i contatti preferiti.
 */
public class Rubrica {

    // Contenitore per i contatti, ordinato e senza duplicati grazie all'implementazione TreeSet.
    private Set<Contatto> elenco;

    // Oggetto per la gestione dei contatti preferiti.
    private RubricaPreferiti elencoPreferiti;

    /**
     * Costruttore della classe Rubrica. Inizializza l'elenco dei contatti e l'elenco dei preferiti.
     */
    public Rubrica() {
        this.elenco = new TreeSet<>(); // TreeSet garantisce l'ordinamento naturale dei contatti.
        this.elencoPreferiti = new RubricaPreferiti(); // Crea una nuova istanza di RubricaPreferiti.
    }

    /**
     * Restituisce l'elenco dei contatti nella rubrica.
     *
     * @return un Set contenente tutti i contatti presenti nella rubrica.
     */
    public Set<Contatto> getElenco() {
        return elenco;
    }

    /**
     * Restituisce l'elenco dei contatti preferiti.
     *
     * @return un oggetto RubricaPreferiti per gestire i contatti preferiti.
     */
    public RubricaPreferiti getElencoPreferiti() {
        return elencoPreferiti;
    }

    /**
     * Aggiunge un contatto all'elenco della rubrica. Se il contatto è già presente, non verrà aggiunto nuovamente.
     *
     * @param c il contatto da aggiungere.
     * @throws IllegalArgumentException se il contatto è null.
     */
    public void addContatto(Contatto c) {
        if (c == null) {
            throw new IllegalArgumentException("Il contatto non può essere null.");
        }
        elenco.add(c);
    }

    /**
     * Rimuove un contatto dall'elenco della rubrica.
     *
     * @param c il contatto da rimuovere.
     * @throws IllegalArgumentException se il contatto è null.
     */
    public void removeContatto(Contatto c) {
        if (c == null) {
            throw new IllegalArgumentException("Il contatto non può essere null.");
        }
        elenco.remove(c);
    }

    /**
     * Rimuove tutti i contatti dalla rubrica, svuotandola completamente.
     */
    public void resetRubrica() {
        elenco.clear();
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
        if (!elenco.contains(c)) {
            throw new IllegalArgumentException("Il contatto non è presente nella rubrica e non può essere aggiunto ai preferiti.");
        }
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
}
