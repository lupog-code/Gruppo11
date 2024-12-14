/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package it.unisa.diem.mycontacts.datastructure;

import it.unisa.diem.mycontacts.data.Contatto;
import it.unisa.diem.mycontacts.exceptions.InvalidContactException;
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
    public void setUp() throws InvalidContactException {
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
    
    
    @Test
    public void testRubrica(){
        
    // Verifica che l'elenco sia inizializzato e vuoto    
    assertNotNull(rubrica.getElenco());
    assertTrue(rubrica.getElenco().isEmpty());

    // Verifica che l'elenco dei preferiti sia inizializzato e vuoto
    assertNotNull(rubrica.getElencoPreferiti());
    assertTrue(rubrica.getElencoPreferiti().isEmpty());
    }
    
    @Test
    public void testGetElenco() {
        
        assertEquals(0,rubrica.getElenco().size()); //controllo che l'elenco sia inzialmente vuoto
        
        rubrica.aggiungiContatto(c1);
        rubrica.aggiungiContatto(c2);

        ObservableSet<Contatto> elenco = rubrica.getElenco();
        assertEquals(2, elenco.size());
        assertTrue(elenco.contains(c1)); //controllo che sia stato inserito il contatto c1
        assertTrue(elenco.contains(c2)); //controllo che sia stato inserito il contatto c2
    }

    
    @Test
    public void testGetElencoPreferiti() {
        
        assertEquals(0,rubrica.getElenco().size()); //controllo che l'elenco sia inzialmente vuoto

        rubrica.aggiungiContatto(c1);
        rubrica.aggiungiContatto(c2);

        ObservableSet<Contatto> preferiti = rubrica.getElencoPreferiti();
        assertEquals(1, preferiti.size()); //controlla la dimensione dei preferiti
        assertTrue(preferiti.contains(c2)); //lo contiene poichè preferito
        assertFalse(preferiti.contains(c1)); //non è contenuto poichè non preferito
    }

    
    @Test
    public void testRicercaContatti() {
        
        assertEquals(0,rubrica.getElenco().size()); //controllo che l'elenco sia inzialmente vuoto

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

    
    @Test
    public void testAggiungiContatto() {
        
        assertEquals(0,rubrica.getElenco().size()); //controllo che l'elenco sia inzialmente vuoto

        rubrica.aggiungiContatto(c1);
        assertTrue(rubrica.getElenco().contains(c1));

        rubrica.aggiungiContatto(c2);
        assertTrue(rubrica.getElenco().contains(c2));
    }

    
    @Test
    public void testRimuoviContatto() {
        
        assertEquals(0,rubrica.getElenco().size()); //controllo che l'elenco sia inzialmente vuoto

        rubrica.aggiungiContatto(c1);
        assertTrue(rubrica.getElenco().contains(c1));
        rubrica.rimuoviContatto(c1);
        assertFalse(rubrica.getElenco().contains(c1));

        
    }

    
    @Test
    public void testResetRubrica() {
        
        assertEquals(0,rubrica.getElenco().size()); //controllo che l'elenco sia inzialmente vuoto

        rubrica.aggiungiContatto(c1);
        rubrica.aggiungiContatto(c2);

        rubrica.resetRubrica();

        assertTrue(rubrica.getElenco().isEmpty());
        assertTrue(rubrica.getElencoPreferiti().isEmpty());
    }

    
    @Test
    public void testIsRubricaVuota() {
        
        assertEquals(0,rubrica.getElenco().size()); //controllo che l'elenco sia inzialmente vuoto

        assertTrue(rubrica.isRubricaVuota());

        rubrica.aggiungiContatto(c1);
        assertFalse(rubrica.isRubricaVuota());

        rubrica.rimuoviContatto(c1);
        assertTrue(rubrica.isRubricaVuota());
    }
    

    
    @Test
    public void testAggiungiAPreferiti() {
        
        assertEquals(0,rubrica.getElenco().size()); //controllo che l'elenco sia inzialmente vuoto

        rubrica.aggiungiContatto(c2);
        rubrica.aggiungiAPreferiti(c2);

        assertTrue(rubrica.getElencoPreferiti().contains(c2));
    }

    @Test
    public void testRimuoviDaPreferiti() {
        
        assertEquals(0,rubrica.getElenco().size()); //controllo che l'elenco sia inzialmente vuoto

        rubrica.aggiungiContatto(c2);
        assertTrue(rubrica.getElenco().contains(c2)); //controllo che sia presente
        rubrica.rimuoviDaPreferiti(c2);

        assertFalse(rubrica.getElencoPreferiti().contains(c2)); //controllo che sia stao eliminato
        
        assertEquals(1,rubrica.getElenco().size()); //controllo che l'elenco sia vuoto
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
