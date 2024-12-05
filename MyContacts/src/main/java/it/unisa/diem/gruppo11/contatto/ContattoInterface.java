/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package it.unisa.diem.gruppo11.contatto;

import java.util.List;

/**
 *
 * @author lupo
 */
public interface ContattoInterface {

    // Metodi per accedere e modificare le proprietà del contatto
    String getNome();
    void setNome(String nome);

    String getCognome();
    void setCognome(String cognome);

    List<Integer> getNumeri();
    void setNumeri(List<Integer> numeri);

    List<String> getEmail();
    void setEmail(List<String> email);

    boolean isPreferito();
    void setPreferito(boolean preferito);

    // Metodi per la gestione del contatto
    void modificaContatto(Contatto c);
    void switchPreferiti(Contatto c);
    boolean contattoValido(Contatto c);

}
