package it.unisa.diem.mycontacts.data;

import it.unisa.diem.mycontacts.exceptions.InvalidContactException;
import java.util.HashSet;
import java.util.Set;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Classe di test per la classe {@link Contatto}.
 * Questo test verifica il corretto funzionamento di tutti i metodi della classe {@link Contatto},
 * inclusi costruttori, metodi getter, equals, compareTo e gestione delle eccezioni.
 */
public class ContattoTest {
    
    private Contatto c1, c2, c3, c4, c5;

    /**
     * Configura l'ambiente di test prima di ogni test.
     * Viene creato un insieme di numeri e email per essere utilizzato nei vari test.
     * 
     * @throws InvalidContactException se si verifica un errore nella creazione del contatto.
     */
    @BeforeEach
    public void setUp() throws InvalidContactException {
        Set<String> numeri = new HashSet<>();
        numeri.add("3331234567");
        numeri.add("0817654321");
        numeri.add("4569868424");

        Set<String> email = new HashSet<>();
        email.add("mario.rossi@example.com");
        email.add("rossimario@gmail.com");
        email.add("tonyeffe@unisa.it");

        c1 = new Contatto("Mario", "Rossi", numeri, email, false);
        c2 = new Contatto("Mario", "Rossi", null, null, true);
        c3 = new Contatto("Mario", "", null, email, true);
        c4 = new Contatto("", "Rossi", null, null, true);
    }
   
    /**
     * Testa la creazione di un contatto valido.
     */
    @Test
    public void testContatto1() throws InvalidContactException {
        Set<String> numeri = new HashSet<>();
        numeri.add("3331234567");
        numeri.add("0817654321");
        numeri.add("4569868424");

        Set<String> email = new HashSet<>();
        email.add("mario.rossi@example.com");
        email.add("rossimario@gmail.com");
        email.add("tonyeffe@unisa.it");
        
        c1 = new Contatto("Mario", "Rossi", numeri, email, false);
        assertNotNull(c1);
    }
    
    /**
     * Testa la creazione di un contatto con numeri ed email nulli.
     */
    @Test
    public void testContatto2() throws InvalidContactException {
        c2 = new Contatto("Mario", "Rossi", null, null, true);
        assertNotNull(c2);
    }
    
    /**
     * Testa la creazione di un contatto con cognome vuoto.
     */
    @Test
    public void testContatto3() throws InvalidContactException {
        c3 = new Contatto("Mario", "", null, null, true);
        assertNotNull(c3);
    }
    
    /**
     * Testa la creazione di un contatto con nome vuoto.
     */
    @Test
    public void testContatto4() throws InvalidContactException {
        c4 = new Contatto("", "Rossi", null, null, true);
        assertNotNull(c4);
    }
    
    /**
     * Testa la creazione di un contatto con nome e cognome vuoti.
     * Si aspetta che venga lanciata un'eccezione {@link InvalidContactException}.
     */
    @Test
    public void testContatto5() throws InvalidContactException {
        assertThrows(InvalidContactException.class, () -> {
            new Contatto("", "", null, null, true);
        });
    }
    
    /**
     * Testa il metodo {@link Contatto#getNome()} per il caso di nome valido.
     */
    @Test
    public void testGetNome1() {
        String expResult = "Mario";
        String result = c1.getNome();
        assertEquals(expResult, result);
    }
    
    /**
     * Testa il metodo {@link Contatto#getNome()} per il caso di nome vuoto.
     */
    @Test
    public void testGetNome2() {
        String expResult = "";
        String result = c4.getNome();
        assertEquals(expResult, result);
    }

    /**
     * Testa il metodo {@link Contatto#getCognome()} per il caso di cognome valido.
     */
    @Test
    public void testGetCognome1() {
        String expResult = "Rossi";
        String result = c1.getCognome();
        assertEquals(expResult, result);
    }
    
    /**
     * Testa il metodo {@link Contatto#getCognome()} per il caso di cognome vuoto.
     */
    @Test
    public void testGetCognome2() {
        String expResult = "";
        String result = c3.getCognome();
        assertEquals(expResult, result);
    }

    /**
     * Testa il metodo {@link Contatto#getNumeri()} per il caso di numeri validi.
     */
    @Test
    public void testGetNumeri1() {
        Set<String> expResult = new HashSet<>();
        expResult.add("3331234567");
        expResult.add("0817654321");
        expResult.add("4569868424");
        
        Set<String> result = c1.getNumeri();
        assertEquals(expResult, result);
    }
    
