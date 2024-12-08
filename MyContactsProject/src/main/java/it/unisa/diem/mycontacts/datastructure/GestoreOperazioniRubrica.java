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

/**
 * La classe GestoreOperazioniRubrica implementa le operazioni per gestire una rubrica.
 * Consente di cercare, aggiungere, rimuovere e gestire contatti, nonché di resettare l'intera rubrica.
 * Implementa l'interfaccia OperazioniRubricaInterface.
 */
public class GestoreOperazioniRubrica {
    
    // Riferimento alla rubrica che gestisce.
    private Rubrica rubrica;

    /**
     * Costruttore della classe GestoreOperazioniRubrica.
     * 
     * @param rubrica la rubrica da gestire.
     */
    public GestoreOperazioniRubrica(Rubrica rubrica) {
        this.rubrica = rubrica;
    }

    /**
     * Ricerca contatti nella rubrica in base a un testo specificato.
     * La ricerca avviene confrontando il testo con il cognome e il nome dei contatti.
     * 
     * @param text il testo di ricerca.
     * @return un Set di contatti che corrispondono alla ricerca.
     */
    public Set<Contatto> ricercaContatti(String text) {
        Set<Contatto> risultati = new TreeSet<>();

        // Restituisce l'intera rubrica se il testo è nullo o vuoto.
        if (text == null || text.isEmpty()) return rubrica.getElenco();

        // Aggiunge i contatti che corrispondono alla ricerca.
        for (Contatto c : rubrica.getElenco()) {
            if (c.getCognome().toLowerCase().startsWith(text.toLowerCase()) || 
                c.getNome().toLowerCase().startsWith(text.toLowerCase())) {
                risultati.add(c);
            }
        }

        return risultati;
    }

    /**
     * Aggiunge un nuovo contatto alla rubrica.
     * Se il contatto è valido, viene aggiunto anche alla lista dei preferiti se contrassegnato come tale.
     * 
     * @param c il contatto da aggiungere.
     * @return true se il contatto è stato aggiunto con successo, false se non valido.
     */
    public boolean aggiungiContatto(Contatto c) {
        // Verifica la validità del contatto.
        if (!c.contattoValido()) {
            return false;
        }

        // Aggiunge il contatto alla rubrica.
        rubrica.addContatto(c);

        // Aggiunge il contatto ai preferiti se è contrassegnato come preferito.
        if (c.isPreferito()) {
            rubrica.getElencoPreferiti().addContattoPreferito(c);
        }

        return true;
    }

    /**
     * Rimuove un contatto dalla rubrica.
     * Se il contatto è tra i preferiti, viene rimosso anche da questa lista.
     * 
     * @param c il contatto da rimuovere.
     * @return true se il contatto è stato rimosso con successo, false se la rubrica è vuota o il contatto non esiste.
     */
    public boolean rimuoviContatto(Contatto c) {
        // Verifica se la rubrica è vuota.
        if (rubrica.isRubricaVuota()) {
            return false;
        }

        // Rimuove il contatto dall'elenco principale.
        boolean rimosso = rubrica.getElenco().remove(c);

        // Rimuove il contatto dalla lista dei preferiti se esiste.
        if (c.isPreferito()) {
            rubrica.getElencoPreferiti().removeContattoPreferito(c);
        }

        return rimosso;
    }

    /**
     * Resetta l'intera rubrica, eliminando tutti i contatti e i preferiti.
     */
    public void resetTotale() {
        // Svuota l'elenco dei contatti e dei preferiti.
        rubrica.resetRubrica();
        rubrica.getElencoPreferiti().resetRubricaPreferiti();
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

    /**
     * Importa una rubrica da un file specificato.
     * Questo metodo deve essere implementato per leggere i dati da un file.
     * 
     * @param nomefile il nome del file da cui importare la rubrica.
     * @throws IOException se si verifica un errore durante l'importazione.
     */
    public static void importaRubrica(String nomefile) throws IOException {
        // Implementazione da completare per leggere e importare la rubrica da un file.
        throw new UnsupportedOperationException("Metodo non implementato.");
    }
}
