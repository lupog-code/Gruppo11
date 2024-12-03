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
public class Rubrica implements RubricaInterface {
    
    private Set<Contatto> elenco;
    private RubricaPreferiti elencoPreferiti;

    public Rubrica() {
        this.elenco = new TreeSet<>();
        this.elencoPreferiti = new RubricaPreferiti();
    }

    @Override
    public Set<Contatto> getElenco() {
        return elenco;
    }
    
    //getsire
    @Override
    public boolean addContatto(Contatto c) {
        elenco.add(c);
        
        if(c.isPreferito()) elencoPreferiti.addContattoPreferito(c);
        
        return true;
    }
    
    //gestire
    @Override
    public boolean removeContatto(Contatto c) {
        if(this.isRubricaVuota()) {
            return false;
        }
        
        if(c.isPreferito()) {
            System.out.println("avviso");
        }
        
        elenco.remove(c);
        elencoPreferiti.removeContattoPreferito(c);
        
        return true;
    }
    
    @Override
    public void resetRubrica() {
        elenco.clear();
        elencoPreferiti.getElencoPreferiti().clear();
    }
    
    @Override
    public boolean isRubricaVuota() {
        return elenco.isEmpty();
    }
    
    public boolean addContattoPreferito(Contatto c) {
        elencoPreferiti.addContattoPreferito(c);
        return true;
    }
    
    public boolean removeContattoPreferito(Contatto c) {
        elencoPreferiti.removeContattoPreferito(c);
        return true;
    }
    
    public Set<Contatto> getElencoPreferiti() {
        return elencoPreferiti.getElencoPreferiti();
    }

}
