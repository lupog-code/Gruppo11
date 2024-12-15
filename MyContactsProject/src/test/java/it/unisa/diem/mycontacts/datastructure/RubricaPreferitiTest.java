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
    
   
    @Test
    public void testRubricaPreferiti(){
        
        //controllo che l'elenco dei preferiti sia inizializzato e vuoto
        assertNotNull(rubrica.getElencoPreferiti());
        assertTrue(rubrica.getElencoPreferiti().isEmpty());
    }
    
    
    
    @Test
    public void testAddContattoPreferito() {
        
        assertEquals(0,rubrica.getElencoPreferiti().size()); //controllo che l'elenco sia inzialmente vuoto

        rubrica.addContattoPreferito(c1);
        rubrica.addContattoPreferito(c2);

        assertFalse(rubrica.getElencoPreferiti().contains(c1)); //controllo che c1 non sia stato inserito
        assertTrue(rubrica.getElencoPreferiti().contains(c2)); //controllo che c2 sia stato inserito
    }

    
    @Test
    public void testRemoveContattoPreferito() {
        
        assertEquals(0,rubrica.getElencoPreferiti().size()); //controllo che l'elenco sia inzialmente vuoto
        
        rubrica.addContattoPreferito(c2);
        assertTrue(rubrica.getElencoPreferiti().contains(c2)); //controllo che il contatto sia correttamente inserito

        rubrica.removeContattoPreferito(c2);
       
        assertFalse(rubrica.getElencoPreferiti().contains(c2));//controllo che il contatto sia correttamente rimosso
    }

    @Test
    public void testResetRubricaPreferiti() {

        assertEquals(0,rubrica.getElencoPreferiti().size()); //controllo che l'elenco sia inzialmente vuoto
        rubrica.addContattoPreferito(c2);
        assertTrue(rubrica.getElencoPreferiti().contains(c2)); //controllo inserimento corretto del contatto
        
        rubrica.resetRubricaPreferiti();
        assertTrue(rubrica.getElencoPreferiti().isEmpty()); //controllo che la rubrica sia vuota dopo il reset
    }

    @Test
    public void testGetElencoPreferiti() {
        
        assertEquals(0,rubrica.getElencoPreferiti().size()); //controllo che l'elenco sia inzialmente vuoto

        rubrica.addContattoPreferito(c1);
        rubrica.addContattoPreferito(c2);
        ObservableSet<Contatto> result = rubrica.getElencoPreferiti(); //prende l'elenco dei preferiti
        
        assertEquals(result.size(), 1); //1 poiche solo c2 è stato inserito
        assertTrue(rubrica.getElencoPreferiti().contains(c2)); //c2 è presente
        assertFalse(rubrica.getElencoPreferiti().contains(c1)); //c1 non è presente
    }

    
    @Test
    public void testRicercaContattiPreferiti() {
        
        assertEquals(0,rubrica.getElencoPreferiti().size()); //controllo che l'elenco sia inzialmente vuoto
        
        rubrica.addContattoPreferito(c1); //non sarà agigunto poichè non preferito
        rubrica.addContattoPreferito(c2); //sarà aggiunto poichè preferito

        ObservableSet<Contatto> risultati; 
        
        risultati = rubrica.ricercaContattiPreferiti("Rossi");
        assertEquals(0, risultati.size()); //0 poichè Rossi è contenuto in c1 che non è stato aggiunto a preferiti
        assertFalse(risultati.contains(c1)); //non contiene c1 poiche non è preferito
        assertFalse(risultati.contains(c2)); //non contiene c2 poichè Rossi non è predente al suo interno
        
        risultati = rubrica.ricercaContattiPreferiti("Umberto");
        assertEquals(1, risultati.size()); //1 poichè umberto è contenuto in c2 che è aggiunto a preferiti
        assertTrue(risultati.contains(c2)); //true poichè c2 è predente in preferiti
        assertFalse(risultati.contains(c1)); // false poichè c1 non è presente in preferiti

        risultati = rubrica.ricercaContattiPreferiti(""); //mostra tutti i contatti all'interno dei preferiti
        assertEquals(1, risultati.size()); //1 poichè solo 1 contatto presente in preferiti
        assertTrue(risultati.contains(c2)); //c2 è presente
        assertFalse(risultati.contains(c1)); //c1 non è presente
    }
    
}
