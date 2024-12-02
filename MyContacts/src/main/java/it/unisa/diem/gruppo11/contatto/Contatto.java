/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package it.unisa.diem.gruppo11.contatto;

/**
 *
 * @author lupo
 */
public class Contatto {

    private String nome;
    private String cognome;
    private int numeri[];
    private String email[];
    private boolean preferito;

    public Contatto(String nome, String cognome, int[] numeri, String[] email, boolean preferito) {
        this.nome = nome;
        this.cognome = cognome;
        this.numeri = new int[3];
        this.email = new String[3];
        this.preferito = preferito;
    }

    public String getNome() {
        return nome;
    }

    public String getCognome() {
        return cognome;
    }

    public int[] getNumeri() {
        return numeri;
    }

    public String[] getEmail() {
        return email;
    }

    public boolean isPreferito() {
        return preferito;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setCognome(String cognome) {
        this.cognome = cognome;
    }

    //gestire
    public void setNumeri(int[] numeri) {
        this.numeri = numeri;
    }

    public void setEmail(String[] email) {
        this.email = email;
    }

    public void setPreferito(boolean preferito) {
        this.preferito = preferito;
    }
    
}
