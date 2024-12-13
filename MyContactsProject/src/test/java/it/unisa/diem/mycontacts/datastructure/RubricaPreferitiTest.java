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
    
    private RubricaPreferiti rubrica;
    private Contatto contatto1;
    private Contatto contatto2;
    
    @BeforeEach
    public void setUp() {
        rubrica = new RubricaPreferiti();
        System.out.println("Creo rubrica");
        
        Set<String> numeri1 = new HashSet<>();
        numeri1.add("3331234567");
        Set<String> email1 = new HashSet<>();
        email1.add("mario.rossi@example.com");
        contatto1 = new Contatto("Mario", "Rossi", numeri1, email1, false);

        Set<String> numeri2 = new HashSet<>();
        numeri2.add("0817654321");
        Set<String> email2 = new HashSet<>();
        email2.add("luigi.verdi@example.com");
        contatto2 = new Contatto("Luigi", "Verdi", numeri2, email2, false);
    }

    /**
     * Test of addContattoPreferito method, of class RubricaPreferiti.
     */
    @Test
    public void testAddContattoPreferito() {
        System.out.println("addContattoPreferito");
   
        rubrica.addContattoPreferito(contatto1);

        assertTrue(rubrica.getElencoPreferiti().contains(contatto1));
    }

    /**
     * Test of removeContattoPreferito method, of class RubricaPreferiti.
     */
    @Test
    public void testRemoveContattoPreferito() {
        System.out.println("removeContattoPreferito");
        
        rubrica.addContattoPreferito(contatto1);
        rubrica.removeContattoPreferito(contatto1);
        
        assertFalse(rubrica.getElencoPreferiti().contains(contatto1));
    
    }

    /**
     * Test of resetRubricaPreferiti method, of class RubricaPreferiti.
     */
    @Test
    public void testResetRubricaPreferiti() {
        System.out.println("resetRubricaPreferiti");
        
        rubrica.addContattoPreferito(contatto1);
        rubrica.resetRubricaPreferiti();
        
        assertTrue(rubrica.getElencoPreferiti().isEmpty());
    }

    /**
     * Test of getElencoPreferiti method, of class RubricaPreferiti.
     */
    @Test
    public void testGetElencoPreferiti() {
        System.out.println("getElencoPreferiti");
        
        rubrica.addContattoPreferito(contatto1);
        ObservableSet<Contatto> expResult = rubrica.getElencoPreferiti();
        
        assertEquals(1,expResult.size());
        assertTrue(expResult.contains(contatto1));
    }
    
}
