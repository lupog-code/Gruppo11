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
 * Classe di test per la classe {@link Rubrica}.
 * Contiene casi di test per verificare il comportamento dei metodi
 * di gestione dei contatti, ricerca, preferiti e altre funzionalità della rubrica.
 */
public class RubricaTest {
    
    private Contatto c1;
    private Contatto c2;
    private Rubrica rubrica;
    
    /**
     * Configura l'ambiente di test prima di ogni test.
     * Crea una rubrica vuota e inizializza due contatti da utilizzare nei test.
     * 
     * @throws InvalidContactException se si verifica un errore nella creazione dei contatti.
     */
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
        
        c1 = new Contatto("Mario", "Rossi", numeri, email, true);
        
        Set<String> numeri2 = new HashSet<>();
        numeri.add("2221234567");
        numeri.add("1237654321");
        numeri.add("7779868424");
        
        Set<String> email2 = new HashSet<>();
        email.add("umberto-zorro@example.com");
        email.add("adelaide@gmail.com");
        email.add("jAx@unisa.it");
         
        c2 = new Contatto("Umberto", "Zorro", numeri2, email2, false);
    }
    
    /**
     * Verifica l'inizializzazione corretta della rubrica e dei preferiti.
     */
    @Test
    public void testRubrica() { 
        assertNotNull(rubrica.getElenco());
        assertTrue(rubrica.getElenco().isEmpty());
        assertNotNull(rubrica.getElencoPreferiti());
        assertTrue(rubrica.getElencoPreferiti().isEmpty());
    }
    
    /**
     * Verifica che l'elenco iniziale dei contatti sia vuoto.
     */
    @Test
    public void testGetElenco1() { 
        ObservableSet<Contatto> elenco = rubrica.getElenco();
        assertEquals(0, elenco.size());
    }
    
    /**
     * Verifica che un contatto aggiunto venga correttamente incluso nell'elenco.
     */
    @Test
    public void testGetElenco2() { 
        rubrica.aggiungiContatto(c1);
        ObservableSet<Contatto> elenco = rubrica.getElenco();
        assertEquals(1, elenco.size());
        assertTrue(elenco.contains(c1));
    }
    
    /**
     * Verifica che l'elenco dei preferiti sia inizialmente vuoto.
     */
    @Test
    public void testGetElencoPreferiti1() {
        ObservableSet<Contatto> preferiti = rubrica.getElencoPreferiti();
        assertEquals(0, preferiti.size());
    }
    
    /**
     * Verifica che un contatto aggiunto ai preferiti sia effettivamente incluso.
     */
    @Test
    public void testGetElencoPreferiti2() {
        rubrica.aggiungiAPreferiti(c1);
        ObservableSet<Contatto> preferiti = rubrica.getElencoPreferiti();
        assertEquals(1, preferiti.size());
        assertTrue(preferiti.contains(c1));
    }

    /**
     * Testa la ricerca dei contatti: ricerca vuota restituisce l'intera rubrica.
     */
    @Test
    public void testRicercaContatti1() {
        rubrica.aggiungiContatto(c1);
        rubrica.aggiungiContatto(c2);
        ObservableSet<Contatto> risultati = rubrica.ricercaContatti("");
        assertEquals(2, risultati.size());
        assertTrue(risultati.contains(c1));
        assertTrue(risultati.contains(c2));
    }
    
    /**
     * Testa la ricerca dei contatti per nome.
     */
    @Test
    public void testRicercaContatti2() {
        rubrica.aggiungiContatto(c1);
        rubrica.aggiungiContatto(c2);
        ObservableSet<Contatto> risultati = rubrica.ricercaContatti("Mario");
        assertEquals(1, risultati.size());
        assertTrue(risultati.contains(c1));
    }
    
    /**
     * Testa la ricerca dei contatt per cognome.
     */
    @Test
    public void testRicercaContatti3() {
        rubrica.aggiungiContatto(c1);
        rubrica.aggiungiContatto(c2);
        ObservableSet<Contatto> risultati = rubrica.ricercaContatti("Rossi");
        assertEquals(1, risultati.size());
        assertTrue(risultati.contains(c1));
    }
    
    /**
     * Testa la ricerca di contatti per l'iniziale del cognome.
     */
    @Test
    public void testRicercaContatti4() {
        rubrica.aggiungiContatto(c1);
        rubrica.aggiungiContatto(c2);
        ObservableSet<Contatto> risultati = rubrica.ricercaContatti("R");
        assertEquals(1, risultati.size());
        assertTrue(risultati.contains(c1));
    }
    
    /**
     * Testa la ricerca di contatti per l'iniziale del nome.
     */
    @Test
    public void testRicercaContatti5() {
        rubrica.aggiungiContatto(c1);
        rubrica.aggiungiContatto(c2);
        ObservableSet<Contatto> risultati = rubrica.ricercaContatti("M");
        assertEquals(1, risultati.size());
        assertTrue(risultati.contains(c1));
    }

    /**
     * Testa la ricerca di contatti per un nome che non esiste.
     */
    @Test
    public void testRicercaContatti6() {
        rubrica.aggiungiContatto(c1);
        rubrica.aggiungiContatto(c2);
        ObservableSet<Contatto> risultati = rubrica.ricercaContatti("aaa");
        assertEquals(0, risultati.size());
    }

    /**
     * Verifica che sia possibile aggiungere un contatto alla rubrica.
     */
    @Test
    public void testAggiungiContatto() {
        rubrica.aggiungiContatto(c1);
        assertTrue(rubrica.getElenco().contains(c1));
    }
  
    /**
     * Verifica la rimozione di un contatto dalla rubrica.
     */
    @Test
    public void testRimuoviContatto() {
        rubrica.aggiungiContatto(c1);
        rubrica.rimuoviContatto(c1);
        assertFalse(rubrica.getElenco().contains(c1));
    }

    /**
     * Testa la funzionalità di reset della rubrica.
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
     * Testa il metodo {@link Rubrica#isRubricaVuota()} quando la rubrica è vuota.
     */
    @Test
    public void testIsRubricaVuota1() {
        assertTrue(rubrica.isRubricaVuota());
    }
    
    /**
     * Testa il metodo {@link Rubrica#isRubricaVuota()} quando la rubrica non è vuota.
     */
    @Test
    public void testIsRubricaVuota2() {
        rubrica.aggiungiContatto(c1);
        assertFalse(rubrica.isRubricaVuota());
    }
    
    /**
     * Verifica che un contatto possa essere aggiunto ai preferiti.
     */
    @Test
    public void testAggiungiAPreferiti1() {
        rubrica.aggiungiAPreferiti(c1);
        assertTrue(rubrica.getElencoPreferiti().contains(c1));
    }

    /**
     * Verifica che un contatto non preferito non venga aggiunto.
     */
    @Test
    public void testAggiungiAPreferiti2() {
        rubrica.aggiungiAPreferiti(c2);
        assertFalse(rubrica.getElencoPreferiti().contains(c2));
    }

    /**
     * Testa la rimozione di un contatto dai preferiti.
     */
    @Test
    public void testRimuoviDaPreferiti() {
        rubrica.aggiungiAPreferiti(c1);
        rubrica.rimuoviDaPreferiti(c1);
        assertFalse(rubrica.getElencoPreferiti().contains(c2));
    }
    
}