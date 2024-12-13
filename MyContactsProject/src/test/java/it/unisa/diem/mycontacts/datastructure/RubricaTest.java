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
    
    public RubricaTest() {
    }
    
    @BeforeAll
    public static void setUpClass() {
    }
    
    @AfterAll
    public static void tearDownClass() {
    }
    
    @BeforeEach
    public void setUp() {
        Rubrica rubrica = new Rubrica();
         
        Set<String> numeri = new HashSet<>();
        numeri.add("3331234567");
        numeri.add("0817654321");
        numeri.add("4569868424");

        Set<String> email = new HashSet<>();
        email.add("mario.rossi@example.com");
        email.add("rossimario@gmail.com");
        email.add("tonyeffe@unisa.it");
        Contatto c1= new Contatto("Mario", "Rossi", numeri, email, false);
        
        Set<String> numeri2 = new HashSet<>();
        numeri.add("2221234567");
        numeri.add("1237654321");
        numeri.add("7779868424");
        
        Set<String> email2 = new HashSet<>();
        email.add("umberto-zorro@example.com");
        email.add("adelaide@gmail.com");
        email.add("jAx@unisa.it");
        Contatto c2 =new Contatto("Umberto","Zorro",numeri2,email2,true);
        
    }
    
    @AfterEach
    public void tearDown() {
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
        assertTrue(preferiti.contains(c1));
        assertFalse(preferiti.contains(c2));
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

        risultati = rubrica.ricercaContatti("L");
        assertEquals(1, risultati.size());
        assertTrue(risultati.contains(c2));

        risultati = rubrica.ricercaContatti("");
        assertEquals(2, risultati.size());
    }

    /**
     * Test of aggiungiContatto method, of class Rubrica.
     */
    @Test
    public void testAggiungiContatto() {
        assertTrue(rubrica.aggiungiContatto(c1));
        assertTrue(rubrica.getElenco().contains(c2));

        assertTrue(rubrica.aggiungiContatto(c1));
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
        System.out.println("rimuoviDaPreferiti");
        Contatto c = null;
        Rubrica instance = new Rubrica();
        instance.rimuoviDaPreferiti(c);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of esportaRubrica method, of class Rubrica.
     */
    @Test
    public void testEsportaRubrica() throws Exception {
       rubrica.aggiungiContatto(c1);
        File file = File.createTempFile("rubrica_test", ".txt");
        file.deleteOnExit();

        try (FileWriter writer = new FileWriter(file)) {
            rubrica.esportaRubrica();
        }

        assertTrue(file.exists());
        assertTrue(file.length() > 0);
    }

    /**
     * Test of importaRubrica method, of class Rubrica.
     */
    @Test
    public void testImportaRubrica() throws Exception {
        File file = File.createTempFile("rubrica_test", ".txt");
        file.deleteOnExit();

        try (FileWriter writer = new FileWriter(file)) {
            writer.write("Mario,Rossi,12345,,mario.rossi@example.com,,,true\n");
        }
        rubrica.importaRubrica();

        assertEquals(1, rubrica.getElenco().size());
        assertTrue(rubrica.getElenco().stream().anyMatch(c -> c.getNome().equals("Mario") && c.getCognome().equals("Rossi")));
    }
}
