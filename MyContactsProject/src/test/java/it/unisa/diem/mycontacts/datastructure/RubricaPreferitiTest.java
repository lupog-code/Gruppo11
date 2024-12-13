/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package it.unisa.diem.mycontacts.datastructure;

import it.unisa.diem.mycontacts.data.Contatto;
import it.unisa.diem.mycontacts.exceptions.InvalidContactException;
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
    
    private RubricaPreferiti rubrica;
    private Contatto c1;
    private Contatto c2;
    
    @BeforeEach
    public void setUp() throws InvalidContactException {
        rubrica = new RubricaPreferiti();
        
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
        
        rubrica.addContattoPreferito(c1);
        rubrica.addContattoPreferito(c2);

        assertFalse(rubrica.getElencoPreferiti().contains(c1));
        assertTrue(rubrica.getElencoPreferiti().contains(c2));
    }

    /**
     * Test of removeContattoPreferito method, of class RubricaPreferiti.
     */
    @Test
    public void testRemoveContattoPreferito() {
        System.out.println("removeContattoPreferito");
        
        rubrica.addContattoPreferito(c2);
        assertTrue(rubrica.getElencoPreferiti().contains(c2));

        rubrica.removeContattoPreferito(c2);
       
        assertFalse(rubrica.getElencoPreferiti().contains(c2));
    }

    /**
     * Test of resetRubricaPreferiti method, of class RubricaPreferiti.
     */
    @Test
    public void testResetRubricaPreferiti() {
        System.out.println("resetRubricaPreferiti");
        
        rubrica.addContattoPreferito(c1);
        rubrica.resetRubricaPreferiti();

        assertTrue(rubrica.getElencoPreferiti().isEmpty());
    }

    /**
     * Test of getElencoPreferiti method, of class RubricaPreferiti.
     */
    @Test
    public void testGetElencoPreferiti() {
        System.out.println("getElencoPreferiti");
       
        rubrica.addContattoPreferito(c1);
        rubrica.addContattoPreferito(c2);
        ObservableSet<Contatto> result = rubrica.getElencoPreferiti();
        
        assertEquals(result.size(), 1);
        assertTrue(rubrica.getElencoPreferiti().contains(c2));
        assertFalse(rubrica.getElencoPreferiti().contains(c1));
    }

    /**
     * Test of ricercaContattiPreferiti method, of class RubricaPreferiti.
     */
    @Test
    public void testRicercaContattiPreferiti() {
        System.out.println("ricercaContattiPreferiti");
        
        rubrica.addContattoPreferito(c1);
        rubrica.addContattoPreferito(c2);

        ObservableSet<Contatto> risultati; 
        
        risultati = rubrica.ricercaContattiPreferiti("Rossi");
        //in questo caso addContattoPreferiti è assoluto, cioè aggiunge il contatto alla lista preferiti a prescindere dal flag preferito. Il controllo del flag è effetuato dal metodo aggiungiContatto
        assertEquals(0, risultati.size());
        assertFalse(risultati.contains(c1));
        assertFalse(risultati.contains(c2));
        
        risultati = rubrica.ricercaContattiPreferiti("Umberto");
        assertEquals(1, risultati.size());
        assertTrue(risultati.contains(c2));
        assertFalse(risultati.contains(c1));

        risultati = rubrica.ricercaContattiPreferiti("");
        assertEquals(1, risultati.size());
        assertTrue(risultati.contains(c2));
        assertFalse(risultati.contains(c1));
    }
    
}