    /**
     * Testa il metodo {@link Contatto#getNumeri()} per il caso di numeri nulli.
     */
    @Test
    public void testGetNumeri2() {
        Set<String> expResult = new HashSet<>();
        Set<String> result = c2.getNumeri();
        assertEquals(expResult, result);
    }

    /**
     * Testa il metodo {@link Contatto#getEmail()} per il caso di email valide.
     */
    @Test
    public void testGetEmail1() {
        Set<String> expResult = new HashSet<>();
        expResult.add("mario.rossi@example.com");
        expResult.add("rossimario@gmail.com");
        expResult.add("tonyeffe@unisa.it");
        
        Set<String> result = c1.getEmail();
        assertEquals(expResult, result);
    }
    
    /**
     * Testa il metodo {@link Contatto#getEmail()} per il caso di email nulle.
     */
    @Test
    public void testGetEmail2() {
        Set<String> expResult = new HashSet<>();
        Set<String> result = c2.getEmail();
        assertEquals(expResult, result);
    }

    /**
     * Testa il metodo {@link Contatto#isPreferito()} per il caso di contatto non preferito.
     */
    @Test
    public void testIsPreferito1() {
        boolean expResult = false;
        boolean result = c1.isPreferito();
        assertEquals(expResult, result);
    }
    
    /**
     * Testa il metodo {@link Contatto#isPreferito()} per il caso di contatto preferito.
     */
    @Test
    public void testIsPreferito2() {
        boolean expResult = true;
        boolean result = c2.isPreferito();
        assertEquals(expResult, result);
    }

    /**
     * Testa il metodo {@link Contatto#equals(Object)} per il confronto tra due contatti uguali.
     */
    @Test
    public void testEquals1() throws InvalidContactException {
        Contatto c = new Contatto("Mario", "Rossi", null, null, true);
        boolean expResult = true;
        boolean result = c2.equals(c);
        assertEquals(true, result);
    }
    
    /**
     * Testa il metodo {@link Contatto#equals(Object)} per il confronto tra due contatti con stato diverso.
     */
    @Test
    public void testEquals2() throws InvalidContactException {
        Contatto c = new Contatto("Mario", "Rossi", null, null, false);
        boolean expResult = true;
        boolean result = c2.equals(c);
        assertEquals(true, result);
    }
    
    /**
     * Testa il metodo {@link Contatto#equals(Object)} per il confronto tra contatti con cognome diverso.
     */
    @Test
    public void testEquals3() throws InvalidContactException {
        Contatto c = new Contatto("Mario", "Verdi", null, null, true);
        boolean expResult = false;
        boolean result = c2.equals(c);
        assertEquals(false, result);
    }

    /**
     * Testa il metodo {@link Contatto#compareTo(Contatto)} per il confronto tra contatti.
     */
    @Test
    public void testCompareTo1() throws InvalidContactException {
        Contatto c = new Contatto("Luigi", "Verdi", null, null, false);
        assertTrue(c1.compareTo(c) < 0);
    }
    
    /**
     * Testa il metodo {@link Contatto#compareTo(Contatto)} per il confronto tra contatti con cognome uguale.
     */
    public void testCompareTo2() throws InvalidContactException {
        Contatto c = new Contatto("Luigi", "Rossi", null, null, false);
        assertTrue(c.compareTo(c1) < 0);
    }
    
    /**
     * Testa il metodo {@link Contatto#compareTo(Contatto)} per il confronto tra contatti con nome diverso.
     */
    public void testCompareTo3() throws InvalidContactException {
        Contatto c = new Contatto("Luigi", "Ricco", null, null, false);
        assertTrue(c.compareTo(c1) < 0);
    }
    
    /**
     * Testa il metodo {@link Contatto#compareTo(Contatto)} per il confronto tra contatti con nome vuoto.
     */
    public void testCompareTo4() throws InvalidContactException {
        Contatto c = new Contatto("Luigi", "", null, null, false);
        assertTrue(c.compareTo(c3) < 0);
    }
    
    /**
     * Testa il metodo {@link Contatto#compareTo(Contatto)} per il confronto tra contatti uguali.
     */
    public void testCompareTo5() throws InvalidContactException {
        Contatto c = new Contatto("Mario", "Rossi", null, null, false);
        assertTrue(c3.compareTo(c) == 0);
    }
    
}

