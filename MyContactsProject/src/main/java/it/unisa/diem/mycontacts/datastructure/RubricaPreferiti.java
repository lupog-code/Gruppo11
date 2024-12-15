package it.unisa.diem.mycontacts.datastructure;

import it.unisa.diem.mycontacts.data.Contatto;
import java.util.TreeSet;
import javafx.collections.FXCollections;
import javafx.collections.ObservableSet;

/**
 * @class RubricaPreferiti
 * @brief La classe RubricaPreferiti gestisce i contatti preferiti all'interno di una rubrica.
 *        Utilizza un Set per garantire che i contatti siano univoci e ordinati in modo naturale.
 */
public class RubricaPreferiti {
    
    // Set che contiene i contatti preferiti, ordinato e senza duplicati.
    private ObservableSet<Contatto> elencoPreferiti;

    /**
     * @brief Costruttore della classe RubricaPreferiti.
     *        Inizializza l'elenco dei contatti preferiti come un TreeSet, che garantisce l'ordinamento naturale dei contatti.
     */
    public RubricaPreferiti() {
        this.elencoPreferiti = FXCollections.observableSet(new TreeSet<>()); // TreeSet mantiene l'ordine naturale e l'unicità dei contatti.
    }

    /**
     * @brief Aggiunge un contatto alla lista dei preferiti.
     * 
     * @param c Il contatto da aggiungere alla lista dei preferiti.
     */
    public void addContattoPreferito(Contatto c) {
        if(c.isPreferito()){
            elencoPreferiti.add(c); // Aggiunge il contatto ai preferiti.
        }
    }

    /**
     * @brief Rimuove un contatto dalla lista dei preferiti.
     * 
     * @param c Il contatto da rimuovere dalla lista dei preferiti.
     */
    public void removeContattoPreferito(Contatto c) {
        if(c.isPreferito()){
            elencoPreferiti.remove(c); // Rimuove il contatto dai preferiti.
        }
    }

    /**
     * @brief Rimuove tutti i contatti dalla lista dei preferiti, svuotando l'elenco.
     */
    public void resetRubricaPreferiti() {
        elencoPreferiti.clear(); // Svuota la lista dei preferiti.
    }

    /**
     * @brief Restituisce l'elenco dei contatti preferiti.
     * 
     * @return Un Set contenente tutti i contatti preferiti presenti nella rubrica.
     */
    public ObservableSet<Contatto> getElencoPreferiti() {
        return elencoPreferiti; // Restituisce l'elenco dei contatti preferiti.
    }
    
    /**
     * @brief Esegue una ricerca dei contatti preferiti in base al testo inserito.
     * La ricerca avviene per cognome o nome.
     * 
     * @param text Testo da ricercare.
     * @return un Set contenente i contatti preferiti che corrispondono alla ricerca.
     */
    public ObservableSet<Contatto> ricercaContattiPreferiti(String text) {
        ObservableSet<Contatto> risultati = FXCollections.observableSet(new TreeSet<>());

        // Restituisce l'intera rubrica se il testo di ricerca è nullo o vuoto.
        if (text == null || text.isEmpty()) return elencoPreferiti;

        // Aggiunge i contatti che corrispondono alla ricerca.
        for (Contatto c : elencoPreferiti) {
            if (c.getCognome().toLowerCase().startsWith(text.toLowerCase()) || 
                c.getNome().toLowerCase().startsWith(text.toLowerCase())) {
                risultati.add(c);
            }
        }

        return risultati;
    }
}
