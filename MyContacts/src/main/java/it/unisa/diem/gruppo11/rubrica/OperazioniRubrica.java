/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package it.unisa.diem.gruppo11.rubrica;

import it.unisa.diem.gruppo11.contatto.Contatto;
import java.io.IOException;
import java.util.Set;
import java.util.TreeSet;

/**
 *
 * @author lupo
 */
public class OperazioniRubrica {
    
    public boolean aggiungiContatto(Contatto c) {
        if(c.getNome() == null || c.getCognome() == null) {
            System.out.printf("Avviso");
            return false;
        }
        
        if(c.getNumeri().length==0) {
            //controllo numeri
            return false;
        }
        
        if(c.getEmail().length==0) {
            //controllo email
            return false;
        }
        
        //
        return true;
    }
    
    public Set ricercaContatto(String text) {
        Set<Contatto> results = null;
        return results;
    }
    
    public void salvaRubrica(String nomefile) throws IOException {
        
    }
    
    public static void leggiRubrica(String nomefile) throws IOException {
        
    }
    
}
