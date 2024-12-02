/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package it.unisa.diem.gruppo11.rubrica;

import it.unisa.diem.gruppo11.contatto.Contatto;
import java.util.Objects;
import java.util.Set;
import java.util.TreeSet;

/**
 *
 * @author lupo
 */
public class RubricaCompleta {
    
    private Set<Contatto> elenco;

    public RubricaCompleta() {
        elenco = new TreeSet<>();
    }
    
    public void addContatto(Contatto c) {
        elenco.add(c);
    }
    
    public Contatto removeContatto(Contatto c) {
        //possibile modifica
        elenco.remove(c);
        return c;
    }
    
    public boolean rubricaVuota() {
        return elenco.isEmpty();
    }
    
}
