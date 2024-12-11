package it.unisa.diem.mycontacts.datastructure;

import it.unisa.diem.mycontacts.data.Contatto;
import java.util.Set;
import java.util.TreeSet;
import javafx.collections.FXCollections;
import javafx.collections.ObservableSet;

/**
 * @class RubricaPreferiti
 * @brief Classe che rappresenta un contenitore per gestire i contatti preferiti in una rubrica.
 * @author Gruppo11
 * 
 * La classe utilizza un Set per garantire che i contatti siano univoci e ordinati.
 */
public class RubricaPreferiti {
    
    /**
     * @brief Set che contiene i contatti preferiti.
     * 
     * Utilizza un TreeSet per garantire l'ordinamento naturale dei contatti 
     * e l'assenza di duplicati. È avvolto in un ObservableSet per supportare il binding.
     */
    private ObservableSet<Contatto> elencoPreferiti;

    /**
     * @brief Costruttore della classe RubricaPreferiti.
     * 
     * Inizializza l'elenco dei contatti preferiti come un TreeSet, 
     * garantendo l'ordinamento naturale e l'unicità dei contatti.
     */
    public RubricaPreferiti() {
        this.elencoPreferiti = FXCollections.observableSet(new TreeSet<>());
    }

    /**
     * @brief Aggiunge un contatto alla lista dei preferiti.
     * 
     * @param c Il contatto da aggiungere.
     * @throw IllegalArgumentException Se il contatto passato è null.
     */
    public void addContattoPreferito(Contatto c) {
        if (c == null) {
            throw new IllegalArgumentException("Il contatto non può essere null.");
        }
        elencoPreferiti.add(c);
    }

    /**
     * @brief Rimuove un contatto dalla lista dei preferiti.
     * 
     * @param c Il contatto da rimuovere.
     * @throw IllegalArgumentException Se il contatto passato è null.
     */
    public void removeContattoPreferito(Contatto c) {
        if (c == null) {
            throw new IllegalArgumentException("Il contatto non può essere null.");
        }
        elencoPreferiti.remove(c);
    }

    /**
     * @brief Rimuove tutti i contatti dalla lista dei preferiti.
     * 
     * Svuota completamente l'elenco dei contatti preferiti.
     */
    public void resetRubricaPreferiti() {
        elencoPreferiti.clear();
    }

    /**
     * @brief Restituisce l'elenco dei contatti preferiti.
     * 
     * @return Un ObservableSet contenente tutti i contatti contrassegnati come preferiti.
     */
    public ObservableSet<Contatto> getElencoPreferiti() {
        return elencoPreferiti;
    }
}

