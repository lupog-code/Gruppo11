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
public abstract class Rubrica {
    
    private Set<Contatto> elenco;

    public Rubrica() {
        elenco = new TreeSet<>();
    }
    
    public abstract void addContatto(Contatto c);
    
    public abstract Contatto removeContatto(Contatto c);
    
    public boolean rubricaVuota() {
        return elenco.isEmpty();
    }
    
}
