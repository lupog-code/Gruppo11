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
     * @throws IllegalArgumentException Se il contatto è null, viene lanciata un'eccezione.
     */
    public void addContattoPreferito(Contatto c) {
        // Verifica che il contatto non sia null.
        if (c == null) {
            throw new IllegalArgumentException("Il contatto non può essere null.");
        }
        elencoPreferiti.add(c); // Aggiunge il contatto ai preferiti.
    }

    /**
     * @brief Rimuove un contatto dalla lista dei preferiti.
     * 
     * @param c Il contatto da rimuovere dalla lista dei preferiti.
     * @throws IllegalArgumentException Se il contatto è null, viene lanciata un'eccezione.
     */
    public void removeContattoPreferito(Contatto c) {
        // Verifica che il contatto non sia null.
        if (c == null) {
            throw new IllegalArgumentException("Il contatto non può essere null.");
        }
        elencoPreferiti.remove(c); // Rimuove il contatto dai preferiti.
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
}
