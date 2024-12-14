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
 * Classe di test per la classe {@link RubricaPreferiti}.
 * Questo test verifica il corretto funzionamento della classe {@link RubricaPreferiti},
 * che gestisce l'elenco dei contatti preferiti. Vengono verificati i metodi di aggiunta, rimozione,
 * ricerca dei contatti, così come il reset della rubrica e il controllo dello stato.
 */
public class RubricaPreferitiTest {
    
    private RubricaPreferiti rubrica;
    private Contatto c1, c2, c3;

    /**
     * Configura l'ambiente di test prima di ogni test.
     * Vengono creati contatti e inizializzata la rubrica dei preferiti.
     * 
     * @throws InvalidContactException se si verifica un errore nella creazione dei contatti.
     */
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
        
        c1 = new Contatto("Mario", "Rossi", numeri, email, true);
        
        Set<String> numeri2 = new HashSet<>();
        numeri2.add("2221234567");
        numeri2.add("1237654321");
        numeri2.add("7779868424");
        
        Set<String> email2 = new HashSet<>();
        email2.add("umberto-zorro@example.com");
        email2.add("adelaide@gmail.com");
        email2.add("jAx@unisa.it");
         
        c2 = new Contatto("Umberto","Zorro",numeri2,email2,false);
        
        c3 = new Contatto("Umberto","Zorro",numeri2,email2,true);
    }
   
    /**
     * Testa la corretta inizializzazione della rubrica preferiti.
     * Si verifica che la rubrica sia vuota all'inizio.
     */
    @Test
    public void testRubricaPreferiti(){
        assertNotNull(rubrica.getElencoPreferiti());
        assertTrue(rubrica.getElencoPreferiti().isEmpty());
    }
     
    /**
     * Testa l'aggiunta di un contatto preferito valido.
     * Si verifica che il contatto venga aggiunto alla rubrica dei preferiti.
     */
    @Test
    public void testAddContattoPreferito1() {
        rubrica.addContattoPreferito(c1);
        assertTrue(rubrica.getElencoPreferiti().contains(c1)); // Controlla che c1 sia stato inserito
    }

    /**
     * Testa l'aggiunta di un contatto che non può essere aggiunto alla rubrica.
     * Si verifica che il contatto con preferito impostato su false non venga aggiunto.
     */
    @Test
    public void testAddContattoPreferito2() {
        rubrica.addContattoPreferito(c2);
        assertFalse(rubrica.getElencoPreferiti().contains(c2)); // Controlla che c2 non sia stato inserito
    }
    
    /**
     * Testa la rimozione di un contatto dalla rubrica dei preferiti.
     * Si verifica che il contatto venga rimosso correttamente.
     */
    @Test
    public void testRemoveContattoPreferito() {
        rubrica.addContattoPreferito(c1);
        rubrica.removeContattoPreferito(c1);
        assertFalse(rubrica.getElencoPreferiti().contains(c1)); // Controlla che c1 sia stato rimosso
    }

    /**
     * Testa il reset della rubrica dei preferiti.
     * Si verifica che tutti i contatti vengano rimossi e la rubrica diventi vuota.
     */
    @Test
    public void testResetRubricaPreferiti() {
        rubrica.addContattoPreferito(c1);
        rubrica.resetRubricaPreferiti();
        assertTrue(rubrica.getElencoPreferiti().isEmpty()); // Controlla che la rubrica sia vuota dopo il reset
    }
    
    /**
     * Testa il recupero dell'elenco dei preferiti quando la rubrica è vuota.
     * Si verifica che la dimensione dell'elenco sia zero.
     */
    @Test
    public void testGetElencoPreferiti1() {
        ObservableSet<Contatto> result = rubrica.getElencoPreferiti(); // Prende l'elenco dei preferiti
        assertEquals(result.size(), 0); // La rubrica deve essere vuota
    }

    /**
     * Testa il recupero dell'elenco dei preferiti con un contatto inserito.
     * Si verifica che l'elenco contenga il contatto aggiunto.
     */
    @Test
    public void testGetElencoPreferiti2() {
        rubrica.addContattoPreferito(c1);
        ObservableSet<Contatto> result = rubrica.getElencoPreferiti(); // Prende l'elenco dei preferiti
        assertEquals(result.size(), 1); // L'elenco contiene solo c1
        assertTrue(rubrica.getElencoPreferiti().contains(c1)); // c1 è presente
    }

    /**
     * Testa la ricerca di contatti preferiti quando il campo di ricerca è vuoto.
     * Si verifica che vengano restituiti tutti i contatti preferiti.
     */
    @Test
    public void testRicercaContatti1Preferiti() {
        rubrica.addContattoPreferito(c1);
        rubrica.addContattoPreferito(c3);
        ObservableSet<Contatto> risultati = rubrica.ricercaContattiPreferiti("");
        assertEquals(2, risultati.size()); // Dovrebbero esserci 2 contatti
        assertTrue(risultati.contains(c1));
        assertTrue(risultati.contains(c3));
    }
    
    /**
     * Testa la ricerca di contatti preferiti per nome.
     * Si verifica che venga trovato il contatto con il nome corrispondente.
     */
    @Test
    public void testRicercaContattiPreferiti2() {
        rubrica.addContattoPreferito(c1);
        rubrica.addContattoPreferito(c3);
        ObservableSet<Contatto> risultati = rubrica.ricercaContattiPreferiti("Mario");
        assertEquals(1, risultati.size()); // Dovrebbe essere trovato 1 contatto
        assertTrue(risultati.contains(c1)); // c1 è presente
    }
    
    /**
     * Testa la ricerca di contatti preferiti per cognome.
     * Si verifica che venga trovato il contatto con il cognome corrispondente.
     */
    @Test
    public void testRicercaContattiPreferiti3() {
        rubrica.addContattoPreferito(c1);
        rubrica.addContattoPreferito(c3);
        ObservableSet<Contatto> risultati = rubrica.ricercaContattiPreferiti("Rossi");
        assertEquals(1, risultati.size()); // Dovrebbe essere trovato 1 contatto
        assertTrue(risultati.contains(c1)); // c1 è presente
    }
    
    /**
     * Testa la ricerca di contatti preferiti per l'iniziale del cognome.
     * Si verifica che venga trovato il contatto con l'iniziale corrispondente.
     */
    @Test
    public void testRicercaContattiPreferiti4() {
        rubrica.addContattoPreferito(c1);
        rubrica.addContattoPreferito(c3);
        ObservableSet<Contatto> risultati = rubrica.ricercaContattiPreferiti("R");
        assertEquals(1, risultati.size()); // Dovrebbe essere trovato 1 contatto
        assertTrue(risultati.contains(c1)); // c1 è presente
    }
    
    /**
     * Testa la ricerca di contatti preferiti per l'iniziale del nome.
     * Si verifica che venga trovato il contatto con l'iniziale corrispondente.
     */
    @Test
    public void testRicercaContattiPreferiti5() {
        rubrica.addContattoPreferito(c1);
        rubrica.addContattoPreferito(c3);
        ObservableSet<Contatto> risultati = rubrica.ricercaContattiPreferiti("R");
        assertEquals(1, risultati.size()); // Dovrebbe essere trovato 1 contatto
        assertTrue(risultati.contains(c1)); // c1 è presente
    }

    /**
     * Testa la ricerca di contatti preferiti per un nome che non esiste.
     * Si verifica che non vengano trovati contatti corrispondenti.
     */
    @Test
    public void testRicercaContattiPreferiti6() {
        rubrica.addContattoPreferito(c1);
        rubrica.addContattoPreferito(c2);
        ObservableSet<Contatto> risultati = rubrica.ricercaContattiPreferiti("aaa");
        assertEquals(0, risultati.size()); // Nessun contatto trovato
    }
    
}
