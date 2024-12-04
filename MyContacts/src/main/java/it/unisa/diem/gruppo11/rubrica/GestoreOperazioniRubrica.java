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
public class GestoreOperazioniRubrica {
    
    private Rubrica rubrica;

    public GestoreOperazioniRubrica(Rubrica rubrica) {
        this.rubrica = rubrica;
    }
    
    public Set<Contatto> ricercaContatti(String text) {
        Set<Contatto> risultati = new TreeSet<>();
        
        if(text == null || text.isEmpty()) return rubrica.getElenco();
        
        for(Contatto c : rubrica.getElenco()) {
            if(c.getCognome().toLowerCase().startsWith(text.toLowerCase()) || c.getNome().toLowerCase().startsWith(text.toLowerCase())) {
                risultati.add(c);
            }
        }
        
        return risultati;
    }
    
    public boolean aggiungiContatto(Contatto c) {
        if(!c.contattoValido(c))
            return false;
        
        rubrica.addContatto(c);
        
        if(c.isPreferito()) rubrica.getElencoPreferiti().addContattoPreferito(c);
        
        return true;
    }
    
    public boolean rimuoviContatto(Contatto c) {
        if(rubrica.isRubricaVuota()) {
            return false;
        }
        
        if(c.isPreferito()) {
            //cambiare
            System.out.println("Sicuro?");
        }
        
        rubrica.getElenco().remove(c);
        
        if(c.isPreferito()) rubrica.getElencoPreferiti().removeContattoPreferito(c);
        
        return true;
    }
    
    public void resetTotale() {
        rubrica.resetRubrica();
        rubrica.getElencoPreferiti().resetRubricaPreferiti();
    }
    
    public void salvaRubrica(String nomefile) throws IOException {
        
    }
    
    public static void leggiRubrica(String nomefile) throws IOException {
        
    }
    
}
