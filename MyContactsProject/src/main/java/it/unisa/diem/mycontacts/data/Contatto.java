/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package it.unisa.diem.mycontacts.data;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

/**
 * Classe che rappresenta un contatto con nome, cognome, numeri di telefono,
 * indirizzi email e uno stato di preferito. Implementa l'interfaccia Comparable
 * per consentire il confronto tra contatti.
 */
public class Contatto implements Comparable<Contatto> {

    private String nome;  // Nome del contatto
    private String cognome;  // Cognome del contatto
    private Set<String> numeri;  // Set di numeri di telefono del contatto (senza duplicati e ignorando l'ordine)
    private Set<String> email;  // Set di indirizzi email del contatto (senza duplicati e ignorando l'ordine)
    private boolean preferito;  // Stato che indica se il contatto è preferito

    /**
     * Costruttore per creare un contatto con tutti i suoi attributi.
     *
     * @param nome Nome del contatto
     * @param cognome Cognome del contatto
     * @param numeri Set di numeri di telefono
     * @param email Set di indirizzi email
     * @param preferito Stato di preferito del contatto
     */
    public Contatto(String nome, String cognome, Set<String> numeri, Set<String> email, boolean preferito) {
        this.nome = nome;
        this.cognome = cognome;
        this.numeri = new HashSet<>(numeri); 
        this.email = new HashSet<>(email);    
        this.preferito = preferito;
    }

    // Getter e setter per nome e cognome
    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCognome() {
        return cognome;
    }

    public void setCognome(String cognome) {
        this.cognome = cognome;
    }

    // Getter e setter per numeri di telefono
    public Set<String> getNumeri() {
        return new HashSet<>(numeri);  // Restituisce una copia per garantire la sicurezza
    }

    public void setNumeri(Set<String> numeri) {
        this.numeri = new HashSet<>(numeri);  // Creazione di una copia per garantire l'immutabilità
    }

    // Getter e setter per email
    public Set<String> getEmail() {
        return new HashSet<>(email);  // Restituisce una copia per garantire la sicurezza
    }

    public void setEmail(Set<String> email) {
        this.email = new HashSet<>(email);  // Creazione di una copia per garantire l'immutabilità
    }

    // Getter e setter per lo stato di preferito
    public boolean isPreferito() {
        return preferito;
    }

    public void setPreferito(boolean preferito) {
        this.preferito = preferito;
    }

    /**
     * Confronta il contatto corrente con un altro per determinarne l'uguaglianza.
     * Due contatti sono considerati uguali se hanno lo stesso nome, cognome, 
     * numeri di telefono e email (senza considerare l'ordine).
     *
     * @param obj Oggetto da confrontare
     * @return true se i contatti sono uguali, false altrimenti
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
        
        // Confronta nome, cognome, numeri e email
        return Objects.equals(nome, other.nome) &&
               Objects.equals(cognome, other.cognome) &&
               numeri.equals(other.numeri) &&
               email.equals(other.email);
    }

    /**
     * Calcola l'hash code del contatto.
     * L'hash code è basato sugli attributi del contatto(senza preferito) per garantire
     * un comportamento corretto nelle strutture dati come HashSet e HashMap.
     *
     * @return L'hash code del contatto
     */
    @Override
    public int hashCode() {
        return Objects.hash(nome, cognome, numeri, email);
    }

    /**
     * Confronta il contatto corrente con un altro per determinarne l'ordinamento.
     * L'ordinamento è basato sul cognome e poi sul nome.
     *
     * @param o Contatto da confrontare
     * @return un valore negativo, zero o positivo a seconda dell'ordinamento
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
     * Modifica i dettagli di un contatto esistente con i dati di un altro contatto.
     *
     * @param c Contatto da cui copiare i dettagli
     */
    public void modificaContatto(Contatto c) {
        this.nome = c.nome;
        this.cognome = c.cognome;
        this.numeri = new HashSet<>(c.numeri);
        this.email = new HashSet<>(c.email);
        this.preferito = c.preferito;
    }

    /**
     * Inverte lo stato di preferito di un contatto.
     */
    public void switchPreferiti() {
        this.preferito = !(this.preferito); 
    }

    /**
     * Verifica se un contatto è valido.
     * Un contatto è considerato valido se ha un nome o un cognome non vuoti,
     * e se i numeri di telefono e le email sono conformi a specifiche condizioni.
     *
     * @return true se il contatto è valido, false altrimenti
     */
    public boolean contattoValido() {
        // Verifica che il contatto abbia almeno un nome o un cognome non vuoto
        if (cognome.isEmpty() && nome.isEmpty()) {
            return false;
        }

        /*
        if(!numeri.isEmpty()) {
            // Verifica la lunghezza dei numeri di telefono (tra 3 e 10 cifre)
            for (String numero : numeri) {
                if (numero.length() > 10 || numero.length() < 3) {
                    return false;  // I numeri devono avere tra 3 e 10 cifre
                }
            }
        }

        if(!email.isEmpty()) {
            // Verifica la validità degli indirizzi email (struttura corretta e nessun spazio)
            for (String mail : email) {
                if (!mail.matches("^[a-zA-Z0-9](\\.?[a-zA-Z0-9])*@[a-zA-Z0-9]+\\.[a-zA-Z]{2,3}$") || mail.contains(" ")) {
                    return false;  // Le email devono avere una struttura valida e non devono contenere spazi
                }
            }
        }*/

        return true;
    }
}

