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
    private RubricaPreferiti elencoPreferiti;

    public Rubrica() {
        this.elenco = new TreeSet<>();
        this.elencoPreferiti = new RubricaPreferiti();
    }

    public Set<Contatto> getElenco() {
        return elenco;
    }
    
    public RubricaPreferiti getElencoPreferiti() {
        return elencoPreferiti;
    }
    
    //getsire
    public boolean addContatto(Contatto c) {
        elenco.add(c);
        
        return true;
    }
    
    //gestire
    public boolean removeContatto(Contatto c) {
        elenco.remove(c);
        
        return true;
    }
    
    public void resetRubrica() {
        elenco.clear();
    }
    
    public boolean isRubricaVuota() {
        return elenco.isEmpty();
    }

}
