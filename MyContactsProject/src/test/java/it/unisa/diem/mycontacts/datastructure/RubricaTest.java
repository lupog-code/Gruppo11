/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package it.unisa.diem.mycontacts.datastructure;

import it.unisa.diem.mycontacts.data.Contatto;
import java.io.File;
import java.io.FileWriter;
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
 * @author gianm
 */
public class RubricaTest {
    
    private Contatto c1;
    private Contatto c2;
    private Rubrica rubrica;
    
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
     * Test of getElenco method, of class Rubrica.
     */
    @Test
    public void testGetElenco() {
  
        rubrica.aggiungiContatto(c1);
        rubrica.aggiungiContatto(c2);

        ObservableSet<Contatto> elenco = rubrica.getElenco();
        assertEquals(2, elenco.size());
        assertTrue(elenco.contains(c1));
        assertTrue(elenco.contains(c2));
    }

    /**
     * Test of getElencoPreferiti method, of class Rubrica.
     */
    @Test
    public void testGetElencoPreferiti() {
        
        rubrica.aggiungiContatto(c1);
        rubrica.aggiungiContatto(c2);

        ObservableSet<Contatto> preferiti = rubrica.getElencoPreferiti();
        assertEquals(1, preferiti.size());
        assertTrue(preferiti.contains(c2));
        assertFalse(preferiti.contains(c1));
    }

    /**
     * Test of ricercaContatti method, of class Rubrica.
     */
    @Test
    public void testRicercaContatti() {
        
       rubrica.aggiungiContatto(c1);
        rubrica.aggiungiContatto(c2);

        ObservableSet<Contatto> risultati = rubrica.ricercaContatti("Mario");
        assertEquals(1, risultati.size());
        assertTrue(risultati.contains(c1));

        risultati = rubrica.ricercaContatti("aaa");
        assertEquals(0, risultati.size());
        assertFalse(risultati.contains(c2));

        risultati = rubrica.ricercaContatti("");
        assertEquals(2, risultati.size());
    }

    /**
     * Test of aggiungiContatto method, of class Rubrica.
     */
    @Test
    public void testAggiungiContatto() {
         
        assertTrue(rubrica.aggiungiContatto(c1));
        assertTrue(rubrica.getElenco().contains(c1));

        assertTrue(rubrica.aggiungiContatto(c2));
        assertTrue(rubrica.getElenco().contains(c2));
    }

    /**
     * Test of rimuoviContatto method, of class Rubrica.
     */
    @Test
    public void testRimuoviContatto() {

        rubrica.aggiungiContatto(c1);
        assertTrue(rubrica.rimuoviContatto(c1));
        assertFalse(rubrica.getElenco().contains(c1));

        // Test su rubrica vuota
        assertFalse(rubrica.rimuoviContatto(c2));
    }

    /**
     * Test of resetRubrica method, of class Rubrica.
     */
    @Test
    public void testResetRubrica() {
        
        rubrica.aggiungiContatto(c1);
        rubrica.aggiungiContatto(c2);

        rubrica.resetRubrica();

        assertTrue(rubrica.getElenco().isEmpty());
        assertTrue(rubrica.getElencoPreferiti().isEmpty());
    }

    /**
     * Test of isRubricaVuota method, of class Rubrica.
     */
    @Test
    public void testIsRubricaVuota() {
           
       assertTrue(rubrica.isRubricaVuota());

        rubrica.aggiungiContatto(c1);
        assertFalse(rubrica.isRubricaVuota());

        rubrica.rimuoviContatto(c1);
        assertTrue(rubrica.isRubricaVuota());
    }
    

    /**
     * Test of aggiungiAPreferiti method, of class Rubrica.
     */
    @Test
    public void testAggiungiAPreferiti() {
        
       rubrica.aggiungiContatto(c2);
        rubrica.aggiungiAPreferiti(c2);

        assertTrue(rubrica.getElencoPreferiti().contains(c2));
    }

    /**
     * Test of rimuoviDaPreferiti method, of class Rubrica.
     */
    @Test
    public void testRimuoviDaPreferiti() {
           
        rubrica.aggiungiContatto(c2);
        rubrica.rimuoviDaPreferiti(c2);

        assertFalse(rubrica.getElencoPreferiti().contains(c1));
    }

    /**
     * Test of esportaRubrica method, of class Rubrica.
     *
    @Test
    public void testEsportaRubrica() throws Exception {
        
        File file = File.createTempFile("rubrica_test", ".txt");
        file.deleteOnExit();

        try (FileWriter writer = new FileWriter(file)) {
            writer.write("Mario,Rossi,12345,,mario.rossi@example.com,,,true\n");
        }
        rubrica.importaRubrica();

        assertEquals(1, rubrica.getElenco().size());
        assertTrue(rubrica.getElenco().stream().anyMatch(c -> c.getNome().equals("Mario") && c.getCognome().equals("Rossi")));
    }*/
}
