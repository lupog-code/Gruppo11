/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package it.unisa.diem.gruppo11.rubrica;

import it.unisa.diem.gruppo11.contatto.Contatto;
import java.util.Set;
import java.util.TreeSet;

/**
 *
 * @author lupo
 */
public class Rubrica {
    
    private Set<Contatto> elenco;
    private Set<Contatto> elencoPreferiti;

    public Rubrica() {
        elenco = new TreeSet<>();
        elencoPreferiti = new TreeSet<>();
    }

    public Set<Contatto> getElenco() {
        return elenco;
    }
    
    public Set<Contatto> getElencoPreferiti() {
        return elencoPreferiti;
    }
    
    //getsire
    public boolean addContatto(Contatto c) {
        elenco.add(c);
        
        if(c.isPreferito()) elencoPreferiti.add(c);
        
        return true;
    }
    
    //gestire
    public boolean removeContatto(Contatto c) {
        if(this.rubricaVuota()) {
            return false;
        }
        
        if(c.isPreferito()) {
            System.out.println("avviso");
        }
        
        elenco.remove(c);
        elencoPreferiti.remove(c);
        return true;
    }
    
    public boolean addContattoPreferito(Contatto c) {
        elencoPreferiti.add(c);
        return true;
    }
    
    public boolean removeContattoPreferito(Contatto c) {
        elencoPreferiti.remove(c);
        return true;
    }
    
    public boolean rubricaVuota() {
        return elenco.isEmpty();
    }
    
    public void resetRubrica() {
        elenco.removeAll(elenco);
        elencoPreferiti.removeAll(elencoPreferiti);
    }
    
}
