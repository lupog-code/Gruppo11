/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package it.unisa.diem.gruppo11.contatto;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 *
 * @author lupo
 */
public class Contatto implements Comparable<Contatto>, ContattoInterface {

    private String nome;
    private String cognome;
    private List<Integer> numeri;
    private List<String> email;
    private boolean preferito;

    public Contatto(String nome, String cognome, List<Integer> numeri, List<String> email, boolean preferito) {
        this.nome = nome;
        this.cognome = cognome;
        this.numeri = new ArrayList<>();
        this.email = new ArrayList<>();
        this.preferito = preferito;
    }

    public String getNome() {
        return nome;
    }

    public String getCognome() {
        return cognome;
    }

    public List<Integer> getNumeri() {
        return numeri;
    }

    public List<String> getEmail() {
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
    public void setNumeri(List<Integer> numeri) {
        this.numeri = numeri;
    }
    
    //gestire
    public void setEmail(List<String> email) {
        this.email = email;
    }

    public void setPreferito(boolean preferito) {
        this.preferito = preferito;
    }

    @Override
    public int compareTo(Contatto o) {
        int i = this.cognome.compareTo(o.getCognome());
        
        return (i != 0) ? i : this.nome.compareTo(o.getCognome());
        
    }

    //modificare
    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Contatto other = (Contatto) obj;
        
        if (!Objects.equals(this.nome, other.nome)) {
            return false;
        }
        if (!Objects.equals(this.cognome, other.cognome)) {
            return false;
        }
        if (!Objects.equals(this.numeri, other.numeri)) {
            return false;
        }
        if (!Objects.equals(this.email, other.email)) {
            return false;
        }
        return true;
    }

    @Override
    public void modificaContatto(Contatto c) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void switchPreferiti(Contatto c) {
        if(c.isPreferito()) {
            c.setPreferito(false);
        } else {
            c.setPreferito(true);
        }
    }

    @Override
    public boolean contattoValido(Contatto c) {
        if(c.getCognome().isEmpty() && c.getNome().isEmpty())
            return false;
        
        for(Integer numero : c.getNumeri()) {
            if(numero.toString().length() > 10 || numero.toString().length() < 3)
                return false;
        }
        
        for(String email : c.getEmail()) {
            if(!email.matches("^[a-zA-Z0-9](\\.?[a-zA-Z0-9])*@[a-zA-Z0-9]+\\.[a-zA-Z]{2,3}$") || email.contains(" "))
                return false;
        }
        
        return true;
    }
    
}
