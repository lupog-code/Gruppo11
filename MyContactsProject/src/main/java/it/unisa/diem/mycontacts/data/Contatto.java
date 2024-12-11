package it.unisa.diem.mycontacts.data;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

/**
 * @class Contatto
 * @brief Classe che rappresenta un contatto, implementa l'interfaccia Comparable per permettere l'ordinamento.
 * @author Gruppo11
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
     */
    public Contatto(String nome, String cognome, Set<String> numeri, Set<String> email, boolean preferito) {
        this.nome = nome;
        this.cognome = cognome;
        this.numeri = numeri; // Può essere null
        this.email = email;   // Può essere null
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
        return numeri != null ? new HashSet<>(numeri) : null;
    }

    /** @brief Getter per gli indirizzi email del contatto.
     *  @return Un set con gli indirizzi email o null.
     */
    public Set<String> getEmail() {
        return email != null ? new HashSet<>(email) : null;
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

    /**
     * @brief Modifica i dettagli di un contatto esistente con i dati di un altro contatto.
     * 
     * @param c Contatto da cui copiare i dettagli.
     */
    public void modificaContatto(Contatto c) {
        if (c == null) {
            throw new IllegalArgumentException("Il contatto passato è null");
        }
        this.numeri.clear();
        if (c.numeri != null) {
            this.numeri.addAll(c.numeri);
        }
        this.email.clear();
        if (c.email != null) {
            this.email.addAll(c.email);
        }
        this.preferito = c.preferito;
    }

    /**
     * @brief Inverte lo stato di preferito di un contatto.
     */
    public void switchPreferiti() {
        this.preferito = !this.preferito; 
    }

    /**
     * @brief Verifica se un contatto è valido.
     * Un contatto è considerato valido se ha un nome o un cognome non vuoti.
     * 
     * @return true se il contatto è valido, false altrimenti.
     */
    public boolean contattoValido() {
        return !(cognome == null || cognome.isEmpty()) || !(nome == null || nome.isEmpty());
    }
}