package it.unisa.diem.mycontacts.data;

import it.unisa.diem.mycontacts.exceptions.InvalidContactException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;

/**
 * @class Contatto
 * @brief Classe che rappresenta un contatto, implementa l'interfaccia Comparable per permettere l'ordinamento.
 */
public class Contatto implements Comparable<Contatto> {

    private final String nome;  /**< Nome del contatto */
    private final String cognome;  /**< Cognome del contatto */
    private final Set<String> numeri;  /**< Set di numeri di telefono (può essere null) */
    private final Set<String> email;  /**< Set di indirizzi email (può essere null) */
    private boolean preferito;  /**< Stato di preferito del contatto */

    /**
     * @brief Costruttore per creare un contatto con tutti i suoi attributi.
     * 
     * @param nome Nome del contatto.
     * @param cognome Cognome del contatto.
     * @param numeri Set di numeri di telefono del contatto (può essere null).
     * @param email Set di indirizzi email del contatto (può essere null).
     * @param preferito Stato di preferito del contatto.
     * @throws it.unisa.diem.mycontacts.exceptions.InvalidContactException
     */
    public Contatto(String nome, String cognome, Set<String> numeri, Set<String> email, boolean preferito) throws InvalidContactException {
        if(nome.isEmpty() && cognome.isEmpty())
            throw new InvalidContactException();
        this.nome = nome;
        this.cognome = cognome;
        this.numeri = numeri != null ? numeri : new HashSet<>(); // Può essere vuota
        this.email = email != null ? email : new HashSet<>();   // Può essere vuota
        this.preferito = preferito;
    }

    /** @brief Getter per il nome del contatto.
     *  @return Il nome del contatto.
     */
    public String getNome() {
        return nome;
    }

    /** @brief Getter per il cognome del contatto.
     *  @return Il cognome del contatto.
     */
    public String getCognome() {
        return cognome;
    }

    /** @brief Getter per i numeri di telefono del contatto.
     *  @return Un set con i numeri di telefono o null.
     */
    public Set<String> getNumeri() {
        return numeri != null ? new HashSet<>(numeri) : new HashSet<>(); 
    }

    /** @brief Getter per gli indirizzi email del contatto.
     *  @return Un set con gli indirizzi email o null.
     */
    public Set<String> getEmail() {
        return email != null ? new HashSet<>(email) : new HashSet<>(); 
    }   

    /** @brief Getter per lo stato di preferito del contatto.
     *  @return true se il contatto è preferito, false altrimenti.
     */
    public boolean isPreferito() {
        return preferito;
    }

    /**
     * @brief Verifica l'uguaglianza tra due contatti.
     * Due contatti sono considerati uguali se hanno lo stesso nome, cognome,
     * numeri di telefono ed email.
     * 
     * @param obj Oggetto da confrontare.
     * @return true se i contatti sono uguali, false altrimenti.
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        Contatto other = (Contatto) obj;
        return Objects.equals(nome, other.nome) &&
               Objects.equals(cognome, other.cognome) &&
               Objects.equals(numeri, other.numeri) &&
               Objects.equals(email, other.email);
    }

    /**
     * @brief Calcola l'hash code del contatto.
     * 
     * @return Un valore hash basato sugli attributi del contatto.
     */
    @Override
    public int hashCode() {
        return Objects.hash(nome, cognome, numeri, email);
    }

    /**
     * @brief Confronta il contatto corrente con un altro per determinare l'ordinamento.
     * L'ordinamento è basato sul cognome e poi sul nome.
     * 
     * @param o Contatto da confrontare.
     * @return Un valore negativo, zero o positivo a seconda dell'ordinamento.
     */
    @Override
    public int compareTo(Contatto o) {
        int result = this.cognome.compareTo(o.cognome);
        if (result != 0) {
            return result;
        }
        return this.nome.compareTo(o.nome);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(nome).append(",");
        sb.append(cognome).append(",");

        // Aggiunge numeri (se presenti).
        List<String> numeriList = new ArrayList<>(numeri);
        for (int i = 0; i < 3; i++) {
            sb.append(i < numeriList.size() ? numeriList.get(i) : "").append(",");
        }

        // Aggiunge email (se presenti).
        List<String> emailList = new ArrayList<>(email);
        for (int i = 0; i < 3; i++) {
            sb.append(i < emailList.size() ? emailList.get(i) : "").append(",");
        }

        // Aggiunge il flag preferito.
        sb.append(preferito);
        
        return sb.toString();
    }
    
}