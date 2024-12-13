/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package it.unisa.diem.mycontacts.datastructure;

import it.unisa.diem.mycontacts.data.Contatto;
import java.util.HashSet;
import java.util.Set;
import javafx.collections.ObservableSet;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 *
 * @author lupo
 */
public class RubricaPreferitiTest {
    
    private Rubrica rubrica;
    private Contatto c1;
    private Contatto c2;
    
    @BeforeEach
    public void setUp() {
        rubrica = new Rubrica();
        
        Set<String> numeri = new HashSet<>();
        numeri.add("3331234567");
        numeri.add("0817654321");
        numeri.add("4569868424");

        Set<String> email = new HashSet<>();
        email.add("mario.rossi@example.com");
        email.add("rossimario@gmail.com");
        email.add("tonyeffe@unisa.it");
        
        c1 = new Contatto("Mario", "Rossi", numeri, email, false);
        
        Set<String> numeri2 = new HashSet<>();
        numeri.add("2221234567");
        numeri.add("1237654321");
        numeri.add("7779868424");
        
        Set<String> email2 = new HashSet<>();
        email.add("umberto-zorro@example.com");
        email.add("adelaide@gmail.com");
        email.add("jAx@unisa.it");
         
        c2 = new Contatto("Umberto","Zorro",numeri2,email2,true);
    }
    
    /**
     * Test of addContattoPreferito method, of class RubricaPreferiti.
     */
    @Test
    public void testAddContattoPreferito() {
        System.out.println("addContattoPreferito");
        
        rubrica.aggiungiContatto(c1);
        rubrica.aggiungiContatto(c2);

        assertFalse(rubrica.getElencoPreferiti().contains(c1));
        assertTrue(rubrica.getElencoPreferiti().contains(c2));

    }

    /**
     * Test of removeContattoPreferito method, of class RubricaPreferiti.
     */
    @Test
    public void testRemoveContattoPreferito() {
        System.out.println("removeContattoPreferito");
        
        rubrica.aggiungiContatto(c2);
        assertTrue(rubrica.getElencoPreferiti().contains(c2));

        rubrica.rimuoviContatto(c2);
        
        assertFalse(rubrica.getElencoPreferiti().contains(c2));
    }

    /**
     * Test of resetRubricaPreferiti method, of class RubricaPreferiti.
     */
    @Test
    public void testResetRubricaPreferiti() {
        System.out.println("resetRubricaPreferiti");
        
        rubrica.aggiungiContatto(c2);
        assertTrue(rubrica.getElencoPreferiti().contains(c2));

        rubrica.resetRubrica();

        assertTrue(rubrica.getElencoPreferiti().isEmpty());
    }

    /**
     * Test of getElencoPreferiti method, of class RubricaPreferiti.
     */
    @Test
    public void testGetElencoPreferiti() {
        System.out.println("getElencoPreferiti");
       
        rubrica.aggiungiContatto(c1);
        rubrica.aggiungiContatto(c2); 

        ObservableSet<Contatto> result = rubrica.getElencoPreferiti();
        
        assertEquals(result.size(), 1);
        assertFalse(rubrica.getElencoPreferiti().contains(c1));
        assertTrue(rubrica.getElencoPreferiti().contains(c2));
    }

    /**
     * Test of ricercaContattiPreferiti method, of class RubricaPreferiti.
     */
    @Test
    public void testRicercaContattiPreferiti() {
        System.out.println("ricercaContattiPreferiti");
        
        rubrica.aggiungiContatto(c1);
        rubrica.aggiungiContatto(c2);

        ObservableSet<Contatto> risultati; 
        
        risultati = rubrica.ricercaPreferiti("Rossi");
        assertEquals(0, risultati.size());
        assertFalse(risultati.contains(c1));
        assertFalse(risultati.contains(c2));
        
        risultati = rubrica.ricercaPreferiti("Umberto");
        assertEquals(1, risultati.size());
        assertTrue(risultati.contains(c2));
        assertFalse(risultati.contains(c1));

        risultati = rubrica.ricercaPreferiti("");
        assertEquals(1, risultati.size());
        assertTrue(risultati.contains(c2));
        assertFalse(risultati.contains(c1));
    }
    
}
