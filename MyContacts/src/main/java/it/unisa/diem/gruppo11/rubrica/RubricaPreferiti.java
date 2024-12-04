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
public class RubricaPreferiti {
    
    private final Set<Contatto> elencoPreferiti;

    public RubricaPreferiti() {
        this.elencoPreferiti = new TreeSet<>();
    }

    public boolean addContattoPreferito(Contatto c) {
        return elencoPreferiti.add(c);
    }

    public boolean removeContattoPreferito(Contatto c) {
        return elencoPreferiti.remove(c);
    }
    
    public void resetRubricaPreferiti() {
        elencoPreferiti.clear();
    }

    public Set<Contatto> getElencoPreferiti() {
        return elencoPreferiti;
    }
    
}
